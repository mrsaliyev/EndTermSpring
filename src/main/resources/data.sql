Insert into users(id , fullName , login , password, blocked) values
    (nextval('users_id_seq') , 'admin' , 'admin' , '$2y$12$eNW4LjmMs0SjEqGV8c49tOyQ2JALrb5HSVsmbIKoJvix6OMcy5IWK' , false);

Insert into user_roles (user_id , role_id) values ((select id from users where login='admin') , (select id from roles where name='ROLE_ADMIN'));

Insert into roles(id, name) values (nextval('roles_id_seq'), 'ROLE_OPERATOR');
Insert into roles(id, name) values (nextval('roles_id_seq'), 'ROLE_ADMIN');

