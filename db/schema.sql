create table cars (
    id serial primary key,
    name varchar(100),
    engine_id int not null unique references engines(id),
    body_id int not null references bodies(id),
    photo_id int references photos(id),
    driver_id int unique references drivers(id),
);

create table engines (
    id serial primary key,
    name varchar(50)
);

create table bodies (
    id serial primary key,
    name varchar(50)
);

create table photos (
    id serial primary key,
    url varchar(255)
);

create table drivers (
    id serial primary key,
    name varchar(100)
);

create table ads (
    id serial primary key,
    description varchar(1000),
    is_sold boolean,
    price decimal(8, 2),
    car_id not null int references cars(id),
    author_id int not null references drivers(id)
);