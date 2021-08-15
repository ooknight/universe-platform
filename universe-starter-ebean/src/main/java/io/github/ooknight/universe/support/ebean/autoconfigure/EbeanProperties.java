package io.github.ooknight.universe.support.ebean.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.EnumType;

@ConfigurationProperties(prefix = "universe.ebean")
public class EbeanProperties {

    private EnumType defaultEnumType = EnumType.STRING;
    private boolean disableLazyLoading = true;
    private boolean expressionEqualsWithNullAsNoop = true;
    private long slowQueryMillis = 3000L;
    private String encryptSecret;

    public EnumType getDefaultEnumType() {
        return defaultEnumType;
    }

    public void setDefaultEnumType(EnumType defaultEnumType) {
        this.defaultEnumType = defaultEnumType;
    }

    public boolean isDisableLazyLoading() {
        return disableLazyLoading;
    }

    public void setDisableLazyLoading(boolean disableLazyLoading) {
        this.disableLazyLoading = disableLazyLoading;
    }

    public boolean isExpressionEqualsWithNullAsNoop() {
        return expressionEqualsWithNullAsNoop;
    }

    public void setExpressionEqualsWithNullAsNoop(boolean expressionEqualsWithNullAsNoop) {
        this.expressionEqualsWithNullAsNoop = expressionEqualsWithNullAsNoop;
    }

    public long getSlowQueryMillis() {
        return slowQueryMillis;
    }

    public void setSlowQueryMillis(long slowQueryMillis) {
        this.slowQueryMillis = slowQueryMillis;
    }

    public String getEncryptSecret() {
        return encryptSecret;
    }

    public void setEncryptSecret(String encryptSecret) {
        this.encryptSecret = encryptSecret;
    }
}
