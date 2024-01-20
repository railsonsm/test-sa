package sa.test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import sa.test.client.response.MockClientResponse;

@FeignClient(name="mocky", url="https://run.mocky.io")
public interface MockClient {
   
   @GetMapping("/v3/9769bf3a-b0b6-477a-9ff5-91f63010c9d3")
   public ResponseEntity<MockClientResponse> notifyUser();
}
