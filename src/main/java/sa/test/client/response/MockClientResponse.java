package sa.test.client.response;

public class MockClientResponse {
   private boolean messageSent;

   public boolean isMessageSent() {
      return messageSent;
   }

   public void setMessageSent(boolean messageSent) {
      this.messageSent = messageSent;
   }

   @Override
   public String toString() {
      return "MockClientResponse [messageSent=" + messageSent + "]";
   }
}
