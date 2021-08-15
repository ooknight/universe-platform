package io.github.ooknight.universe.support.secure.api.configure;

import io.github.ooknight.universe.support.secure.api.crypto.SecureApiCrypto;
import io.github.ooknight.universe.support.secure.api.crypto.impl.SecureApiCryptoAes;
import io.github.ooknight.universe.support.secure.api.crypto.impl.SecureApiCryptoNoop;
import io.github.ooknight.universe.support.secure.api.crypto.impl.SecureApiCryptoRsa;
import io.github.ooknight.universe.support.secure.api.spring.SecureRequestBodyAdvice;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(SecureApiProperties.class)
@Import(SecureRequestBodyAdvice.class)
public class SecureApiAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "universe.secure.api.decryptor", havingValue = "noop", matchIfMissing = true)
    public SecureApiCrypto noop() {
        return new SecureApiCryptoNoop();
    }

    @Bean
    @ConditionalOnProperty(name = "universe.secure.api.decryptor", havingValue = "aes")
    SecureApiCrypto aes() {
        return new SecureApiCryptoAes();
    }

    @Bean
    @ConditionalOnProperty(name = "universe.secure.api.decryptor", havingValue = "rsa")
    SecureApiCrypto rsa() {
        return new SecureApiCryptoRsa();
    }
}
