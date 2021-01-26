package ru.vapima.butjet4;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = TemplateApplication.class, initializers = BaseTest.Initializer.class)
public abstract class BaseTest {
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgreSQLContainer.start();

            /* Динамически подставляем сгенерированные проперти */
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.jpa.properties.hibernate.default_schema=public",
                    "spring.datasource.hikari.schema=public",
                    "spring.liquibase.change-log=classpath:db/changelog/master.xml"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.7")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

}
