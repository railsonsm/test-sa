package sa.test.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sa.test.model.dto.request.CreateAccountReqest;
import sa.test.service.AccountService;

@RequestMapping("/accounts")
@RestController
public class AccountResource {
   private AccountService accountService;

   public AccountResource(AccountService accountService) {
      this.accountService = accountService;
   }

   @PostMapping
   public ResponseEntity<Void> createAccount(@Valid @RequestBody CreateAccountReqest request) {
      accountService.create(request);
      return ResponseEntity.status(HttpStatus.CREATED).build();
   }
}
