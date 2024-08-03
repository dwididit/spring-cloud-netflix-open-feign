package dev.dwidi.transactionservice.config;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    public static class CustomErrorDecoder implements ErrorDecoder {
        @Override
        public Exception decode(String methodKey, Response response) {
            if (response.status() == 404) {
                byte[] responseBody;
                try {
                    responseBody = ResponseUtil.responseToByteArray(response);
                } catch (IOException e) {
                    return new FeignException.NotFound("Resource not found", response.request(), null, null);
                }
                return new FeignException.NotFound("Resource not found", response.request(), responseBody, null);
            }
            return FeignException.errorStatus(methodKey, response);
        }
    }
}