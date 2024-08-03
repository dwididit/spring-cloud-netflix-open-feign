package dev.dwidi.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicResponseDTO<T> {
    private Integer code;
    private String message;
    private T data;
}
