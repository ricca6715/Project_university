package it.unisalento.se.saw.configurations;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("it.unisalento.se.saw.repositories")
@PropertySource("classpath:application.properties")
public class JPAConfigurations {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HIBERNATE_DDL = "hibernate.hbm2ddl.validate";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN ="entitymanager.packages.to.scan";
 private static final String PROPERTY_NAME_HIBERNATE_ENABLE_LAZY_LOAD_NO_TRANS ="hibernate.enable_lazy_load_no_trans";
	
	@Resource
	Environment environment;
	
	@Bean
	public DataSource dataSource() {
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
	dataSource.setUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
	dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
	dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
	return dataSource;
	}
		
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = 
		new LocalContainerEntityManagerFactoryBean();
	entityManagerFactoryBean.setDataSource(dataSource());
	entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
	entityManagerFactoryBean.setPackagesToScan
		(environment.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
	entityManagerFactoryBean.setJpaProperties(hibProperties());
	return entityManagerFactoryBean;
	}
	
	private Properties hibProperties() {
		 Properties properties = new Properties();
		 properties.put(
			PROPERTY_NAME_HIBERNATE_DIALECT,
		          environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		 properties.put(
			PROPERTY_NAME_HIBERNATE_SHOW_SQL, 
			environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		 properties.put(PROPERTY_NAME_HIBERNATE_DDL, 
			environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		 properties.put(PROPERTY_NAME_HIBERNATE_ENABLE_LAZY_LOAD_NO_TRANS, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_ENABLE_LAZY_LOAD_NO_TRANS));
		 return properties;

		 }
		 
		 @Bean
		 public JpaTransactionManager transactionManager() {
		 JpaTransactionManager transactionManager = new JpaTransactionManager();
		 transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		 return transactionManager;
		 }


}