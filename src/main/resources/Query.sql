CREATE TABLE users
(
    id    serial primary key ,
    card  varchar(16),
    pin   varchar(4),
    uname varchar(25),
    bal   numeric
);

CREATE TABLE transactions
(
    transid serial primary key,
    user_id int,
    amount  numeric,
    stat    varchar(20),
    bal     numeric
);

INSERT INTO users(card, pin, uname, bal)
VALUES ('5555555555554444', '1234', 'admin', 2000);

