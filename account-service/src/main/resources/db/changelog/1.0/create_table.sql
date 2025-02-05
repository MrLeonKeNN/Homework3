--liquibase formatted sql
--changeset user:create_table

CREATE TABLE IF NOT EXISTS account
(
    id              UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
    client_id       UUID                     NOT NULL,
    department_id   UUID                     NOT NULL,
    account_number  VARCHAR(20)              NOT NULL,
    type_account    VARCHAR(10)              NOT NULL,
    account_balance NUMERIC(19, 4)           NOT NULL DEFAULT 0.0000,
    hold_balance    NUMERIC(19, 4)           NOT NULL DEFAULT 0.0000,
    status_name     VARCHAR(7)               NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    closed_at       TIMESTAMP WITH TIME ZONE,
    block_reason    TEXT,
    master_account  BOOLEAN                  NOT NULL DEFAULT FALSE,
    currency_name   VARCHAR(3)               NOT NULL,
    name_account    VARCHAR(50)              NOT NULL
);
