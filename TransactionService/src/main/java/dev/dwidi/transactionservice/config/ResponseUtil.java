package dev.dwidi.transactionservice.config;

import feign.Response;

import java.io.IOException;
import java.io.InputStream;

public class ResponseUtil {

    public static byte[] responseToByteArray(Response response) throws IOException {
        try (InputStream inputStream = response.body().asInputStream()) {
            return inputStream.readAllBytes();
        }
    }
}