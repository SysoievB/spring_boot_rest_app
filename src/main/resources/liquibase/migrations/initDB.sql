--liquibase formatted sql
--changeset sysoiev:1

create table if not exists orders
(
    id         bigint primary key auto_increment,
    order_name varchar(100)
);

create table if not exists accounts
(
    id     bigint primary key auto_increment,
    status enum ('ACTIVE', 'BANNED', 'DELETED') DEFAULT 'ACTIVE'
);

create table if not exists customers
(
    id         bigint primary key auto_increment,
    name       varchar(155),
    surname    varchar(155),
    order_id   bigint,
    account_id bigint,
    foreign key (order_id) references orders (id),
    foreign key (account_id) references accounts (id)
);

create table if not exists customers_orders
(
    customer_id bigint not null,
    order_id    bigint not null,
    unique (order_id),
    foreign key (customer_id) references customers (id) on update cascade on delete cascade,
    foreign key (order_id) references orders (id)
);