drop table roles, user_roles, users;


create table users (
                       id int not null auto_increment primary key,
                       firstName varchar(255) not null ,
                       lastName varchar(255) not null ,
                       email varchar(255) not null ,
                       age int not null ,
                       password varchar(255) not null
);

create table roles (
                       id int not null auto_increment primary key ,
                       name varchar(255) not null
);

create table user_roles(
                           user_id int not null ,
                           role_id int not null,
                           foreign key (user_id) references users(id),
                           foreign key (role_id) references roles(id),

                           unique (user_id, role_id)

);

insert into users values (1, 'tom', 'jerry','j@mail.ru',23, 'gUuFwNo4zkMV+erdGtBlf5NunNgcELQuiCFJmCU4F+E=');


insert into roles values (1, 'ROLE_USER');
insert into roles values (2, 'ROLE_ADMIN');
insert into user_roles values (1, 2);


select * from users;
select * from roles;
select *from user_roles;