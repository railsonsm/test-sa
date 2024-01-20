package sa.test.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sa.test.exception.BusinessException;
import sa.test.model.Account;
import sa.test.model.Transaction;
import sa.test.model.dto.request.CreateTransactionRequest;
import sa.test.model.dto.request.NotificationRequest;
import sa.test.model.enums.TransactionStatus;
import sa.test.repository.TransactionRepository;

@Service
public class TransactionService {
   private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

   public TransactionService(TransactionRepository transactionRepository, AccountService accountService,
         NotificationService notificationService) {
      this.transactionRepository = transactionRepository;
      this.accountService = accountService;
      this.notificationService = notificationService;
   }

   private TransactionRepository transactionRepository;
   private AccountService accountService;
   private NotificationService notificationService;

   public Transaction create(CreateTransactionRequest request) {
      Account source = accountService.getAccountByAgencyAndAccountNumber(request.getSourceAccount().getAgency(),
            request.getSourceAccount().getAccountNumber());

      Account destination = accountService.getAccountByAgencyAndAccountNumber(
            request.getDestinationAccount().getAgency(), request.getDestinationAccount().getAccountNumber());

      validateTransaction(source, destination);

      accountService.validateBalance(source, request.getValue());

      Transaction transaction = request.toTransaction();
      transaction.setSourceAccount(source);
      transaction.setDestinationAccount(destination);

      save(transaction);

      try {
         execute(transaction);
         notifyUsers(transaction);
      } catch (Exception e) {
         transaction.setStatus(TransactionStatus.ERROR);
         transaction.setDescription(e.getMessage());
         transaction.setFinished(LocalDateTime.now());
         save(transaction);
      }

      return transaction;
   }

   public void notifyUsers(Transaction transaction) {

      Account sourceAccount = transaction.getSourceAccount();
      Account destinationAccount = transaction.getDestinationAccount();

      try {
         notificationService
               .notify(new NotificationRequest("Transaction finished", sourceAccount.getCustomer().getId()));
         notificationService
               .notify(new NotificationRequest("Transaction finished", destinationAccount.getCustomer().getId()));
      } catch (Exception e) {
         logger.error("Erro occurred during notification", e);
      }
   }

   @Transactional
   public void execute(Transaction transaction) throws Exception {
      Account sourceAccount = transaction.getSourceAccount();
      Account destionationAccount = transaction.getDestinationAccount();

      transaction.getSourceAccount().setBalance(sourceAccount.getBalance().subtract(transaction.getTransactionValue()));
      transaction.getDestinationAccount()
            .setBalance(destionationAccount.getBalance().add(transaction.getTransactionValue()));

      accountService.save(sourceAccount);
      accountService.save(destionationAccount);

      transaction.setStatus(TransactionStatus.FINISHED);
      transaction.setFinished(LocalDateTime.now());
      save(transaction);
   }

   public void save(Transaction transaction) {
      transactionRepository.save(transaction);
   }

   public void validateTransaction(Account source, Account destination) {
      if (Boolean.TRUE.equals(source == null)) {
         throw new BusinessException("Invalid source account");
      }

      if (Boolean.TRUE.equals(destination == null)) {
         throw new BusinessException("Invalid destination account");
      }
   }

}
