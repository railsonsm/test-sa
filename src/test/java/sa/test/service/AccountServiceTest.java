package sa.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sa.test.exception.BusinessException;
import sa.test.model.Account;
import sa.test.model.Customer;
import sa.test.model.dto.request.CreateAccountReqest;
import sa.test.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName("Deve criar a conta")
    void t1() {
        CreateAccountReqest request = new CreateAccountReqest();
        request.setAgency(123L);
        request.setAccountNumber(456L);
        request.setCustomerId(789L);

        when(accountRepository.validateAccountByAgencyAndAccountNumber(anyLong(), anyLong())).thenReturn(false);
        when(customerService.getCustomerById(anyLong())).thenReturn(new Customer());

        accountService.create(request);

        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    @DisplayName("Deve lançar erro por duplicidade de conta/agencia")
    void t2() {

        CreateAccountReqest request = new CreateAccountReqest();
        request.setAgency(123L);
        request.setAccountNumber(456L);
        request.setCustomerId(789L);

        when(accountRepository.validateAccountByAgencyAndAccountNumber(anyLong(), anyLong())).thenReturn(true);

        assertThrows(BusinessException.class, () -> accountService.create(request));
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @DisplayName("Deve lançar erro por falta de saldo")
    void t3() {

        Account account = new Account();
        account.setBalance(new BigDecimal("100.00"));
        BigDecimal valueToWithdraw = new BigDecimal("150.00");

        BusinessException exception = assertThrows(BusinessException.class,
                () -> accountService.validateBalance(account, valueToWithdraw));
        assertEquals("Account has not balance", exception.getMessage());
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @DisplayName("Deve lançar erro pois o cliente não foi encontrado")
    void t4() {
        
        Long customerId = 123L; 
        when(customerService.getCustomerById(customerId)).thenReturn(null);

        BusinessException exception = assertThrows(BusinessException.class, () -> accountService.validateCustomer(customerId));
        assertEquals("Invalid customer", exception.getMessage());
        verify(accountRepository, never()).save(any(Account.class));
    }

}
