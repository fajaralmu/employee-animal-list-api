package com.fajar.employeedataapi.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.fajar.employeedataapi.entities.Employee;
import com.fajar.employeedataapi.entities.Role;
import com.fajar.employeedataapi.entities.Salary;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@PropertySource({ "classpath:application.properties" })
public class HibernateSessionConfig {
	private static SessionFactory factory;

	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String hibernateDialect;
	@Value("${spring.datasource.url}")
	private String datasourceUrl;
	@Value("${spring.datasource.username}")
	private String datasourceUsename;
	@Value("${spring.datasource.password}")
	private String datasourcePassword;
	@Value("${spring.datasource.driver.class}")
	private String datasourceDriverClass;
	@Value("${spring.jpa.show-sql:false}")
	private String jpaShowSql;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String hibernateDdlAuto;
	@Value("${spring.jpa.generate-ddl}")
	private String jpaGenerateDDL;

	@Autowired
	private DataSource dataSource;

	@Bean
	@Primary
	public SessionFactory generateSession() {
		log.info("=============SESSION FACTORY==========");
		try {
			org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();

			configuration.setProperties(additionalProperties());

			/**
			 * adding persistence classes
			 */

			configuration.addAnnotatedClass(Employee.class);
			configuration.addAnnotatedClass(Role.class);
			configuration.addAnnotatedClass(Salary.class); 

			factory = configuration.buildSessionFactory();

			return factory;
		} catch (Throwable ex) {

			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

	}

	@Bean(name = "transactionManagerRef")
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql("true".equals(jpaShowSql));
		vendorAdapter.setGenerateDdl("true".equals(jpaGenerateDDL));

		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactory.setPackagesToScan("com.fajar.employeedataapi.entities");
		entityManagerFactory.setPersistenceUnitName("default");

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", hibernateDialect);
		properties.setProperty("hibernate.show_sql", jpaShowSql);
		properties.setProperty("hbm2ddl.auto", hibernateDdlAuto);

		entityManagerFactory.setJpaProperties(properties);

		return entityManagerFactory;
	}

	private Properties additionalProperties() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", hibernateDialect);
		properties.setProperty("hibernate.connection.url", datasourceUrl);
		properties.setProperty("hibernate.connection.username", datasourceUsename);
		properties.setProperty("hibernate.connection.password", datasourcePassword);
		properties.setProperty("hibernate.connection.driver_class", datasourceDriverClass);
		properties.setProperty("hibernate.current_session_context_class", "thread");
		properties.setProperty("hibernate.show_sql", jpaShowSql);
//		properties.setProperty("hibernate.connection.pool_size", "1");
		properties.setProperty("hbm2ddl.auto", hibernateDdlAuto);
		return properties;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
