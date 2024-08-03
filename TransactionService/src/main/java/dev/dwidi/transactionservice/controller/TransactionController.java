package dev.dwidi.transactionservice.controller;

import dev.dwidi.transactionservice.dto.PublicResponseDTO;
import dev.dwidi.transactionservice.dto.TransactionRequestDTO;
import dev.dwidi.transactionservice.dto.TransactionResponseDTO;
import dev.dwidi.transactionservice.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create")
    public PublicResponseDTO<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        log.info("Creating transaction for user with ID: {} and product with ID: {}", transactionRequestDTO.getUserId(), transactionRequestDTO.getProductId());
        return transactionService.createTransaction(transactionRequestDTO);
    }

    @GetMapping("/all")
    public PublicResponseDTO<List<TransactionResponseDTO>> getAllTransactions() {
        log.info("Fetching all transactions");
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public PublicResponseDTO<TransactionResponseDTO> getTransactionById(@PathVariable Long id) {
        log.info("Fetching transaction with ID: {}", id);
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/user/{userId}")
    public PublicResponseDTO<List<TransactionResponseDTO>> getTransactionsByUserId(@PathVariable Long userId) {
        log.info("Fetching transactions for user with ID: {}", userId);
        return transactionService.getTransactionsByUserId(userId);
    }
}
