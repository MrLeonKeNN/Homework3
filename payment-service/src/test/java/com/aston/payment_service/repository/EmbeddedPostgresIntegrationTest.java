package com.aston.payment_service.repository;

import com.aston.payment_service.config.EmbeddedPostgresConfiguration;
import com.aston.payment_service.config.EmbeddedPostgresWithLiquibaseConfiguration;
import com.aston.payment_service.config.TestContainersInitializer;
import com.aston.payment_service.entity.Outbox;
import com.aston.payment_service.entity.enums.Aggregate;
import com.aston.payment_service.entity.enums.OutboxStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@DataJpaTest(properties = {"spring.jpa.hibernate.ddl-auto=none"})
//@ExtendWith(EmbeddedPostgresConfiguration.EmbeddedPostgresExtension.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ContextConfiguration(classes = {EmbeddedPostgresWithLiquibaseConfiguration.class})
//@DataJpaTest
//@AutoConfigureEmbeddedDatabase(provider = ZONKY)

//@DataJpaTest
//@ExtendWith(TestContainersInitializer.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ContextConfiguration(initializers = TestContainersInitializer.class)
@SpringBootTest
@Testcontainers
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class EmbeddedPostgresIntegrationTest {

    @Autowired
    private OutboxRepository outboxRepository;

    @Container
    public static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13-alpine")
            .withDatabaseName("integration_test_db")
            .withUsername("test")
            .withPassword("test");

    // С помощью @DynamicPropertySource регистрируем свойства для Spring Boot, чтобы DataSource использовал контейнер
    @DynamicPropertySource
    static void registerDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @Test
    void givenEmbeddedPostgres_whenSavePerson_thenSavedEntityShouldBeReturnedWithExpectedFields() {

        Outbox outbox = Outbox.builder()
                .aggregate(Aggregate.PAYMENT)
                .aggregateId(UUID.randomUUID())
                .createdDate(Instant.now())
                .status(OutboxStatus.SEND)
                .build();

//        Person person = new Person();
//        person.setName("New user");
//
        Outbox savedOutbox = outboxRepository.save(outbox);
        assertNotNull(savedOutbox.getId());
        assertEquals(outbox.getStatus(), savedOutbox.getStatus());
    }
}
