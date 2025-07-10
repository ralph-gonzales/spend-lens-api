ALTER spendlens.cash_flows
      RENAME COLUMN date TO cash_flow_date;

ALTER spendlens.cash_flows
      RENAME COLUMN name TO cash_flow_name;

ALTER spendlens.assets
      RENAME COLUMN date TO asset_date;

ALTER spendlens.expenses
      RENAME COLUMN date TO expense_date;

ALTER spendlens.expenses
      RENAME COLUMN name TO expense_name;

DROP INDEX IF EXISTS spendlens.idx_cash_flows_user_date;

CREATE INDEX idx_cash_flows_user_cash_flow_date ON spendlens.cash_flows;

DROP INDEX IF EXISTS spendlens.idx_assets_user_date;

CREATE INDEX idx_assets_user_asset_date ON spendlens.assets;

DROP INDEX IF EXISTS spendlens.idx_expenses_user_date;

CREATE INDEX idx_expenses_user_expense_date ON spendlens.expenses;
