package dev.dwidi.transactionservice.service;

import dev.dwidi.transactionservice.client.ProductClient;
import dev.dwidi.transactionservice.client.UserClient;
import dev.dwidi.transactionservice.dto.PublicResponseDTO;
import dev.dwidi.transactionservice.dto.TransactionRequestDTO;
import dev.dwidi.transactionservice.dto.TransactionResponseDTO;
import dev.dwidi.transactionservice.entity.Transaction;
import dev.dwidi.transactionservice.entity.dto.ProductResponseDTO;
import dev.dwidi.transactionservice.entity.dto.UserResponseDTO;
import dev.dwidi.transactionservice.exception.ResourceNotFoundException;
import dev.dwidi.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private ProductClient productClient;

    public PublicResponseDTO<TransactionResponseDTO> createTransaction(TransactionRequestDTO transactionRequestDTO) {
        // Check if user exists
        UserResponseDTO user;
        try {
            PublicResponseDTO<UserResponseDTO> userResponse = userClient.getUserById(transactionRequestDTO.getUserId());
            user = userResponse.getData();
            if (user == null) {
                throw new ResourceNotFoundException("User not found with ID: " + transactionRequestDTO.getUserId());
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found with ID: " + transactionRequestDTO.getUserId());
        }

        // Check if product exists
        ProductResponseDTO product;
        try {
            PublicResponseDTO<ProductResponseDTO> productResponse = productClient.getProductById(transactionRequestDTO.getProductId());
            product = productResponse.getData();
            if (product == null) {
                throw new ResourceNotFoundException("Product not found with ID: " + transactionRequestDTO.getProductId());
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("Product not found with ID: " + transactionRequestDTO.getProductId());
        }

        // Create transaction
        Transaction transaction = new Transaction();
        transaction.setUserId(transactionRequestDTO.getUserId());
        transaction.setProductId(transactionRequestDTO.getProductId());
        transactionRepository.save(transaction);

        return new PublicResponseDTO<>(200, "Transaction created successfully", new TransactionResponseDTO(transaction));
    }

    public PublicResponseDTO<List<TransactionResponseDTO>> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionResponseDTO> transactionResponseDTOS = transactions.stream()
                .map(TransactionResponseDTO::new)
                .collect(Collectors.toList());
        return new PublicResponseDTO<>(200, "Transactions fetched successfully", transactionResponseDTOS);
    }

    public PublicResponseDTO<TransactionResponseDTO> getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + id));
        return new PublicResponseDTO<>(200, "Transaction fetched successfully", new TransactionResponseDTO(transaction));
    }

    public PublicResponseDTO<List<TransactionResponseDTO>> getTransactionsByUserId(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        List<TransactionResponseDTO> transactionResponseDTOS = transactions.stream()
                .map(TransactionResponseDTO::new)
                .collect(Collectors.toList());
        return new PublicResponseDTO<>(200, "Transactions fetched successfully", transactionResponseDTOS);
    }
}
