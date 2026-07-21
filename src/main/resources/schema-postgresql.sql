DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT
ALL
ON SCHEMA public TO postgres;
GRANT ALL
ON SCHEMA public TO public;

CREATE TABLE "vpn_profile"
(
    "id"         uuid PRIMARY KEY,
    "user_id"    uuid,
    "created_at" timestamp,
    "updated_at" timestamp
);

CREATE TABLE "my_user"
(
    "id"         uuid PRIMARY KEY,
    "email"      varchar UNIQUE NOT NULL,
    "password"   vatchar        NOT NULL,
    "role"       varchar,
    "created_at" timestamp,
    "updated_at" timestamp
);

CREATE TABLE "vpn_tariff"
(
    "id"               uuid PRIMARY KEY,
    "varchar"          name,
    "is_trial"         bool,
    "is_enabled"       bool,
    "traffic_limit_gb" integer,
    "devices_limit"    integer,
    "price_rubles"     integer,
    "created_at"       timestamp,
    "updated_at"       timestamp,
);

CREATE TABLE "subscription"
(
    "id"               uuid PRIMARY KEY,
    "vpn_profile_id"   uuid,
    "created_at"       timestamp,
    "updated_at"       timestamp,
    "traffic_limit_gb" integer,
    "devices_limit"    integer,
    "price_rubles"     integer
);

ALTER TABLE "vpn_profile"
    ADD FOREIGN KEY ("user_id") REFERENCES "my_user" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "subscription"
    ADD FOREIGN KEY ("vpn_profile_id") REFERENCES "vpn_profile" ("id") DEFERRABLE INITIALLY IMMEDIATE;

CREATE UNIQUE INDEX uq_one_active_trial_tariff
    ON vpn_tariff (is_trial)
    WHERE is_trial = true AND is_enabled = true;