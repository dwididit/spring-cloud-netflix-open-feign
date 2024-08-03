package dev.dwidi.transactionservice.entity;

import dev.dwidi.transactionservice.entity.dto.ProductResponseDTO;
import dev.dwidi.transactionservice.entity.dto.UserResponseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Long userId;
    private Long productId;

    @Transient
    private UserResponseDTO userResponseDTO;

    @Transient
    private ProductResponseDTO productResponseDTO;
}
