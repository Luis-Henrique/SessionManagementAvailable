package br.com.luish.management.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import br.com.luish.management.utils.ConstantesUtil;

@Configuration
@Profile("dev")
public class MySQLConfiguration {
	
	//conexao com o banco
		@Bean
		public DataSource dataSource() {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setUrl(ConstantesUtil.URL_MYSQL);
			dataSource.setUsername(ConstantesUtil.USER);
			dataSource.setPassword(ConstantesUtil.PASSWORD);
			return dataSource;
			
		}
		
		//configuracao hibernate
		@Bean
		public JpaVendorAdapter jpaVendorAdapter() {
			HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();//conexao com hibernate
			adapter.setDatabase(Database.MYSQL);//db utilizando
			adapter.setShowSql(true);//etapas de banco no console
			adapter.setGenerateDdl(true);// permite hibernate criar tabelas automaticamente
			adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
			adapter.setPrepareConnection(true);//hibernate preparar a conexao automaticamente
			return adapter;
		}

}
