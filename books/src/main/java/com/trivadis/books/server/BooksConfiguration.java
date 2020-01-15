package com.trivadis.books.server;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.trivadis.books.server.Entity.BookEntity;

@Configuration
@ComponentScan(basePackageClasses = BooksConfiguration.class)
@EnableTransactionManagement  // für datenbankanbindung
@EnableJpaRepositories   // für spring data
public class BooksConfiguration {
	
	@Bean
	DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(); 
		dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres");
		dataSource.setDriverClassName(Driver.class.getName());
		dataSource.setSchema("book");
		return dataSource;
	}

	//kann auch in persistency.xml erfolgen
	@Bean
	@Autowired
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		
		emfb.setDataSource(dataSource);
		emfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emfb.setPersistenceUnitName("books");
		emfb.setPackagesToScan(BookEntity.class.getPackage().getName());
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", PostgreSQL10Dialect.class.getName());
		jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		emfb.setJpaProperties(jpaProperties);
		return emfb;

	}
	
	
	@Bean
	@Autowired
	PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
