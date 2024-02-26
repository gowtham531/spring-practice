package com.restservices.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@ComponentScan("com.*")
@EnableJpaRepositories("com.*")
@Configuration
public class RestServicesJpaConfig {

	// Bean Objects

	// 1. Create DataSource Object i.e provide Database details
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		dataSource.setUsername("system");
		dataSource.setPassword("manager");
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		return dataSource;
	}

	// 2. Entity Manager Factory instance
	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean createEntityManagerFactory() {

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

		// a. Set DataSource Details // created at step 1
		factory.setDataSource(getDataSource());

		// b. Packages info of Entity classes
		factory.setPackagesToScan("com.*");

		// c. Hibernate FW Properties
		factory.setJpaProperties(configureHibernateProps());

		// 4. Which vendor , impl FW
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		factory.setJpaVendorAdapter(adapter);

		return factory;
	}

	// 3. Create A Transaction manager
	@Bean("transactionManager")
	public PlatformTransactionManager createTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(createEntityManagerFactory().getObject());

		return transactionManager;
	}

	public Properties configureHibernateProps() {

		Properties props = new Properties();
		props.setProperty("hibernate.hbm2ddl.auto", "none");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");

		return props;
	}

}