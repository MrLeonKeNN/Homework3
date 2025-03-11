package com.aston.payment_service.config;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class TestContainersInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext>, AfterAllCallback {

    private static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(
            "postgres:14.1")
            .withDatabaseName("postgres-service")
            .withUsername("admin")
            .withPassword("admin");


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        postgreSQLContainer.start();

        TestPropertyValues.of(
                        "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                        "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                        "spring.datasource.password=" + postgreSQLContainer.getPassword()
                )
                .applyTo(applicationContext.getEnvironment());
        // Получаем соединение
        try (Connection connection = postgreSQLContainer.createConnection("")) {

            // Пытаемся создать схему. Для PostgreSQL можно использовать IF NOT EXISTS,
            // чтобы избежать ошибки, если схема уже создана.
            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE SCHEMA IF NOT EXISTS payment_service");
            }

            // Если требуется, можно установить схему по умолчанию для сессии
            try (Statement statement = connection.createStatement()) {
                statement.execute("SET search_path TO payment_service");
            }

            // Запускаем миграции Liquibase
            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase(
                    "db/changelog/db.changelog-master.xml",
                    new ClassLoaderResourceAccessor(),
                    database
            );
            liquibase.update("");
        } catch (SQLException | LiquibaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        if (postgreSQLContainer == null) {
            return;
        }
        postgreSQLContainer.close();
    }
}
