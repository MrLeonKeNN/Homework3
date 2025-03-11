-- liquibase formatted sql

-- changeset ilyan:1739011789776 - 2
CREATE EXTENSION IF NOT EXISTS pgcrypto;


-- changeset ilyan:1739011789777 - 3
CREATE TABLE card_application
(
    id                   UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    product_id           UUID                                        NOT NULL,
    payment_system       VARCHAR(255)                                NOT NULL,
    currency             VARCHAR(50)                                 NOT NULL,
    application_status   VARCHAR(50)                                 NOT NULL,
    first_name           VARCHAR(100)                                NOT NULL,
    last_name            VARCHAR(100)                                NOT NULL,
    patronymic           VARCHAR(100),
    mobile_phone         VARCHAR(20)                                 NOT NULL,
    email                VARCHAR(255)                                NOT NULL,
    birth_date           TIMESTAMP WITH TIME ZONE                    NOT NULL,
    citizenship          VARCHAR(100)                                NOT NULL,
    card_delivery_method VARCHAR(100)                                NOT NULL,
    client_address       TEXT                                        NOT NULL,
    code                 INT                                         NOT NULL
);

-- changeset ilyan:1739011789778 - 4
CREATE TABLE card_products
(
    id             UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    name           VARCHAR(255)                                NOT NULL,
    description    VARCHAR(255),
    termId         UUID,
    limitationId   UUID,
    status         VARCHAR(50)                                 NOT NULL,
    currencies     VARCHAR(255),
    validityPeriod VARCHAR(255),
    createdAt      TIMESTAMP WITH TIME ZONE                    NOT NULL,
    updatedAt      TIMESTAMP WITH TIME ZONE,
    level          VARCHAR(50)                                 NOT NULL,
    isVirtual      BOOLEAN                                     NOT NULL,
    paymentSystems VARCHAR(255),
    cardType       VARCHAR(50)                                 NOT NULL
);

-- changeset ilyan:1739011789779 - 5
CREATE TABLE debit_cards
(
    id                      UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    account_id              VARCHAR(255)                                NOT NULL,
    client_id               VARCHAR(255)                                NOT NULL,
    product_id              VARCHAR(255)                                NOT NULL,
    secure_data_id          VARCHAR(255)                                NOT NULL,
    first_name              VARCHAR(255)                                NOT NULL,
    last_name               VARCHAR(255)                                NOT NULL,
    status                  VARCHAR(50)                                 NOT NULL,
    created_at              TIMESTAMP WITH TIME ZONE                    NOT NULL,
    expiration_date         TIMESTAMP WITH TIME ZONE                    NOT NULL,
    closed_at               TIMESTAMP WITH TIME ZONE,
    push_per_day            BOOLEAN                                     NOT NULL,
    amount_per_day          DECIMAL(10, 2)                              NOT NULL,
    cash_withdrawal_per_day DECIMAL(10, 2)                              NOT NULL,
    payment_system          VARCHAR(50)                                 NOT NULL,
    currency                VARCHAR(10)                                 NOT NULL,
    master_card             BOOLEAN                                     NOT NULL
);

-- changeset ilyan:17390117812774 - 6
CREATE TABLE limitations
(
    id                      UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    amount_per_day          DECIMAL(10, 2)                              NOT NULL, -- Для денежных значений
    amount_per_month        DECIMAL(10, 2)                              NOT NULL, -- Для денежных значений
    amount_per_operation    BIGINT                                      NOT NULL, -- Для больших значений
    cash_withdrawal_per_day BIGINT                                      NOT NULL, -- Для больших значений
    operation_per_day       INT                                         NOT NULL, -- Для целых чисел
    operation_per_month     INT                                         NOT NULL  -- Для целых чисел
);

-- changeset ilyan:173903211789774 - 7
CREATE TABLE secure_data
(
    id                   UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    pin_code             INT                                         NOT NULL,
    cvc_code             INT                                         NOT NULL,
    first_twelve_numbers INT                                         NOT NULL,
    last_four_numbers    INT                                         NOT NULL
);

-- changeset ilyan:17439011789774 - 8
CREATE TABLE terms
(
    id                         UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    cost_per_month             DECIMAL(10, 2)                              NOT NULL,
    transfer_commission        DECIMAL(10, 2)                              NOT NULL,
    cash_withdrawal_commission DECIMAL(10, 2)                              NOT NULL
);