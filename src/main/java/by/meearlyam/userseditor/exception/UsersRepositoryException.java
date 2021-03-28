package main.java.by.meearlyam.userseditor.exception;

public class UsersRepositoryException extends Exception {
    /**
     * Constructor with specified string
     *
     * @param message string
     */
    public UsersRepositoryException(String message) {
        super(message);
    }

    /**
     * Constructor with specified string and exception
     *
     * @param message string
     * @param e       error covered
     */
    public UsersRepositoryException(String message, Throwable e) {
        super(message, e);
    }
}
