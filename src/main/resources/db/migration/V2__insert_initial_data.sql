INSERT INTO spend_lens.role_type (code,name) VALUES
    ('USER','user'),
    ('ADMIN','admin');

INSERT INTO spend_lens.app_user (
    username, first_name, last_name, email, role_type, created_by, last_modified_by) VALUES
    ('Administrator', 'Ralph', 'Gonzales', 'rmpgonzales@gmail.com', 'ADMIN', 1, 1);

INSERT INTO spend_lens.cash_flow_type_template (name, created_by, last_modified_by) VALUES
    ('SALARY', 1, 1),
    ('SAVINGS_INTEREST', 1, 1),
    ('DEBT', 1 ,1),
    ('CASHBACK', 1, 1);

INSERT INTO spend_lens.bank (name, created_by, last_modified_by) VALUES
    ('BDO', 1, 1),
    ('Bank of the Philippine Islands', 1, 1),
    ('GoTyme Bank', 1, 1),
    ('Tonik Bank', 1, 1),
    ('Maya Bank', 1, 1),
    ('SeaBank Philippines', 1, 1),
    ('BPI Direct BanKo', 1, 1),
    ('CIMB Bank', 1, 1),
    ('Netbank', 1, 1);

INSERT INTO spend_lens.asset_type (code,name) VALUES
    ('CASH', 'Cash'),
    ('BANK', 'Bank');

INSERT INTO spend_lens.expense_category_type (code,name) VALUES
    ('ESSENTIAL_EXPENSE', 'Essential Expense'),
    ('PERSONAL_EXPENSE', 'Personal Expense');

INSERT INTO spend_lens.expense_category_template (name, created_by, last_modified_by) VALUES
    ('Food', 1, 1),
    ('Monthly Dues', 1, 1),
    ('Transportation', 1, 1),
    ('Personal Property', 1, 1),
    ('Others', 1, 1);

INSERT INTO spend_lens.payment_type (code,name) VALUES
    ('CASH', 'Cash'),
    ('CASHLESS', 'Cashless');