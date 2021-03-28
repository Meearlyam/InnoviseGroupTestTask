package main.java.by.meearlyam.userseditor.exception;

public class CommandExecutionException extends Exception {
    /**
     * Constructor with specified string
     *
     * @param message string
     */
    public CommandExecutionException(String message) {
        super(message);
    }

    /**
     * Constructor with specified string and exception
     *
     * @param message string
     * @param e       error covered
     */
    public CommandExecutionException(String message, Throwable e) {
        super(message, e);
    }
}
