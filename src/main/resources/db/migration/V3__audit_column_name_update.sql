ALTER TABLE spend_lens.assets RENAME COLUMN updated_by TO last_modified_by;
ALTER TABLE spend_lens.assets RENAME COLUMN updated_at TO last_modified_date;

ALTER TABLE spend_lens.users RENAME COLUMN updated_by TO last_modified_by;
ALTER TABLE spend_lens.users RENAME COLUMN updated_date TO last_modified_date;

ALTER TABLE spend_lens.cash_flows RENAME COLUMN updated_by TO last_modified_by;
ALTER TABLE spend_lens.cash_flows RENAME COLUMN updated_at TO last_modified_date;

ALTER TABLE spend_lens.expenses RENAME COLUMN updated_by TO last_modified_by;
ALTER TABLE spend_lens.expenses RENAME COLUMN updated_at TO last_modified_date;

ALTER TABLE spend_lens.time_deposits RENAME COLUMN updated_by TO last_modified_by;
ALTER TABLE spend_lens.time_deposits RENAME COLUMN updated_at TO last_modified_date;