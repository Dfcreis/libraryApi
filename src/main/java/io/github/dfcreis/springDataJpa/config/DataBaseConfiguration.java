package io.github.dfcreis.springDataJpa.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.DriverManager;

@Configuration
public class DataBaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String user;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-cass-name}")
    String driver;


//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dmd = new DriverManagerDataSource();
//
//        dmd.setUrl(url);
//        dmd.setUsername(user);
//        dmd.setPassword(password);
//        dmd.setDriverClassName(driver);
//
//        return dmd;
//    }

    @Bean
    public DataSource hikariDataSource(){
    //documentacao do hikari no endereco (https://github.com/brettwooldridge/)

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10); //Maximo de conexoes liberadas
        config.setMinimumIdle(1);//Minimo de conexoes ja liberadas des do inicio
        config.setPoolName("Libary_db_poll"); //setar o nome do pool (Opcional)
        config.setMaxLifetime(600000);//tempo do hikari e em milesegundos
        config.setConnectionTimeout(100000);//tempo que ira tentar uma conexao caso nao consiga ira dar timeout
        config.setConnectionTestQuery("select 1"); //testar se esta ocorrendo conexao com banco de dados


        return new HikariDataSource(config);


    }

}
