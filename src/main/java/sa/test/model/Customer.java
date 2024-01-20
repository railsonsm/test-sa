package sa.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import sa.test.model.enums.CustomerType;

@Entity
public class Customer {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(unique = true, nullable = false)
   private String document;

   @Embedded
   private Address address;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private CustomerType type;

   public Customer() {
   }

   public Customer(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getDocument() {
      return this.document;
   }

   public void setDocument(String document) {
      this.document = document;
   }

   public Address getAddress() {
      return this.address;
   }

   public void setAddress(Address address) {
      this.address = address;
   }

   public CustomerType getType() {
      return this.type;
   }

   public void setType(CustomerType type) {
      this.type = type;
   }

}
