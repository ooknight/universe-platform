package io.github.ooknight.universe.support.secure.api.endpoint;

import io.github.ooknight.universe.support.secure.api.crypto.SecureApiCrypto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SecureEndpoint {

    @Resource
    private SecureApiCrypto crypto;

    @GetMapping("/secure/encrypt")
    public String encrypt(String content) {
        return crypto.encrypt(content);
    }

    @GetMapping("/secure/decrypt")
    public String decrypt(String content) {
        return crypto.decrypt(content);
    }

    @GetMapping("/secure/sign")
    public String sign(String timestamp) {
        return "eee";
    }
}
