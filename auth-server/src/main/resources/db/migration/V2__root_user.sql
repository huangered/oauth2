DO $$
declare user_id integer;
begin
-- add root user
insert into auth.users(
	account_non_expired, account_non_locked, credentials_non_expired, enabled, password, telephone, username)
	values (true, true, true, true, '$2a$10$ZAe.OdsJShS1tqw/zQ7WGekkCCYt4akBP4ES8xhhNrk2X.8W43YL6','12345678','root') returning id into user_id;

-- add root user first oauth2 credential

insert into auth.oauth2_client (user_id,
                                  client_name,
                                  description,
                                  client_id,
                                  client_secret,
                                  scope_list,
                                  authorized_grant_types_list,
                                  registered_redirect_uris,
                                  access_token_validity_seconds,
                                  refresh_token_validity_seconds,
                                  auto_approve)
    values (user_id, 'default', 'default oauth2 client', 'abcd','defg','admin','token','https://www.google.com', 3600,3600, false);

--

end$$