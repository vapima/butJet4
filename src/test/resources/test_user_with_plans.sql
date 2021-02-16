INSERT INTO "users" ("name", "email", "hash_password", "role", "state")
VALUES ('test', 'test@test.tt', '$2a$10$ISmet2jVjPpUMp7xVRasTe1q4x0F5H3y4dffJ0/yX4iOQ9JFQxdzq', 'ROLE_ADMIN', 'ACTIVE');
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan', 100, '2030-06-01', (SELECT id from users WHERE email = 'test@test.tt'));