CREATE SCHEMA spend_lens;

CREATE TABLE spend_lens.user (
  id BIGSERIAL PRIMARY KEY,
  username TEXT UNIQUE NOT NULL,
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  email TEXT,
  contact_number TEXT,
  role_type TEXT NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  created_by BIGINT NOT NULL,
  created_date TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  last_modified_by BIGINT,
  last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE spend_lens.role_type (
  code TEXT PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE spend_lens.cashflow_type (
  code TEXT PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE spend_lens.cashflow (
  id BIGSERIAL PRIMARY KEY,
  cashflow_date TIMESTAMPTZ NOT NULL,
  cashflow_name TEXT NOT NULL,
  cashflow_type TEXT NOT NULL,
  amount NUMERIC(14,2) CHECK (amount >= 0) NOT NULL,
  user_id BIGINT NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  created_by BIGINT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  last_modified_by BIGINT,
  last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE spend_lens.bank (
  id BIGSERIAL PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE spend_lens.asset_type (
  code VARCHAR(20) PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE spend_lens.asset (
  id BIGSERIAL PRIMARY KEY,
  asset_date TIMESTAMPTZ NOT NULL,
  asset_type TEXT NOT NULL,
  bank_id BIGINT,
  amount NUMERIC(14,2) CHECK (amount >= 0) NOT NULL,
  user_id BIGINT NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  created_by BIGINT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  last_modified_by BIGINT,
  last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE spend_lens.expense_type (
  code VARCHAR(20) PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE spend_lens.expense_category (
  code VARCHAR(20) PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE spend_lens.payment_type (
  code VARCHAR(20) PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE spend_lens.expense (
  id BIGSERIAL PRIMARY KEY,
  expense_name TEXT NOT NULL,
  expense_date TIMESTAMPTZ NOT NULL,
  expense_type TEXT NOT NULL,
  expense_category TEXT NOT NULL,
  payment_type TEXT NOT NULL,
  amount NUMERIC(14,2) CHECK (amount >= 0) NOT NULL,
  user_id BIGINT NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  created_by BIGINT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  last_modified_by BIGINT,
  last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE spend_lens.time_deposit (
  id BIGSERIAL PRIMARY KEY,
  bank_id BIGINT NOT NULL,
  maturity_date TIMESTAMPTZ NOT NULL,
  principal_amount NUMERIC(14,2) CHECK (principal_amount >= 0) NOT NULL,
  user_id BIGINT NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  created_by BIGINT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  last_modified_by BIGINT,
  last_modified_date TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

ALTER TABLE spend_lens.user ADD FOREIGN KEY (role_type) REFERENCES spend_lens.role_type (code);

ALTER TABLE spend_lens.user ADD FOREIGN KEY (created_by) REFERENCES spend_lens.user (id);

ALTER TABLE spend_lens.user ADD FOREIGN KEY (last_modified_by) REFERENCES spend_lens.user (id);

ALTER TABLE spend_lens.cashflow ADD FOREIGN KEY (cashflow_type) REFERENCES spend_lens.cashflow_type (code);

ALTER TABLE spend_lens.cashflow ADD FOREIGN KEY (user_id) REFERENCES spend_lens.user (id);

ALTER TABLE spend_lens.cashflow ADD FOREIGN KEY (created_by) REFERENCES spend_lens.user (id);

ALTER TABLE spend_lens.cashflow ADD FOREIGN KEY (last_modified_by) REFERENCES spend_lens.user (id);

ALTER TABLE spend_lens.asset ADD FOREIGN KEY (asset_type) REFERENCES spend_lens.asset_type (code);

ALTER TABLE spend_lens.asset ADD FOREIGN KEY (bank_id) REFERENCES spend_lens.bank (id);

ALTER TABLE spend_lens.asset ADD FOREIGN KEY (user_id) REFERENCES spend_lens.user (id);

ALTER TABLE spend_lens.asset ADD FOREIGN KEY (created_by) REFERENCES spend_lens.user (id);

ALTER TABLE spend_lens.asset ADD FOREIGN KEY (last_modified_by) REFERENCES spend_lens.user (id);

ALTER TABLE spend_lens.expense ADD FOREIGN KEY (expense_type) REFERENCES spend_lens.expense_type (code);

ALTER TABLE spend_lens.expense ADD FOREIGN KEY (expense_category) REFERENCES spend_lens.expense_category (code);

ALTER TABLE spend_lens.expense ADD FOREIGN KEY (payment_type) REFERENCES spend_lens.payment_type (code);

ALTER TABLE spend_lens.expense ADD FOREIGN KEY (user_id) REFERENCES spend_lens.user (id);

ALTER TABLE spend_lens.expense ADD FOREIGN KEY (created_by) REFERENCES spend_lens.user (id);

ALTER TABLE spend_lens.expense ADD FOREIGN KEY (last_modified_by) REFERENCES spend_lens.user (id);

ALTER TABLE spend_lens.time_deposit ADD FOREIGN KEY (bank_id) REFERENCES spend_lens.bank (id);

ALTER TABLE spend_lens.time_deposit ADD FOREIGN KEY (user_id) REFERENCES spend_lens.user (id);

ALTER TABLE spend_lens.time_deposit ADD FOREIGN KEY (created_by) REFERENCES spend_lens.user (id);

ALTER TABLE spend_lens.time_deposit ADD FOREIGN KEY (last_modified_by) REFERENCES spend_lens.user (id);
