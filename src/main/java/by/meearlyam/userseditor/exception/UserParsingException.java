package main.java.by.meearlyam.userseditor.exception;

public class UserParsingException extends Exception {
    /**
     * Constructor with specified string
     *
     * @param message string
     */
    public UserParsingException(String message) {
        super(message);
    }

    /**
     * Constructor with specified string and exception
     *
     * @param message string
     * @param e       error covered
     */
    public UserParsingException(String message, Throwable e) {
        super(message, e);
    }
}
