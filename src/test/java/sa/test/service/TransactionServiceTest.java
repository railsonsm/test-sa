package sa.test.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
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

import sa.test.model.Account;
import sa.test.model.Transaction;
import sa.test.model.dto.request.AccountRequest;
import sa.test.model.dto.request.CreateTransactionRequest;
import sa.test.model.enums.TransactionStatus;
import sa.test.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

   @Mock
   private TransactionRepository transactionRepository;

   @Mock
   private AccountService accountService;

   @Mock
   private NotificationService notificationService;

   @InjectMocks
   private TransactionService transactionService;
   private TransactionService transactionServiceSpy;

   @Test
   @DisplayName("Deve criar uma transacao")
   void t1() throws Exception {
      transactionServiceSpy = spy(transactionService);

      CreateTransactionRequest request = createTransactionRequest();

      Account sourceAccount = new Account();

      Account destinationAccount = new Account();

      when(accountService.getAccountByAgencyAndAccountNumber(any(), any())).thenReturn(sourceAccount,
            destinationAccount);
      when(transactionRepository.save(any())).thenReturn(new Transaction());

      doNothing().when(transactionServiceSpy).execute(any(Transaction.class));
      doNothing().when(transactionServiceSpy).notifyUsers(any(Transaction.class));

      Transaction transaction = transactionServiceSpy.create(request);

      assertEquals(TransactionStatus.STARTED, transaction.getStatus());
      verify(transactionRepository, times(1)).save(any(Transaction.class));
   }

   @Test
   @DisplayName("Deve criar uma transacao, mas deve dar erro na execução e salvar status ERROR")
   void t2() throws Exception {
      transactionServiceSpy = spy(transactionService);

      CreateTransactionRequest request = createTransactionRequest();

      Account sourceAccount = new Account();

      Account destinationAccount = new Account();

      when(accountService.getAccountByAgencyAndAccountNumber(any(), any())).thenReturn(sourceAccount,
            destinationAccount);
      when(transactionRepository.save(any())).thenReturn(new Transaction());

      doThrow(RuntimeException.class).when(transactionServiceSpy).execute(any(Transaction.class));

      Transaction transaction = transactionServiceSpy.create(request);

      assertEquals(TransactionStatus.ERROR, transaction.getStatus());
      verify(transactionRepository, times(2)).save(any(Transaction.class));
   }

   @Test
   @DisplayName("Deve executar uma transacao")
   void t3() {

      Account sourceAccount = new Account();
      sourceAccount.setBalance(new BigDecimal("1000"));

      Account destinationAccount = new Account();
      destinationAccount.setBalance(new BigDecimal("0"));

      Transaction transaction = new Transaction();
      transaction.setTransactionValue(new BigDecimal("250"));

      transaction.setSourceAccount(sourceAccount);
      transaction.setDestinationAccount(destinationAccount);

      when(transactionRepository.save(any())).thenReturn(transaction);

      assertDoesNotThrow(() -> transactionService.execute(transaction));
      assertEquals(new BigDecimal("250"), transaction.getDestinationAccount().getBalance());
      assertEquals(new BigDecimal("750"), transaction.getSourceAccount().getBalance());
      verify(accountService, times(2)).save(any(Account.class));
   }

   private CreateTransactionRequest createTransactionRequest() {
      CreateTransactionRequest request = new CreateTransactionRequest();
      AccountRequest sourceAccountRequest = new AccountRequest();
      sourceAccountRequest.setAccountNumber(1l);
      sourceAccountRequest.setAgency(1l);
      AccountRequest destinationAccountRequest = new AccountRequest();
      destinationAccountRequest.setAccountNumber(1l);
      destinationAccountRequest.setAgency(2l);
      request.setDestinationAccount(destinationAccountRequest);
      request.setSourceAccount(sourceAccountRequest);
      return request;
   }

}