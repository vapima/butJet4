INSERT INTO "users" ("name", "email", "hash_password", "role", "state")
VALUES ('many_plans_user', 'plans@us2er.io', 'test_password', 'ROLE_USER', 'ACTIVE');
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan1', 13200, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan2', 1042340, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan3', 14300, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan4', 10340, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan5', 10540, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan6', 17600, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan7', 103430, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan8', 10430, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan9', 13300, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan10', 10430, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan11', 105460, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan12', 10760, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan13', 17600, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan14', 10540, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan15', 104530, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan16', 10650, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan17', 10760, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan18', 10540, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan19', 10430, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan20', 100656, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan21', 10750, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan22', 10640, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan23', 10540, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan24', 10650, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan25', 10530, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan26', 10430, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan27', 10000, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan28', 103230, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan29', 1043240, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
INSERT INTO "plans" ("name", "amount", "expiration_date", "user_id")
VALUES ('test_plan30', 10430, '2030-06-01', (SELECT id from users WHERE email = 'plans@us2er.io'));
