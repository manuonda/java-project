CREATE SEQUENCE order_id_seq start with 1  increment by 1;

CREATE TABLE ORDERS_TBL(
    id bigint default nextval('order_id_seq') not null,
    name text,
    int numeric,
    price numeric ,
    primary key(id)
);