create table car (
    id serial primary key,
    name varchar(100),
    engine_id int not null unique references engine(id),
    driver_id int unique references driver(id)
);

create table engine (
    id serial primary key,
    name varchar(50)
);

create table driver (
    id serial primary key,
    name varchar(100)
);