package extras;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "beans,mvc" })
@EnableJpaRepositories(basePackages = { "beans,mvc" })
@EnableTransactionManagement
public class WalletConfiguration {

	@Bean(autowire = Autowire.BY_NAME)
	public JdbcTemplate getTemplate() {
		JdbcTemplate template = new JdbcTemplate();
		return template;
	}

	@Bean(name = "dataSource")
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("oracle.jdbc.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		ds.setUsername("system");
		ds.setPassword("himi1234");

		return ds;
	}

	@Bean(name = "entityManagerFactory", autowire = Autowire.BY_NAME)
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPackagesToScan("beans");
		return bean;
	}

	@Bean(name = "jpaVendorAdapter")
	public JpaVendorAdapter getAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		return adapter;
	}

	@Bean(name = "jpaProperties")
	public Properties getJpaProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		props.put("hibernate.hbm2ddl.auto", "update");
		props.put("hibernate.show_sql", "true");

		return props;
	}

	@Bean
	public JpaTransactionManager getTransactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}
