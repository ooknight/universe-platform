package io.github.ooknight.universe.support.ebean.autoconfigure;

import io.github.ooknight.universe.support.ebean.ConfigurableEncryptKeyManager;
import io.github.ooknight.universe.support.ebean.LoggingSlowQueryListener;

import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.spring.txn.SpringJdbcTransactionManager;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@ConditionalOnClass(Database.class)
@ConditionalOnBean(DataSource.class)
@EnableConfigurationProperties(EbeanProperties.class)
@AutoConfigureAfter({DataSourceAutoConfiguration.class, TransactionAutoConfiguration.class})
public class EbeanAutoConfiguration {

    @Resource
    private EbeanProperties properties;

    @Bean
    public Database database(DatabaseConfig config) {
        return DatabaseFactory.create(config);
    }

    @Bean
    @ConditionalOnMissingBean(DatabaseConfig.class)
    public DatabaseConfig databaseConfig(DataSource dataSource) {
        DatabaseConfig config = new DatabaseConfig();
        config.setDataSource(dataSource);
        config.setDefaultEnumType(properties.getDefaultEnumType());
        config.setDisableLazyLoading(properties.isDisableLazyLoading());
        config.setExpressionEqualsWithNullAsNoop(properties.isExpressionEqualsWithNullAsNoop());
        config.setSlowQueryMillis(properties.getSlowQueryMillis());
        config.setSlowQueryListener(new LoggingSlowQueryListener());
        config.setExternalTransactionManager(new SpringJdbcTransactionManager());
        if (properties.getEncryptSecret() != null) {
            config.setEncryptKeyManager(new ConfigurableEncryptKeyManager(properties.getEncryptSecret()));
        }
        return config;
    }
}
