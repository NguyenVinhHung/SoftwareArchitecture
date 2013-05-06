drop table if exists Account;
create table if not exists Account(
    Username text primary key,
    Password text
);