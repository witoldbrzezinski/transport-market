package pl.witoldbrzezinski.transportmarket;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {
        "pl.witoldbrzezinski.transportmarket",
})
@EnableTransactionManagement
public class DataSourceTestConfig {

    @Bean
    @Profile("test")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/transportmarket_test");
        dataSource.setUsername("test");
        dataSource.setPassword("test");
        return dataSource;
    }

}
