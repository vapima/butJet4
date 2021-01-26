INSERT INTO "users" ("name","email","hash_password","role","state") VALUES ('test_user','test@user.io','test_password','ROLE_USER','ACTIVE');
INSERT INTO "plans" ("name","amount","expiration_date","user_id") VALUES ('test_plan',100,'2030-06-01',(SELECT id from users WHERE email='test@user.io'));
INSERT INTO "plans" ("name","amount","expiration_date","user_id") VALUES ('test_plan',100332,'2021-01-030',(SELECT id from users WHERE email='test@user.io'));
INSERT INTO "accounts" ("name","is_active", "user_id") VALUES ('test_account',true,(SELECT id from users WHERE email='test@user.io'));
INSERT INTO "account_records" ("description","amount","date_time","account_id") VALUES ('test_accountrecord',-200,'2016-06-22 19:10:25-07',(SELECT id from accounts WHERE name='test_account'));
INSERT INTO "account_records" ("description","amount","date_time","account_id") VALUES ('test_accountrecord',32200,'2020-06-22 19:10:25-07',(SELECT id from accounts WHERE name='test_account'));
INSERT INTO "account_records" ("description","amount","date_time","account_id") VALUES ('test_accountrecord',40200,'2015-06-22 19:10:25-07',(SELECT id from accounts WHERE name='test_account'));