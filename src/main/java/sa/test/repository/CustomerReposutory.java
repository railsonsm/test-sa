package sa.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sa.test.model.Customer;

public interface CustomerReposutory extends JpaRepository<Customer, Long> {
 
   @Query("select case when count(c.id) > 0 then 'true' else 'false' end from Customer c where c.document = :document")
   Boolean existDocument(String document);

   Customer findOneById(Long id);
}
