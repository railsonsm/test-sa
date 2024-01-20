package sa.test.service;

import org.springframework.stereotype.Service;

import sa.test.exception.BusinessException;
import sa.test.model.Customer;
import sa.test.model.dto.request.CreateCustomerRequest;
import sa.test.repository.CustomerReposutory;

@Service
public class CustomerService {
   private CustomerReposutory customerRepository;

   public CustomerService(CustomerReposutory customerRepository) {
      this.customerRepository = customerRepository;
   }

   public void create(CreateCustomerRequest request) {
      validateDocument(request.getDocument());
      
      Customer customer = request.toCustomer();
      save(customer);
   }

   public void save(Customer customer) {
      customerRepository.save(customer);
   }

   public Customer getCustomerById(Long id){
      return customerRepository.findOneById(id);
   }

   public void validateDocument(String document) {
      if (Boolean.TRUE.equals(customerRepository.existDocument(document))) {
         throw new BusinessException("Already exist a customer with this document");
      }
   }
}
