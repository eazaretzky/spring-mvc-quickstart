package ar.com.orangebit.springMvcQuickstart.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableJpaRepositories(basePackages = {"ar.com.orangebit.springMvcQuickstart.repository"})
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class AppConfiguration {

  private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/view/";
  private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

  private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
  private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
  private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
  private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

  private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
  private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
  private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY =
      "hibernate.ejb.naming_strategy";
  private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
  private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL = "hibernate.hbm2ddl.auto";
  private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN =
      "entitymanager.packages.to.scan";

  private static final String PROPERTY_NAME_MESSAGESOURCE_BASENAME = "message.source.basename";
  private static final String PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE =
      "message.source.use.code.as.default.message";

  @Resource
  private Environment environment;

  @Bean
  public DataSource dataSource() {
    BoneCPDataSource dataSource = new BoneCPDataSource();

    dataSource.setDriverClass(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
    dataSource.setJdbcUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
    dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
    dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory()
      throws ClassNotFoundException {
    LocalContainerEntityManagerFactoryBean entityManagerFactory =
        new LocalContainerEntityManagerFactoryBean();

    entityManagerFactory.setDataSource(dataSource());
    entityManagerFactory.setPackagesToScan(environment
        .getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
    entityManagerFactory.setPersistenceProviderClass(HibernatePersistence.class);

    Properties jpaProterties = new Properties();
    jpaProterties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
        environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
    jpaProterties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL,
        environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
    jpaProterties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY,
        environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
    jpaProterties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
        environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
    jpaProterties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL,
        environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL));
    entityManagerFactory.setJpaProperties(jpaProterties);

    return entityManagerFactory;
  }

  @Bean
  public JpaTransactionManager transactionManager() throws ClassNotFoundException {
    JpaTransactionManager transactionManager = new JpaTransactionManager();

    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

    return transactionManager;
  }

  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    messageSource
        .setBasename(environment.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_BASENAME));
    messageSource.setUseCodeAsDefaultMessage(Boolean.parseBoolean(environment
        .getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE)));

    return messageSource;
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
    viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);

    return viewResolver;
  }

}
