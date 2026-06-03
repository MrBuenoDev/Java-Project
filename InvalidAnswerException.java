/**
 * Exception: InvalidAnswerException
 * SDG 3 - Good Health and Well-Being Application
 * Creator: Member 3  |  Tester: Member 5
 */
public class InvalidAnswerException extends RuntimeException {
    private final String invalidInput;

    public InvalidAnswerException(String message) {
        super(message);
        this.invalidInput = "";
    }

    public InvalidAnswerException(String message, String invalidInput) {
        super(message);
        this.invalidInput = invalidInput;
    }

    public String getInvalidInput() { return invalidInput; }

    @Override
    public String toString() {
        return "InvalidAnswerException: " + getMessage()
             + " [input: '" + invalidInput + "']";
    }
}