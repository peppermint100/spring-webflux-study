DROP TABLE IF EXISTS application_user;

create table application_user (
    id int primary key,
    username varchar(255),
    email varchar(255),
    passport varchar(255)
);