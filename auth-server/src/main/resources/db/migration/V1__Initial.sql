CREATE TABLE IF NOT EXISTS app_client_entity
(
    id serial NOT NULL,
    CONSTRAINT app_client_entity_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS app_granted_authority_entity
(
    id serial,
    authority text,
    user_id bigint,
    CONSTRAINT app_granted_authority_entity_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_entity
(
    id serial,
    account_non_expired boolean NOT NULL,
    account_non_locked boolean NOT NULL,
    credentials_non_expired boolean NOT NULL,
    enabled boolean NOT NULL,
    password text,
    telephone text,
    username text,
    CONSTRAINT user_entity_pkey PRIMARY KEY (id)
);