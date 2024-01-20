package sa.test.model.dto.request;

import io.micrometer.common.lang.NonNull;

public class AccountRequest {
   @NonNull
   private Long agency;

   @NonNull
   private Long accountNumber;

   public Long getAgency() {
      return agency;
   }

   public void setAgency(Long agency) {
      this.agency = agency;
   }

   public Long getAccountNumber() {
      return accountNumber;
   }

   public void setAccountNumber(Long accountNumber) {
      this.accountNumber = accountNumber;
   }
}
