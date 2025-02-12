package com.aston.payment_service.config;

import com.opentable.db.postgres.embedded.LiquibasePreparer;
import com.opentable.db.postgres.embedded.PreparedDbProvider;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableJpaRepositories(basePackages = "com.aston.payment_service.repository")
@EntityScan(basePackages = "com.aston.payment_service.entity")
public class EmbeddedPostgresWithLiquibaseConfiguration {

    @Bean
    public DataSource dataSource() throws SQLException {
        return PreparedDbProvider
                .forPreparer(LiquibasePreparer.forClasspathLocation("db/changelog/db.changelog-master.yaml"))
                .createDataSource();
    }

}
