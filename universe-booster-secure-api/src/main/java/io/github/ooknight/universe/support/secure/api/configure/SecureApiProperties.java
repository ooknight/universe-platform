package io.github.ooknight.universe.support.secure.api.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.MINUTES;

@ConfigurationProperties(prefix = "universe.secure.api")
public class SecureApiProperties {

    private String publicKey;
    private String privateKey;
    private Duration expire = Duration.of(3, MINUTES);

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Duration getExpire() {
        return expire;
    }

    public void setExpire(Duration expire) {
        this.expire = expire;
    }
}
