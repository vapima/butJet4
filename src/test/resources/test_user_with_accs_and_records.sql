INSERT INTO "users" ("name", "email", "hash_password", "role", "state")
VALUES ('test', 'test@test.tt', '$2a$10$ISmet2jVjPpUMp7xVRasTe1q4x0F5H3y4dffJ0/yX4iOQ9JFQxdzq', 'ROLE_ADMIN', 'ACTIVE');
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan', 100, '2030-06-01', (SELECT id from users WHERE email = 'test@test.tt'));
INSERT INTO "accounts" ("name", "is_active", "user_id")
VALUES ('test_account', true, (SELECT id from users WHERE email = 'test@test.tt'));
INSERT INTO "account_records" ("description", "amount", "date_time", "account_id")
VALUES ('test_accountrecord', -200, '2016-06-22 19:10:25-07', (SELECT id from accounts WHERE name = 'test_account'));