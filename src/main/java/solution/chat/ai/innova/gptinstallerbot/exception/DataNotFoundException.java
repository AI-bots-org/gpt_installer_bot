package solution.chat.ai.innova.gptinstallerbot.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message + " not found.");
    }

    public DataNotFoundException() {}
}