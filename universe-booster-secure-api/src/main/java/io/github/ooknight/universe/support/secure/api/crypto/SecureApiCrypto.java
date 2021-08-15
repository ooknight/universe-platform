package io.github.ooknight.universe.support.secure.api.crypto;

import static io.github.ooknight.universe.support.utils.COMBINE.x;

public interface SecureApiCrypto {

    default String decrypt(String content) {
        return content;
    }

    default String encrypt(String content) {
        return content;
    }

    default String sign(String timestamp, String body) {

        return x.digest.sha1(timestamp + body);

    }
}
