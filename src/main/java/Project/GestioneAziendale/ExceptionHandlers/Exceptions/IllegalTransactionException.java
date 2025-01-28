package Project.GestioneAziendale.ExceptionHandlers.Exceptions;

public class IllegalTransactionException extends RuntimeException {

    public IllegalTransactionException(String message) {
        super(message);
    }

}
