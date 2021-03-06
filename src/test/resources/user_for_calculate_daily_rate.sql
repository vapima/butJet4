INSERT INTO "users" ("name", "email", "hash_password", "role", "state")
VALUES ('test_user', 'test@user.io', 'test_password', 'ROLE_USER', 'ACTIVE');
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan', -1000, current_date, (SELECT id from users WHERE email = 'test@user.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan', -1000, current_date + 5435346, (SELECT id from users WHERE email = 'test@user.io'));
INSERT INTO "accounts" ("name", "is_active", "user_id")
VALUES ('test_account', true, (SELECT id from users WHERE email = 'test@user.io'));
INSERT INTO "account_records" ("description", "amount", "date_time", "account_id")
VALUES ('test_accountrecord', -1000, current_timestamp - interval '100 day',
        (SELECT id from accounts WHERE name = 'test_account'));
INSERT INTO "account_records" ("description", "amount", "date_time", "account_id")
VALUES ('test_accountrecord', 2500, current_timestamp - interval '1 day',
        (SELECT id from accounts WHERE name = 'test_account'));
INSERT INTO "account_records" ("description", "amount", "date_time", "account_id")
VALUES ('test_accountrecord', -3000, current_timestamp - interval '5 day',
        (SELECT id from accounts WHERE name = 'test_account'));
INSERT INTO "accounts" ("name", "is_active", "user_id")
VALUES ('test_account2', false, (SELECT id from users WHERE email = 'test@user.io'));
INSERT INTO "account_records" ("description", "amount", "date_time", "account_id")
VALUES ('test_accountrecord', 1000, current_timestamp - interval '3 day',
        (SELECT id from accounts WHERE name = 'test_account2'));
INSERT INTO "account_records" ("description", "amount", "date_time", "account_id")
VALUES ('test_accountrecord', 2000, current_timestamp - interval '1 day',
        (SELECT id from accounts WHERE name = 'test_account2'));
INSERT INTO "account_records" ("description", "amount", "date_time", "account_id")
VALUES ('test_accountrecord', 3000, current_timestamp - interval '2 day',
        (SELECT id from accounts WHERE name = 'test_account2'));
INSERT INTO "accounts" ("name", "is_active", "user_id")
VALUES ('test_account3', true, (SELECT id from users WHERE email = 'test@user.io'));
INSERT INTO "account_records" ("description", "amount", "date_time", "account_id")
VALUES ('test_accountrecord', 1000, current_timestamp - interval '1000 day',
        (SELECT id from accounts WHERE name = 'test_account3'));
INSERT INTO "account_records" ("description", "amount", "date_time", "account_id")
VALUES ('test_accountrecord', 2000, current_timestamp - interval '100 day',
        (SELECT id from accounts WHERE name = 'test_account3'));
INSERT INTO "account_records" ("description", "amount", "date_time", "account_id")
VALUES ('test_accountrecord', 3000, current_timestamp - interval '101 day',
        (SELECT id from accounts WHERE name = 'test_account3'));
INSERT INTO "users" ("name", "email", "hash_password", "role", "state")
VALUES ('1test_user', '1test@user.io', '1test_password', 'ROLE_USER', 'ACTIVE');
INSERT INTO "accounts" ("name", "is_active", "user_id")
VALUES ('1test_account2', true, (SELECT id from users WHERE email = '1test@user.io'));
INSERT INTO "account_records" ("description", "amount", "date_time", "account_id")
VALUES ('test_accountrecord', -233, current_timestamp - interval '1 day',
        (SELECT id from accounts WHERE name = '1test_account2'));
INSERT INTO "account_records" ("description", "amount", "date_time", "account_id")
VALUES ('test_accountrecord', 1000000, current_timestamp - interval '2 day',
        (SELECT id from accounts WHERE name = '1test_account2'));
INSERT INTO "account_records" ("description", "amount", "date_time", "account_id")
VALUES ('test_accountrecord', 40200, current_timestamp - interval '1 day',
        (SELECT id from accounts WHERE name = '1test_account2'));
