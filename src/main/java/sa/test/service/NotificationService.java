package sa.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import sa.test.client.MockClient;
import sa.test.client.response.MockClientResponse;
import sa.test.model.dto.request.NotificationRequest;

@Service
public class NotificationService {
   private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

   private MockClient mockClient;

   public NotificationService(MockClient mockClient) {
      this.mockClient = mockClient;
   }

   public void notify(NotificationRequest notify) {
      logger.info("Notify {}, msg {}", notify.getCustomerId(), notify.getMesssage());
      ResponseEntity<MockClientResponse> response = mockClient.notifyUser();
      if (response.getStatusCode().value() == 200) {
         logger.info("{}", response.getBody());
      }
   }
}
