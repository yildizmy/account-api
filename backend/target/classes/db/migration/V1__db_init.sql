CREATE TABLE IF NOT EXISTS account
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    balance     DECIMAL,
    customer_id BIGINT,
    CONSTRAINT pk_account PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS customer
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    name    VARCHAR(50)           NOT NULL,
    surname VARCHAR(50)           NOT NULL,
    email   VARCHAR(50)           NOT NULL,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS transaction
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    description VARCHAR(100),
    date        TIMESTAMP,
    account_id  BIGINT,
    CONSTRAINT pk_transaction PRIMARY KEY (id)
);

ALTER TABLE customer
    ADD CONSTRAINT uc_customer_email UNIQUE (email);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);