package sa.test.model.dto.request;

public class NotificationRequest {
   private String messsage;
   private Long customerId;
   
   public NotificationRequest(String messsage, Long customerId) {
      this.messsage = messsage;
      this.customerId = customerId;
   }
   public String getMesssage() {
      return messsage;
   }
   public void setMesssage(String messsage) {
      this.messsage = messsage;
   }
   public Long getCustomerId() {
      return customerId;
   }
   public void setCustomerId(Long customerId) {
      this.customerId = customerId;
   }
}
