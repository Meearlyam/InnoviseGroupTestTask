package main.java.by.meearlyam.userseditor.exception;

public class UsersEditorControllerException extends Exception {
    /**
     * Constructor with specified string
     *
     * @param message string
     */
    public UsersEditorControllerException(String message) {
        super(message);
    }

    /**
     * Constructor with specified string and exception
     *
     * @param message string
     * @param e       error covered
     */
    public UsersEditorControllerException(String message, Throwable e) {
        super(message, e);
    }
}
