package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Value;
import javax.sql.DataSource;

import com.example.demo.utils.SecretReader;

@Configuration
public class AppConfig {

    @Value("${DB_URL:}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(SecretReader.readSecret("/run/secrets/db_url", dbUrl));
        dataSource.setUsername(SecretReader.readSecret("/run/secrets/db_username", "defaultUser"));
        dataSource.setPassword(SecretReader.readSecret("/run/secrets/db_password", "defaultPassword"));
        return dataSource;
    }
}