--liquibase formatted sql

-- changeset ilyan:1739011789776 - 2
CREATE
    EXTENSION IF NOT EXISTS pgcrypto;

-- ==============================================
-- Создание таблицы autotransfer
-- ==============================================
-- changeset author:1
CREATE TABLE autotransfer
(
    id                      UUID PRIMARY KEY                  DEFAULT gen_random_uuid() NOT NULL,
    name                    VARCHAR(100)             NOT NULL,
    sender_account_number   CHAR(20)                 NOT NULL,
    sender_card_number      CHAR(16)                 NOT NULL,
    sender_client_id        UUID                     NOT NULL,
    receiver_account_number CHAR(20)                 NOT NULL,
    receiver_card_number    CHAR(16)                 NOT NULL,
    amount                  DECIMAL(18, 2)           NOT NULL,
    currency                VARCHAR(3)               NOT NULL,
    start_date              TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date                TIMESTAMP WITH TIME ZONE NULL,
    last_transaction_date   TIMESTAMP WITH TIME ZONE NULL,
    frequency               VARCHAR(100)             NOT NULL,
    comment                 VARCHAR(255)             NULL,
    is_favorite             BOOLEAN                  NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_autotransfer_id ON autotransfer (id);

-- ==============================================
-- Создание таблицы transaction
-- ==============================================
-- changeset author:2
CREATE TABLE transaction
(
    id                      UUID PRIMARY KEY                  DEFAULT gen_random_uuid() NOT NULL,
    sender_account_number   CHAR(20)                 NOT NULL,
    sender_card_number      CHAR(16)                 NOT NULL,
    sender_client_id        UUID                     NOT NULL,
    receiver_account_number CHAR(20)                 NOT NULL,
    receiver_card_number    CHAR(16)                 NOT NULL,
    amount                  DECIMAL(18, 2)           NOT NULL,
    currency                VARCHAR(3)               NOT NULL,
    created_date            TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update_date        TIMESTAMP WITH TIME ZONE NULL,
    transfer_type_name      VARCHAR(100)             NOT NULL,
    status                  VARCHAR(12)              NOT NULL,
    comment                 VARCHAR(255)             NULL,
    is_favorite             BOOLEAN                  NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_transaction_id ON transaction (id);


-- ==============================================
-- Создание таблицы transfer_template
-- ==============================================
-- changeset author:3
CREATE TABLE transfer_template
(
    id                      UUID PRIMARY KEY                  DEFAULT gen_random_uuid() NOT NULL,
    name                    VARCHAR(100)             NOT NULL,
    sender_account_number   CHAR(20)                 NOT NULL,
    sender_card_number      CHAR(16)                 NOT NULL,
    sender_client_id        UUID                     NOT NULL,
    receiver_account_number CHAR(20)                 NOT NULL,
    receiver_card_number    CHAR(16)                 NOT NULL,
    amount                  DECIMAL(18, 2)           NOT NULL,
    currency                VARCHAR(3)               NOT NULL,
    created_date            TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_transaction_date   TIMESTAMP WITH TIME ZONE NULL,
    comment                 VARCHAR(255)             NULL,
    is_favorite             BOOLEAN                  NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_transfer_template_id ON transfer_template (id);

