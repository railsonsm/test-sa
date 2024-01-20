package sa.test.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sa.test.model.dto.request.CreateTransactionRequest;
import sa.test.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionResource {
   private TransactionService transactionService;

   public TransactionResource(TransactionService transactionService) {
      this.transactionService = transactionService;
   }

   @PostMapping
   public ResponseEntity<Void> createTransaction(@Valid @RequestBody CreateTransactionRequest request) {
      transactionService.create(request);
      return ResponseEntity.status(HttpStatus.CREATED).build();
   }
}
