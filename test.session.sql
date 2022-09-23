INSERT INTO users (username, password, enabled) VALUES ('user','',TRUE) ON CONFLICT(username) DO NOTHING ;
INSERT INTO authorities (username, authority) VALUES ('user','ROLE_user') ON CONFLICT(username) DO NOTHING;

drop TABLE users,authorities;

