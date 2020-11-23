CREATE TABLE IF NOT EXISTS auth.users
(
    id serial,
    account_non_expired boolean NOT NULL,
    account_non_locked boolean NOT NULL,
    credentials_non_expired boolean NOT NULL,
    enabled boolean NOT NULL,
    password text NOT NULL,
    telephone text,
    username text NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    unique(username)
);

CREATE TABLE IF NOT EXISTS auth.oauth2_client
(
    id serial,
    user_id bigint,
    client_name text,
    description text,
    client_id text,
    client_secret text,
    scope_list text,
    authorized_grant_types_list text,
    registered_redirect_uris text,
    access_token_validity_seconds int,
    refresh_token_validity_seconds int,
    auto_approve boolean,
    CONSTRAINT app_client_pkey PRIMARY KEY (id),
    CONSTRAINT app_client_fkey FOREIGN KEY (user_id) REFERENCES auth.users (id),
    unique(user_id, client_name)
);

CREATE TABLE IF NOT EXISTS auth.oauth2_granted_authority
(
    id serial,
    authority text,
    user_id bigint,
    CONSTRAINT app_granted_authority_pkey PRIMARY KEY (id),
    CONSTRAINT app_granted_authority_fkey FOREIGN KEY (user_id) REFERENCES auth.users (id)
);

