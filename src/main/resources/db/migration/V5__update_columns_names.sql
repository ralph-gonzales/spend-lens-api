ALTER TABLE spend_lens.cash_flows
      RENAME COLUMN date TO cash_flow_date;

ALTER TABLE spend_lens.cash_flows
      RENAME COLUMN name TO cash_flow_name;

ALTER TABLE spend_lens.assets
      RENAME COLUMN date TO asset_date;

ALTER TABLE spend_lens.expenses
      RENAME COLUMN date TO expense_date;

ALTER TABLE spend_lens.expenses
      RENAME COLUMN name TO expense_name;

DROP INDEX IF EXISTS spend_lens.idx_cash_flows_user_date;

CREATE INDEX idx_cash_flows_user_cash_flow_date ON spend_lens.cash_flows(user_id,cash_flow_date);

DROP INDEX IF EXISTS spend_lens.idx_assets_user_date;

CREATE INDEX idx_assets_user_asset_date ON spend_lens.assets(user_id, asset_date);

DROP INDEX IF EXISTS spend_lens.idx_expenses_user_date;

CREATE INDEX idx_expenses_user_expense_date ON spend_lens.expenses(user_id, expense_date);
