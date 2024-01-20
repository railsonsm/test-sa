package sa.test.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "agency", "accountNumber" }) })

public class Account {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private Long agency;

   @Column(name = "account_number", nullable = false)
   private Long accountNumber;

   private boolean active;
   private BigDecimal balance;

   @JoinColumn(name = "customer_id", nullable = false)
   @ManyToOne
   private Customer customer;

   public Account() {
   }

   public Account(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getAgency() {
      return this.agency;
   }

   public void setAgency(Long agency) {
      this.agency = agency;
   }

   public Long getAccountNumber() {
      return this.accountNumber;
   }

   public void setAccountNumber(Long accountNumber) {
      this.accountNumber = accountNumber;
   }

   public boolean isActive() {
      return this.active;
   }

   public boolean getActive() {
      return this.active;
   }

   public void setActive(boolean active) {
      this.active = active;
   }

   public BigDecimal getBalance() {
      return this.balance;
   }

   public void setBalance(BigDecimal balance) {
      this.balance = balance;
   }

   public Customer getCustomer() {
      return this.customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

}
