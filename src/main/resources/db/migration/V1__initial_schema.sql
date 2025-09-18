CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE SCHEMA spend_lens;

CREATE TABLE spend_lens.role_type (
    code VARCHAR(10) PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE SEQUENCE spend_lens.app_user_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    CACHE 20
    NO CYCLE;

CREATE TABLE spend_lens.app_user (
    id BIGINT DEFAULT nextval('spend_lens.app_user_id_seq'),
    username VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100),
    email VARCHAR(254) NOT NULL,
    email_normalized TEXT GENERATED ALWAYS AS (
        LOWER(BTRIM(email)) COLLATE "C"
    ) STORED,
    contact_number VARCHAR(25),
    role_type VARCHAR(10) NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_by BIGINT NOT NULL,
    created_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_modified_by BIGINT,
    last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_app_user PRIMARY KEY (id),
    CONSTRAINT fk_app_user__role_type FOREIGN KEY (role_type)
        REFERENCES spend_lens.role_type (code),
    CONSTRAINT ck_app_user__email_format_valid
        CHECK (email ~* '^[^@\s]+@[^@\s]+\.[^@\s]+$')
);

ALTER SEQUENCE spend_lens.app_user_id_seq
    OWNED BY spend_lens.app_user.id;

CREATE UNIQUE INDEX uq_app_user__email_normalized__active_only
ON spend_lens.app_user (email_normalized)
WHERE is_active;

CREATE SEQUENCE spend_lens.app_user_cash_flow_type_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    CACHE 20
    NO CYCLE;

CREATE TABLE spend_lens.app_user_cash_flow_type (
    id BIGINT DEFAULT nextval('spend_lens.app_user_cash_flow_type_id_seq'),
    name VARCHAR(100) NOT NULL,
    name_normalized TEXT GENERATED ALWAYS AS (
        LOWER(BTRIM(name)) COLLATE "C"
    ) STORED,
    app_user_id BIGINT NOT NULL,
    is_default BOOLEAN NOT NULL DEFAULT FALSE,
    created_by BIGINT NOT NULL,
    created_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_modified_by BIGINT,
    last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_app_user_cash_flow_type PRIMARY KEY (id),
    CONSTRAINT fk_app_user_cash_flow_type__app_user FOREIGN KEY (app_user_id)
        REFERENCES spend_lens.app_user (id) ON DELETE CASCADE,
    CONSTRAINT uq_app_user_cft__user__name_normalized UNIQUE (app_user_id, name_normalized)
);

CREATE INDEX ix_app_user__cash_flow_type_user
    ON spend_lens.app_user_cash_flow_type (app_user_id);

ALTER SEQUENCE spend_lens.app_user_cash_flow_type_id_seq
    OWNED BY spend_lens.app_user_cash_flow_type.id;

CREATE SEQUENCE spend_lens.app_user_cash_flow_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    CACHE 20
    NO CYCLE;

CREATE TABLE spend_lens.app_user_cash_flow (
    id BIGINT DEFAULT nextval('spend_lens.app_user_cash_flow_id_seq'),
    cash_flow_date date NOT NULL,
    cash_flow_month date GENERATED ALWAYS AS (
        date_trunc('month', cash_flow_date::timestamp)::date
    ) STORED,
    description VARCHAR(100) NOT NULL,
    description_normalized TEXT GENERATED ALWAYS AS (
        LOWER(BTRIM(description)) COLLATE "C"
    ) STORED,
    amount NUMERIC(10,2) CHECK (amount >= 0) NOT NULL,
    app_user_id BIGINT NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_by BIGINT NOT NULL,
    created_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_modified_by BIGINT,
    last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_app_user_cash_flow PRIMARY KEY (id),
    CONSTRAINT fk_app_user_cash_flow__app_user FOREIGN KEY (app_user_id)
        REFERENCES spend_lens.app_user (id),
    CONSTRAINT ck_app_user_cash_flow__amount_bounds
        CHECK (amount >= 0 AND amount <= 99999999.99),
    CONSTRAINT ck_app_user_cash_flow__amount_decimal_scale
        CHECK (amount = ROUND(amount, 2))
);

CREATE INDEX ix_app_user_cash_flow__user__date
    ON spend_lens.app_user_cash_flow (app_user_id, cash_flow_date);

CREATE UNIQUE INDEX uq_app_user_cf__user__date__desc__active_only
ON spend_lens.app_user_cash_flow (app_user_id, cash_flow_month, description_normalized)
WHERE is_active;

ALTER SEQUENCE spend_lens.app_user_cash_flow_type_id_seq
    OWNED BY spend_lens.app_user_cash_flow.id;

CREATE SEQUENCE spend_lens.app_user_cash_flow_type_map_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    CACHE 20
    NO CYCLE;

CREATE TABLE spend_lens.app_user_cash_flow_type_map (
    id BIGINT DEFAULT nextval('spend_lens.app_user_cash_flow_type_map_id_seq'),
    app_user_cash_flow_id BIGINT NOT NULL,
    app_user_cash_flow_type_id BIGINT NOT NULL,
    CONSTRAINT pk_app_user_cash_flow_type_map PRIMARY KEY (id),
    CONSTRAINT fk_app_user_cash_flow_type_map__app_user_cash_flow FOREIGN KEY (app_user_cash_flow_id)
        REFERENCES spend_lens.app_user_cash_flow (id) ON DELETE CASCADE,
    CONSTRAINT fk_app_user_cash_flow_type_map__app_user_cft FOREIGN KEY (app_user_cash_flow_type_id)
        REFERENCES spend_lens.app_user_cash_flow_type (id) ON DELETE CASCADE,
    CONSTRAINT uq_app_user_cft_map__au_cf_id__au_cft_id UNIQUE (app_user_cash_flow_id,app_user_cash_flow_type_id)
);

ALTER SEQUENCE spend_lens.app_user_cash_flow_type_map_id_seq
    OWNED BY spend_lens.app_user_cash_flow_type_map.id;

CREATE SEQUENCE spend_lens.cash_flow_type_template_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    CACHE 20
    NO CYCLE;

CREATE TABLE spend_lens.cash_flow_type_template (
    id BIGINT DEFAULT nextval('spend_lens.cash_flow_type_template_id_seq'),
    name VARCHAR(100) NOT NULL,
    name_normalized TEXT GENERATED ALWAYS AS (
        LOWER(BTRIM(name)) COLLATE "C"
    ) STORED,
    is_default BOOLEAN NOT NULL DEFAULT TRUE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_by BIGINT NOT NULL,
    created_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_modified_by BIGINT,
    last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_cash_flow_type_template PRIMARY KEY (id)
);

CREATE UNIQUE INDEX uq_cft_template__name_normalized__active_only
ON spend_lens.cash_flow_type_template (name_normalized)
WHERE is_active;

ALTER SEQUENCE spend_lens.cash_flow_type_template_id_seq
    OWNED BY spend_lens.cash_flow_type_template.id;

CREATE SEQUENCE spend_lens.bank_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    CACHE 20
    NO CYCLE;

CREATE TABLE spend_lens.bank (
    id BIGINT DEFAULT nextval('spend_lens.bank_id_seq'),
    name VARCHAR(254) NOT NULL,
    name_normalized TEXT GENERATED ALWAYS AS (
        LOWER(BTRIM(name)) COLLATE "C"
    ) STORED,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_by BIGINT NOT NULL,
    created_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_modified_by BIGINT,
    last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_bank PRIMARY KEY (id)
);

CREATE UNIQUE INDEX uq_bank__name__active_only
ON spend_lens.bank (name_normalized)
WHERE is_active;

ALTER SEQUENCE spend_lens.bank_id_seq
    OWNED BY spend_lens.bank.id;

CREATE TABLE spend_lens.asset_type (
    code VARCHAR(10) PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE SEQUENCE spend_lens.app_user_asset_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    CACHE 50
    NO CYCLE;

CREATE TABLE spend_lens.app_user_asset (
    id BIGSERIAL,
    asset_date date NOT NULL,
    asset_month date GENERATED ALWAYS AS (
            date_trunc('month', asset_date::timestamp)::date
    ) STORED,
    asset_type VARCHAR(10) NOT NULL,
    bank_id BIGINT,
    amount NUMERIC(10,2) NOT NULL,
    app_user_id BIGINT NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    version BIGINT NOT NULL DEFAULT 0,
    created_by BIGINT NOT NULL,
    created_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_modified_by BIGINT,
    last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_app_user_asset PRIMARY KEY (id),
    CONSTRAINT fk_app_user_asset__app_user FOREIGN KEY (app_user_id)
        REFERENCES spend_lens.app_user (id),
    CONSTRAINT fk_app_user_asset__asset_type FOREIGN KEY (asset_type)
        REFERENCES spend_lens.asset_type (code),
    CONSTRAINT fk_app_user_asset__bank FOREIGN KEY (bank_id)
        REFERENCES spend_lens.bank (id),
    CONSTRAINT ck_app_user_asset__amount_valid
        CHECK (amount >= 0),
    CONSTRAINT ck_app_user_asset__amount_decimal_valid
        CHECK (amount = ROUND(amount, 2))
);

CREATE UNIQUE INDEX uq_asset__user__month__type__bank__active_only
ON spend_lens.app_user_asset (
    app_user_id,
    asset_month,
    asset_type,
    COALESCE(bank_id, -1)
) WHERE is_active;

CREATE INDEX ix_app_user_asset__user__date
    ON spend_lens.app_user_asset (app_user_id, asset_date);

ALTER SEQUENCE spend_lens.app_user_asset_id_seq
    OWNED BY spend_lens.app_user_asset.id;

CREATE TABLE spend_lens.payment_type (
    code VARCHAR(20) PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE SEQUENCE spend_lens.app_user_expense_category_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    CACHE 20
    NO CYCLE;

CREATE TABLE spend_lens.app_user_expense_category (
    id BIGINT DEFAULT nextval('spend_lens.app_user_expense_category_id_seq'),
    name VARCHAR(100) NOT NULL,
    name_normalized TEXT GENERATED ALWAYS AS (
        LOWER(BTRIM(name)) COLLATE "C"
    ) STORED,
    is_default BOOLEAN NOT NULL DEFAULT TRUE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    app_user_id BIGINT NOT NULL,
    created_by BIGINT NOT NULL,
    created_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_modified_by BIGINT,
    last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_expense_category PRIMARY KEY (id),
    CONSTRAINT fk_app_user_expense_category__app_user FOREIGN KEY (app_user_id)
        REFERENCES spend_lens.app_user (id)
);

CREATE UNIQUE INDEX uq_app_user_ec__user__name__active__only
ON spend_lens.app_user_expense_category (app_user_id, name_normalized)
WHERE is_active;

CREATE INDEX ix_app_user_expense_category__user
    ON spend_lens.app_user_expense_category (app_user_id);

ALTER SEQUENCE spend_lens.app_user_expense_category_id_seq
    OWNED BY spend_lens.app_user_expense_category.id;

CREATE SEQUENCE spend_lens.expense_category_template_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    CACHE 20
    NO CYCLE;

CREATE TABLE spend_lens.expense_category_template(
    id BIGINT default nextval('spend_lens.expense_category_template_id_seq'),
    name VARCHAR(100) NOT NULL,
    name_normalized TEXT GENERATED ALWAYS AS (
        LOWER(BTRIM(name)) COLLATE "C"
    ) STORED,
    is_default BOOLEAN NOT NULL DEFAULT TRUE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_by BIGINT NOT NULL,
    created_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_modified_by BIGINT,
    last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_expense_category_template PRIMARY KEY (id)
);

CREATE UNIQUE INDEX uq_expense_category_template__name__active_only
ON spend_lens.expense_category_template (name_normalized)
WHERE is_active;

ALTER SEQUENCE spend_lens.expense_category_template_id_seq
    OWNED BY spend_lens.expense_category_template.id;

CREATE TABLE spend_lens.expense_category_type(
    code VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE SEQUENCE spend_lens.app_user_expense_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    CACHE 50
    NO CYCLE;

CREATE TABLE spend_lens.app_user_expense (
    id BIGINT DEFAULT nextval('spend_lens.app_user_expense_id_seq'),
    name VARCHAR(100) NOT NULL,
    name_normalized TEXT GENERATED ALWAYS AS (
        LOWER(BTRIM(name)) COLLATE "C"
    ) STORED,
    expense_date DATE NOT NULL,
    payment_type VARCHAR(20) NOT NULL,
    amount NUMERIC(10,2) NOT NULL,
    app_user_id BIGINT NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_by BIGINT NOT NULL,
    created_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_modified_by BIGINT,
    last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_user_expense PRIMARY KEY (id),
    CONSTRAINT fk_app_user_expense__payment_type FOREIGN KEY (payment_type)
        REFERENCES spend_lens.payment_type (code),
    CONSTRAINT fk_app_user_expense__app_user FOREIGN KEY (app_user_id)
        REFERENCES spend_lens.app_user (id),
    CONSTRAINT ck_app_user_expense__amount_bounds
        CHECK (amount >= 0),
    CONSTRAINT ck_app_user_expense__amount_decimal_scale
        CHECK (amount = ROUND(amount, 2))
);

CREATE INDEX ix_app_user_expense__user__date
    ON spend_lens.app_user_expense (app_user_id, expense_date);

CREATE UNIQUE INDEX uq_app_user_expense__user__name__date__active_only
ON spend_lens.app_user_expense (app_user_id, name_normalized, expense_date)
WHERE is_active;

ALTER SEQUENCE spend_lens.app_user_expense_id_seq
    OWNED BY spend_lens.app_user_expense.id;

CREATE SEQUENCE spend_lens.app_user_expense_category_map_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    CACHE 20
    NO CYCLE;

CREATE TABLE spend_lens.app_user_expense_category_map (
    id BIGINT DEFAULT nextval('spend_lens.app_user_expense_category_map_id_seq'),
    app_user_expense_id BIGINT NOT NULL,
    app_user_expense_category_id BIGINT NOT NULL,
    expense_category_type VARCHAR(20) NOT NULL,
    CONSTRAINT pk_user_expense_category_map PRIMARY KEY (id),
    CONSTRAINT fk_app_user_expense_category_map__app_user_expense FOREIGN KEY (app_user_expense_id)
        REFERENCES spend_lens.app_user_expense (id),
    CONSTRAINT fk_app_user_expense_category_map__app_user_ec FOREIGN KEY (app_user_expense_category_id)
        REFERENCES spend_lens.app_user_expense_category (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_app_user_expense_category_map__app_user_ec_type FOREIGN KEY (expense_category_type)
        REFERENCES spend_lens.expense_category_type (code)
        ON DELETE CASCADE,
    CONSTRAINT uq_app_user_ec_map__expense_id__ec_id UNIQUE (app_user_expense_id, app_user_expense_category_id)
);

ALTER SEQUENCE spend_lens.app_user_expense_category_map_id_seq
    OWNED BY spend_lens.app_user_expense_category_map.id;

CREATE SEQUENCE spend_lens.app_user_time_deposit_id_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    CACHE 50
    NO CYCLE;

CREATE TABLE spend_lens.app_user_time_deposit (
    id BIGINT DEFAULT nextval('spend_lens.app_user_time_deposit_id_seq'),
    bank_id BIGINT NOT NULL,
    maturity_date DATE NOT NULL,
    principal_amount NUMERIC(10,2) NOT NULL,
    app_user_id BIGINT NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    version BIGINT NOT NULL DEFAULT 0,
    created_by BIGINT NOT NULL,
    created_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_modified_by BIGINT,
    last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_time_deposit PRIMARY KEY (id),
    CONSTRAINT fk_app_user_time_deposit__bank FOREIGN KEY (bank_id)
        REFERENCES spend_lens.bank (id),
    CONSTRAINT fk_app_user_time_deposit__app_user FOREIGN KEY (app_user_id)
        REFERENCES spend_lens.app_user (id),
    CONSTRAINT ck_app_user_time_deposit__amount_bounds
        CHECK (principal_amount >= 0),
    CONSTRAINT ck_app_user_time_deposit__amount_decimal_scale
        CHECK (principal_amount = ROUND(principal_amount, 2))
);

CREATE UNIQUE INDEX uq_app_user_td__user__bank__date__active_only
ON spend_lens.app_user_time_deposit (
    app_user_id,
    bank_id,
    maturity_date
) WHERE is_active;

CREATE INDEX ix_app_user_time_deposit__user__date
    ON spend_lens.app_user_time_deposit (app_user_id, maturity_date);

ALTER SEQUENCE spend_lens.app_user_time_deposit_id_seq
    OWNED BY spend_lens.app_user_time_deposit.id;