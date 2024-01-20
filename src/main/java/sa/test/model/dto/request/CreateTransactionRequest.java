package sa.test.model.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sa.test.model.Transaction;
import sa.test.model.enums.TransactionStatus;

public class CreateTransactionRequest {
   @NotNull
   @Valid
   private AccountRequest sourceAccount;

   @NotNull
   @Valid
   private AccountRequest destinationAccount;

   @NotBlank
   private String type;

   @NotNull
   private BigDecimal value;

   public Transaction toTransaction() {
      Transaction transaction = new Transaction();
      transaction.setCorrelationId(UUID.randomUUID().toString());
      transaction.setCreated(LocalDateTime.now());
      transaction.setStatus(TransactionStatus.STARTED);
      transaction.setTransactionValue(value);
      return transaction;
   }

   public AccountRequest getSourceAccount() {
      return this.sourceAccount;
   }

   public void setSourceAccount(AccountRequest sourceAccount) {
      this.sourceAccount = sourceAccount;
   }

   public AccountRequest getDestinationAccount() {
      return this.destinationAccount;
   }

   public void setDestinationAccount(AccountRequest destinationAccount) {
      this.destinationAccount = destinationAccount;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public BigDecimal getValue() {
      return this.value;
   }

   public void setValue(BigDecimal value) {
      this.value = value;
   }

}
