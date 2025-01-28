package Project.GestioneAziendale.ExceptionHandlers.Exceptions;

public class NewsNotFoundException extends RuntimeException {
  public NewsNotFoundException(String message) {
    super(message);
  }
}
