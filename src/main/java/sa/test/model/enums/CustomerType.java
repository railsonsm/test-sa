package sa.test.model.enums;

import java.util.Arrays;

public enum CustomerType {
   PJ,PF;

   public static CustomerType get(String enumString) {
      return Arrays.asList(CustomerType.values()).stream().filter(e -> enumString.equalsIgnoreCase(e.name())).findFirst().orElse(null);
   }
}
