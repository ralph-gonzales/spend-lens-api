INSERT INTO spend_lens.role_type (code,name) VALUES
    ('USER','user'),
    ('ADMIN','admin');

INSERT INTO spend_lens.cash_flow_type (code,name) VALUES
    ('SALARY', 'Salary'),
    ('SAVINGS_INTEREST', 'Savings Interest'),
    ('DEBT','Debt'),
    ('CASHBACK', 'Cashback');

INSERT INTO spend_lens.bank (name) VALUES
    ('BDO'),
    ('Bank of the Philippine Islands'),
    ('GoTyme Bank'),
    ('Tonik Bank'),
    ('Maya Bank'),
    ('SeaBank Philippines'),
    ('BPI Direct BanKo'),
    ('CIMB Bank'),
    ('Netbank');

INSERT INTO spend_lens.asset_type (code,name) VALUES
    ('CASH', 'Cash'),
    ('BANK', 'Bank');

INSERT INTO spend_lens.expense_type (code,name) VALUES
    ('ESSENTIAL_EXPENSE', 'Essential Expense'),
    ('PERSONAL_EXPENSE', 'Personal Expense');

INSERT INTO spend_lens.expense_category (code,name) VALUES
    ('FOOD', 'Food'),
    ('MONTHLY_DUES', 'Monthly Dues'),
    ('TRANSPORTATION', 'Transportation'),
    ('PERSONAL_PROPERTY', 'Personal Property'),
    ('OTHERS', 'Others');

INSERT INTO spend_lens.payment_type (code,name) VALUES
    ('CASH', 'Cash'),
    ('CASHLESS', 'Cashless');