create table orders
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    order_number              text not null,
    status                    text,
    amount                    double,
    qty                       int,
    primary key (id)
);

