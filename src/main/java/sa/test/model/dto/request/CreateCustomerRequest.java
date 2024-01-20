package sa.test.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import sa.test.model.Address;
import sa.test.model.Customer;
import sa.test.model.enums.CustomerType;
import sa.test.service.validation.ExistCustomerType;

public class CreateCustomerRequest {

   @NotBlank
   private String name;

   @NotBlank
   private String document;

   @NotBlank
   @ExistCustomerType
   private String type;

   @NotBlank
   private String password;

   @NotBlank
   private String zipCode;

   @NotBlank
   private String street;

   @NotBlank
   private String streetNumber;

   public Customer toCustomer() {
      Customer customer = new Customer();
      Address address = new Address();
      address.setStreet(street);
      address.setStreetNumber(streetNumber);
      address.setZipCode(zipCode);
      customer.setAddress(address);
      customer.setDocument(document);
      customer.setType(CustomerType.valueOf(type));
      return customer;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDocument() {
      return this.document;
   }

   public void setDocument(String document) {
      this.document = document;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getZipCode() {
      return this.zipCode;
   }

   public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
   }

   public String getStreet() {
      return this.street;
   }

   public void setStreet(String street) {
      this.street = street;
   }

   public String getStreetNumber() {
      return this.streetNumber;
   }

   public void setStreetNumber(String streetNumber) {
      this.streetNumber = streetNumber;
   }

}
