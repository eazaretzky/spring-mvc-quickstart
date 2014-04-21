package ar.com.orangebit.springMvcQuickstart.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories (basePackages = {"ar.com.orangebit.springMvcQuickstart.repository"})
@EnableTransactionManagement
@ComponentScan(basePackages = {"ar.com.orangebit.springMvcQuickStart"})
public class AppConfig {
}
