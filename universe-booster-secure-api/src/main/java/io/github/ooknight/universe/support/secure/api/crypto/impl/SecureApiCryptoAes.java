package io.github.ooknight.universe.support.secure.api.crypto.impl;

import io.github.ooknight.universe.support.secure.api.crypto.SecureApiCrypto;

public class SecureApiCryptoAes implements SecureApiCrypto {

    ///**
    // * 请求时间1分钟
    // */
    //private static final int EXPIRE_TIME = 60 * 1000;
    //
    //@Override
    //public HttpInputMessage decrypt(HttpInputMessage input) throws IOException {
    //    ////时间戳校验
    //    String timestamp = input.getHeaders().getFirst("timestamp");
    //    if (timestamp == null || timestamp.isEmpty()) {
    //        throw new RuntimeException("缺少时间戳");
    //    }
    //    Long now = System.currentTimeMillis();
    //    Long timeStamp = Long.parseLong(timestamp);
    //    if ((now - timeStamp) > EXPIRE_TIME) {
    //        throw new RuntimeException("请求超时");
    //    }
    //    //解密操作
    //    String httpBody = decryptBody(input, timeStamp.toString());
    //    if (httpBody == null) {
    //        throw new RuntimeException("解密失败");
    //    }
    //    //return new HttpInputMessage() {
    //    //    @Override
    //    //    public InputStream getBody() throws IOException {
    //    //        return new ByteArrayInputStream(httpBody.getBytes());
    //    //    }
    //    //
    //    //    @Override
    //    //    public HttpHeaders getHeaders() {
    //    //        return input.getHeaders();
    //    //    }
    //    //};
    //    return new SecureHttpInputMessage(new ByteArrayInputStream(httpBody.getBytes()), input.getHeaders());
    //}
    //
    ///**
    // * 解密
    // *
    // * @param inputMessage message
    // * @param timeStamp    header 时间戳
    // * @return 解密的字符串
    // * @throws IOException 异常
    // */
    //private String decryptBody(HttpInputMessage inputMessage, String timeStamp) throws IOException {
    //    //获取body input流 并转换成string
    //    InputStream encryptStream = inputMessage.getBody();
    //    String encryptBody = StreamUtils.copyToString(encryptStream, StandardCharsets.UTF_8);
    //    //解密
    //    //String decryptString = AesSecret.decode(encryptBody);
    //    //if (StringUtils.isEmpty(decryptString)) {
    //    //    return null;
    //    //}
    //    //return AesSecret.split(decryptString.trim(), timeStamp);
    //    return "";
    //}
}
