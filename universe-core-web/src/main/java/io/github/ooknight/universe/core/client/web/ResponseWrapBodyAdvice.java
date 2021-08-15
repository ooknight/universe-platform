package io.github.ooknight.universe.core.client.web;

import io.github.ooknight.universe.core.framework.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ResponseWrapBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final String RESPONSE_WRAP_HEADER_NAME = "response-wrap";
    private static final String RESPONSE_WRAP_HEADER_VALUE_NONE = "none";
    private static final String RESPONSE_WRAP_HEADER_VALUE_SUCCESS = "success";
    private static final String RESPONSE_WRAP_HEADER_VALUE_ERROR = "error";
    private static final String RESPONSE_WRAP_HEADER_VALUE_ALL = "all";

    @Resource
    private MessageSource messageSource;

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType contentType, @NonNull Class<? extends HttpMessageConverter<?>> converterType,
                                  @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (!(body instanceof ResponseWrap) && contentType.includes(MediaType.APPLICATION_JSON) && isWrapSuccess(request)) {
            return ResponseWrap.success(body);
        }
        return body;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrap handle(Exception e, HttpServletRequest request) throws Throwable {
        if (isWrapError(request)) {
            log.warn("未处理的异常 :::: ", e);
            return ResponseWrap.error(500, "未处理的异常", e.getMessage());
        }
        throw e;
    }

    @ExceptionHandler({ValidationException.class, BindException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrap handle(Throwable e, HttpServletRequest request) throws Throwable {
        if (isWrapError(request)) {
            log.warn("参数检查异常(1) :::: ", e);
            return ResponseWrap.error(400, "参数检查异常", e.getMessage());
        }
        throw e;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrap handle(MethodArgumentNotValidException e, HttpServletRequest request) throws MethodArgumentNotValidException {
        if (isWrapError(request)) {
            log.warn("参数检查异常(2) :::: ", e);
            String message = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
            return ResponseWrap.error(400, "参数检查异常", message);
        }
        throw e;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrap handle(BusinessException e, HttpServletRequest request) throws BusinessException {
        if (isWrapError(request)) {
            log.warn("业务异常 :::: {}", e.code());
            String message = e.code();
            try {
                Locale locale = LocaleContextHolder.getLocale();
                message = messageSource.getMessage("error.business." + e.code(), e.args(), locale);
            } catch (Exception ex) {
                // ignore
            }
            return ResponseWrap.error(500, message);
        }
        throw e;
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrap handle(LoginException e, HttpServletRequest request) throws LoginException {
        if (isWrapError(request)) {
            log.warn("登录异常 :::: ", e);
            return ResponseWrap.error(401, "登录失败");
        }
        throw e;
    }

    private boolean isWrapError(HttpServletRequest request) {
        String value = request.getHeader(RESPONSE_WRAP_HEADER_NAME);
        return RESPONSE_WRAP_HEADER_VALUE_ALL.equals(value)
            || RESPONSE_WRAP_HEADER_VALUE_ERROR.equals(value);
    }

    private boolean isWrapSuccess(ServerHttpRequest request) {
        String value = request.getHeaders().getFirst(RESPONSE_WRAP_HEADER_NAME);
        return RESPONSE_WRAP_HEADER_VALUE_ALL.equals(value)
            || RESPONSE_WRAP_HEADER_VALUE_SUCCESS.equals(value);
    }
}
