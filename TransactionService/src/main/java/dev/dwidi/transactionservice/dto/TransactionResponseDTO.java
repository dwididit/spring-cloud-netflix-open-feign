package dev.dwidi.transactionservice.dto;

import dev.dwidi.transactionservice.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {
    private Long transactionId;
    private Long userId;
    private Long productId;

    public TransactionResponseDTO(Transaction transaction) {
        this.transactionId = transaction.getTransactionId();
        this.userId = transaction.getUserId();
        this.productId = transaction.getProductId();
    }
}
