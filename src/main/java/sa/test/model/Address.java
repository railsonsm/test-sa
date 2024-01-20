package sa.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
   @Column(nullable = false)
   private String zipCode;

   @Column(nullable = false)
   private String street;

   @Column(name = "street_number")
   private String streetNumber;


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
