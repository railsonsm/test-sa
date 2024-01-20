package sa.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sa.test.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

   @Query("select case when count(a.id) > 0 then 'true' else 'false' end from Account a where a.agency = :agency and a.accountNumber = :accountNumber")
   Boolean validateAccountByAgencyAndAccountNumber(Long agency, Long accountNumber);

   Account findOneByAgencyAndAccountNumber(Long agency, Long accountNumber);

}
