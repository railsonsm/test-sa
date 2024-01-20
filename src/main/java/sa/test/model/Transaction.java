package sa.test.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import sa.test.model.enums.TransactionStatus;

@Entity
public class Transaction {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @JoinColumn(name = "source_account_id")
   @ManyToOne
   private Account sourceAccount;

   @JoinColumn(name = "destination_account_id")
   @ManyToOne
   private Account destinationAccount;

   @Enumerated(EnumType.STRING)
   private TransactionStatus status;

   private LocalDateTime created;
   private LocalDateTime finished;

   @Column(nullable = false)
   private BigDecimal transactionValue;

   private String description;

   @Column(name = "correlation_id")
   private String correlationId;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Account getSourceAccount() {
      return sourceAccount;
   }

   public void setSourceAccount(Account sourceAccount) {
      this.sourceAccount = sourceAccount;
   }

   public Account getDestinationAccount() {
      return destinationAccount;
   }

   public void setDestinationAccount(Account destinationAccount) {
      this.destinationAccount = destinationAccount;
   }

   public TransactionStatus getStatus() {
      return status;
   }

   public void setStatus(TransactionStatus status) {
      this.status = status;
   }

   public LocalDateTime getCreated() {
      return created;
   }

   public void setCreated(LocalDateTime created) {
      this.created = created;
   }

   public LocalDateTime getFinished() {
      return finished;
   }

   public void setFinished(LocalDateTime finished) {
      this.finished = finished;
   }

   public BigDecimal getTransactionValue() {
      return this.transactionValue;
   }

   public void setTransactionValue(BigDecimal transactionValue) {
      this.transactionValue = transactionValue;
   }

   

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getCorrelationId() {
      return correlationId;
   }

   public void setCorrelationId(String correlationId) {
      this.correlationId = correlationId;
   }

 
}
