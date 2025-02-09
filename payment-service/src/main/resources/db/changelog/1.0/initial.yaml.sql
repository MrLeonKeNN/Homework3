-- liquibase formatted sql

-- changeset ilyan:1739011789774-1
CREATE SEQUENCE IF NOT EXISTS outbox_seq START WITH 1 INCREMENT BY 50;

-- changeset ilyan:1739011789774-2
CREATE TABLE addresses
(
    id       UUID NOT NULL,
    country  VARCHAR(30),
    region   VARCHAR(60),
    city     VARCHAR(60),
    street   VARCHAR(60),
    house    VARCHAR(4),
    building VARCHAR(3),
    office   VARCHAR(4),
    postcode VARCHAR(10),
    CONSTRAINT pk_addresses PRIMARY KEY (id)
);

-- changeset ilyan:1739011789774-3
CREATE TABLE auto_payment_field
(
    id               UUID         NOT NULL,
    field_id         UUID         NOT NULL,
    auto_payments_id UUID         NOT NULL,
    value            VARCHAR(255) NOT NULL,
    CONSTRAINT pk_autopaymentfield PRIMARY KEY (id)
);

-- changeset ilyan:1739011789774-4
CREATE TABLE auto_payments
(
    id                    UUID         NOT NULL,
    client_id             UUID         NOT NULL,
    service_id            UUID         NOT NULL,
    sender_account_number VARCHAR(20),
    sender_card_number    VARCHAR(16),
    amount                DECIMAL,
    currency              VARCHAR(255),
    start_date            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_date              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    periodicity           VARCHAR(100),
    last_payment_date     TIMESTAMP WITHOUT TIME ZONE,
    next_payment_date     TIMESTAMP WITHOUT TIME ZONE,
    is_active             BOOLEAN      NOT NULL,
    comment               VARCHAR(255),
    measured_time_zone    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_autopayments PRIMARY KEY (id)
);

-- changeset ilyan:1739011789774-5
CREATE TABLE entities
(
    id             UUID NOT NULL,
    client_id      UUID,
    addresses_id   UUID,
    name           VARCHAR(50),
    account_number VARCHAR(20),
    kpp            VARCHAR(9),
    ogrn           VARCHAR(13),
    tin            VARCHAR(10),
    currencies     VARCHAR(3),
    status         VARCHAR(10),
    CONSTRAINT pk_entities PRIMARY KEY (id)
);

-- changeset ilyan:1739011789774-6
CREATE TABLE entities_services
(
    id         UUID NOT NULL,
    entity_id  UUID,
    service_id UUID,
    CONSTRAINT pk_entitiesservices PRIMARY KEY (id)
);

-- changeset ilyan:1739011789774-7
CREATE TABLE field
(
    id          UUID NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_field PRIMARY KEY (id)
);

-- changeset ilyan:1739011789774-8
CREATE TABLE outbox
(
    id                 BIGINT       NOT NULL,
    aggregate          VARCHAR(255),
    payload            JSONB,
    aggregate_id       UUID,
    status             VARCHAR(255) NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_outbox PRIMARY KEY (id)
);

-- changeset ilyan:1739011789774-9
CREATE TABLE payment
(
    id                    UUID NOT NULL,
    client_id             UUID,
    service_id            UUID,
    sender_account_number VARCHAR(20),
    sender_card_number    VARCHAR(16),
    amount                DECIMAL,
    currency              VARCHAR(255),
    start_date            TIMESTAMP WITHOUT TIME ZONE,
    is_active             BOOLEAN,
    status                SMALLINT,
    comment               VARCHAR(255),
    payment_id            UUID,
    CONSTRAINT pk_payment PRIMARY KEY (id)
);

-- changeset ilyan:1739011789774-10
CREATE TABLE payment_field
(
    id         UUID NOT NULL,
    field_id   UUID,
    payment_id UUID,
    value      VARCHAR(255),
    CONSTRAINT pk_paymentfield PRIMARY KEY (id)
);

-- changeset ilyan:1739011789774-11
CREATE TABLE service_entity
(
    id       UUID NOT NULL,
    name     VARCHAR(255),
    category VARCHAR(255),
    code     VARCHAR(5),
    CONSTRAINT pk_serviceentity PRIMARY KEY (id)
);

-- changeset ilyan:1739011789774-12
CREATE TABLE service_field
(
    id         UUID NOT NULL,
    field_id   UUID,
    service_id UUID,
    CONSTRAINT pk_servicefield PRIMARY KEY (id)
);

-- changeset ilyan:1738621864438-25
CREATE TABLE shedlock
(
    name       VARCHAR(64),
    lock_until TIMESTAMP(3) NULL,
    locked_at  TIMESTAMP(3) NULL,
    locked_by  VARCHAR(255),
    PRIMARY KEY (name)
)

-- changeset ilyan:1739011789774-13
ALTER TABLE auto_payment_field
    ADD CONSTRAINT FK_AUTOPAYMENTFIELD_ON_AUTO_PAYMENTS FOREIGN KEY (auto_payments_id) REFERENCES auto_payments (id);

-- changeset ilyan:1739011789774-14
ALTER TABLE auto_payment_field
    ADD CONSTRAINT FK_AUTOPAYMENTFIELD_ON_FIELD FOREIGN KEY (field_id) REFERENCES field (id);

-- changeset ilyan:1739011789774-15
ALTER TABLE auto_payments
    ADD CONSTRAINT FK_AUTOPAYMENTS_ON_SERVICE FOREIGN KEY (service_id) REFERENCES service_entity (id);

-- changeset ilyan:1739011789774-16
ALTER TABLE entities_services
    ADD CONSTRAINT FK_ENTITIESSERVICES_ON_ENTITY FOREIGN KEY (entity_id) REFERENCES entities (id);

-- changeset ilyan:1739011789774-17
ALTER TABLE entities_services
    ADD CONSTRAINT FK_ENTITIESSERVICES_ON_SERVICE FOREIGN KEY (service_id) REFERENCES service_entity (id);

-- changeset ilyan:1739011789774-18
ALTER TABLE entities
    ADD CONSTRAINT FK_ENTITIES_ON_ADDRESSES FOREIGN KEY (addresses_id) REFERENCES addresses (id);

-- changeset ilyan:1739011789774-19
ALTER TABLE payment_field
    ADD CONSTRAINT FK_PAYMENTFIELD_ON_FIELD FOREIGN KEY (field_id) REFERENCES field (id);

-- changeset ilyan:1739011789774-20
ALTER TABLE payment_field
    ADD CONSTRAINT FK_PAYMENTFIELD_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES payment (id);

-- changeset ilyan:1739011789774-21
ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES auto_payments (id);

-- changeset ilyan:1739011789774-22
ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_SERVICE FOREIGN KEY (service_id) REFERENCES service_entity (id);

-- changeset ilyan:1739011789774-23
ALTER TABLE service_field
    ADD CONSTRAINT FK_SERVICEFIELD_ON_FIELD FOREIGN KEY (field_id) REFERENCES field (id);

-- changeset ilyan:1739011789774-24
ALTER TABLE service_field
    ADD CONSTRAINT FK_SERVICEFIELD_ON_SERVICE FOREIGN KEY (service_id) REFERENCES service_entity (id);

