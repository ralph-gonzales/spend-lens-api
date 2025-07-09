ALTER spendlens.cash_flows
      RENAME COLUMN date TO cash_flow_date;

ALTER spendlens.assets
      RENAME COLUMN date TO asset_date;

ALTER spendlens.expenses
      RENAME COLUMN date TO expense_date;