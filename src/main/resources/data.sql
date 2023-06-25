INSERT INTO permissao (id, nome) VALUES (1, "ROLE_ADMIN");

INSERT INTO usuario (id, email, senha) VALUES (1, "admin@scholar.edu", "$2a$12$yj2MHvGZslkGDBQuLLs1eeR6gzKgZkFnr7uT3n9Rw/obDaN5TF8ry");

INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (1, 1);