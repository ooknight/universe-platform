package io.github.ooknight.universe.support.secure.api.spring;

import io.github.ooknight.universe.support.secure.api.configure.Constant;
import io.github.ooknight.universe.support.secure.api.configure.SecureApiProperties;
import io.github.ooknight.universe.support.secure.api.crypto.SecureApiCrypto;
import static io.github.ooknight.universe.support.utils.COMBINE.x;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("NullableProblems")
@RestControllerAdvice
public class SecureRequestBodyAdvice extends RequestBodyAdviceAdapter {

    @Resource
    private SecureApiCrypto decryptor;
    @Resource
    private SecureApiProperties properties;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        //return methodParameter.hasParameterAnnotation(RequestBody.class);
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage message, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        //
        // 1. 校验时间戳
        //
        String timestamp = message.getHeaders().getFirst(Constant.HEADER_NAME_TIMESTAMP);
        Assert.notNull(timestamp, "not found timestamp in header");
        Assert.isTrue(Long.parseLong(timestamp) < x.timestamp.now() + properties.getExpire().toMillis(), "request is expire");
        //
        // 2. 检验签名
        //
        String client = message.getHeaders().getFirst(Constant.HEADER_NAME_CLIENT_ID);
        String signature = message.getHeaders().getFirst(Constant.HEADER_NAME_SIGNATURE);
        //
        // 3. 解密数据
        //
        String content = StreamUtils.copyToString(message.getBody(), StandardCharsets.UTF_8);

        return new SecureHttpInputMessage(new ByteArrayInputStream(decryptor.decrypt(content).getBytes()), message.getHeaders());
    }
}
