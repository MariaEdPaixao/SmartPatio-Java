package fiap.com.br.SmartPatio.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("Usuário não encontrado");
    }
}
