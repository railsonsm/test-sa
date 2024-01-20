package sa.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sa.test.exception.BusinessException;
import sa.test.model.Customer;
import sa.test.model.dto.request.CreateCustomerRequest;
import sa.test.repository.CustomerReposutory;

@ExtendWith(MockitoExtension.class)

class CustomerServiceTest {
  @Mock
  private CustomerReposutory customerRepository;

  @InjectMocks
  private CustomerService customerService;

  @Test
  @DisplayName("Deve criar a cliente")
  void t1() {
    CreateCustomerRequest request = new CreateCustomerRequest();
    request.setDocument("123");
    request.setName("Cliente");
    request.setPassword("1234");
    request.setZipCode("1234");
    request.setStreetNumber("Rua longradouro não encontrado");
    request.setType("PF");

    when(customerRepository.existDocument(anyString())).thenReturn(false);

    customerService.create(request);

    verify(customerRepository, times(1)).save(any(Customer.class));
  }

  @Test
  @DisplayName("Deve lançar erro pois já existe algum cliente com esse document")
  void t2() {

    String document = "123456789";
    when(customerRepository.existDocument(document)).thenReturn(true);

    BusinessException exception = assertThrows(BusinessException.class,
        () -> customerService.validateDocument(document));
    assertEquals("Already exist a customer with this document", exception.getMessage());
  }

}
