INSERT INTO "users" ("name", "email", "hash_password", "role", "state")
VALUES ('test', 'test@test.tt', '$2a$10$ISmet2jVjPpUMp7xVRasTe1q4x0F5H3y4dffJ0/yX4iOQ9JFQxdzq', 'ROLE_ADMIN', 'ACTIVE');
INSERT INTO "accounts" ("name", "is_active", "user_id")
VALUES ('test_account', true, (SELECT id from users WHERE email = 'test@test.tt'));