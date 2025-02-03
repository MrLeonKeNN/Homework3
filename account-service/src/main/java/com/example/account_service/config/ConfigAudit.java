package com.example.account_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class ConfigAudit {
//    @Bean
//    public AuditorAware<AuditableUser> auditorProvider() {
//        return new AuditorAwareImpl();
//    }
}
