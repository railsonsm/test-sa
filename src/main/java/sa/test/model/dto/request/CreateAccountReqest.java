package sa.test.model.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import sa.test.model.Account;
import sa.test.model.Customer;

public class CreateAccountReqest {
   @NotNull
   private Long agency;

   @NotNull
   private Long accountNumber;

   @NotNull
   private Long customerId;

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

   public Long getCustomerId() {
      return this.customerId;
   }

   public void setCustomerId(Long customerId) {
      this.customerId = customerId;
   }

   public Account toAccount() {
      Account account = new Account();

      account.setActive(true);
      account.setAgency(agency);
      account.setAccountNumber(accountNumber);
      account.setBalance(new BigDecimal(1000));
      account.setCustomer(new Customer(customerId));
      return account;
   }

}
