ALTER TABLE spendlens.assets
    RENAME COLUMN updated_by to last_modified_by,
    RENAME COLUMN updated_date to last_modified_date;

ALTER TABLE spendlens.users
    RENAME COLUMN updated_by to last_modified_by,
    RENAME COLUMN updated_date to last_modified_date;

ALTER TABLE spendlens.cash_flows
    RENAME COLUMN updated_by to last_modified_by,
    RENAME COLUMN updated_date to last_modified_date;

ALTER TABLE spendlens.expenses
    RENAME COLUMN updated_by to last_modified_by,
    RENAME COLUMN updated_date to last_modified_date;

ALTER TABLE spendlens.time_deposits
    RENAME COLUMN updated_by to last_modified_by,
    RENAME COLUMN updated_date to last_modified_date;