package sa.test.exception;

public class ObjectNotFoundException extends RuntimeException {
   public ObjectNotFoundException() {
      super("Dado não encontrado");
   }
}
