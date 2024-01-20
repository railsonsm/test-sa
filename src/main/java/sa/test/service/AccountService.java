package sa.test.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import sa.test.exception.BusinessException;
import sa.test.model.Account;
import sa.test.model.dto.request.CreateAccountReqest;
import sa.test.repository.AccountRepository;

@Service
public class AccountService {
   private AccountRepository accountRepository;
   private CustomerService customerService;

   public AccountService(AccountRepository accountRepository, CustomerService customerService) {
      this.accountRepository = accountRepository;
      this.customerService = customerService;
   }

   public void create(CreateAccountReqest request) {
      validateAccount(request.getAgency(), request.getAccountNumber());
      validateCustomer(request.getCustomerId());

      Account account = request.toAccount();
      save(account);
   }

   public void save(Account account) {
      accountRepository.save(account);
   }

   public Account getAccountByAgencyAndAccountNumber(Long agency, Long accountNumner) {
      return accountRepository.findOneByAgencyAndAccountNumber(agency, accountNumner);
   }

   public void validateAccount(Long agency, Long accountNumner) {
      if (Boolean.TRUE.equals(accountRepository.validateAccountByAgencyAndAccountNumber(agency, accountNumner))) {
         throw new BusinessException("Already exist a account with these account data");
      }
   }

   public void validateBalance(Account account, BigDecimal value) {

      if (account.getBalance().compareTo(value) < 0) {
         throw new BusinessException("Account has not balance");
      }
   }

   public void validateCustomer(Long id) {
      if (Boolean.TRUE.equals(customerService.getCustomerById(id) == null)) {
         throw new BusinessException("Invalid customer");
      }
   }
}
