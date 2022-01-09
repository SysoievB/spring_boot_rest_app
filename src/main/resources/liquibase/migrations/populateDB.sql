--liquibase formatted sql
--changeset sysoiev:2

-- populate orders
insert into orders
values (5, 'car'),
       (6, 'house'),
       (7, 'plane');

-- populate accounts
insert into accounts
values (1, 'ACTIVE'),
       (2, 'ACTIVE'),
       (3, 'ACTIVE');

-- populate customers
insert into customers
values (1, 'vasia', 'vasev', 1),
       (2, 'petia', 'sidorov', 2);

-- populate customers_orders
insert into customers_orders
values (1, 5),
       (1, 6),
       (2, 7);