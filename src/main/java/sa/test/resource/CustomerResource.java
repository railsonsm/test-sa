package sa.test.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sa.test.model.dto.request.CreateCustomerRequest;
import sa.test.service.CustomerService;

@RequestMapping("/customers")
@RestController
public class CustomerResource {
   
   private CustomerService customerService;

   public CustomerResource(CustomerService customerService) {
      this.customerService = customerService;
   }

   @PostMapping
   public ResponseEntity<Void> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {

      customerService.create(request);

      return ResponseEntity.status(HttpStatus.CREATED).build();
   }
}
