package main.java.by.meearlyam.userseditor.controller;

import main.java.by.meearlyam.userseditor.exception.UserParsingException;
import main.java.by.meearlyam.userseditor.exception.UsersEditorControllerException;
import main.java.by.meearlyam.userseditor.exception.UsersRepositoryException;
import main.java.by.meearlyam.userseditor.model.User;
import main.java.by.meearlyam.userseditor.model.UsersRepository;
import main.java.by.meearlyam.userseditor.util.UserParser;

import java.util.List;

public class UsersEditorController {

    private UsersRepository repository;
    private UserParser userParser;

    public UsersEditorController(UserParser userParser, UsersRepository repository) {
        this.userParser = userParser;
        this.repository = repository;
    }

    public void createUser(int userId, String input) throws UsersEditorControllerException {
        try {
            User newUser = userParser.parseUser(userId, input);
            repository.createUser(newUser);
        }
        catch (UserParsingException | UsersRepositoryException e) {
            throw new UsersEditorControllerException(
                    String.format("Controller exception while creating user: %s", e.getMessage())
            );
        }
    }

    public void updateUser(int userId, String userString) throws UsersEditorControllerException {
        try {
            repository.updateUser(userId, userParser.parseUser(userId, userString));
        }
        catch (UsersRepositoryException | UserParsingException e) {
            throw new UsersEditorControllerException(
                    String.format("Controller exception while updating user: %s", e.getMessage())
            );
        }
    }

    public void deleteUser(int userId) throws UsersEditorControllerException {
        try {
            repository.deleteUser(userId);
        }
        catch (UsersRepositoryException e) {
            throw new UsersEditorControllerException(
                    String.format("Controller exception while deleting user: %s", e.getMessage())
            );
        }
    }

    public User readUserById(int userId) throws UsersEditorControllerException {
        try {
            return repository.readUser(userId);
        }
        catch (UsersRepositoryException e) {
            throw new UsersEditorControllerException(
                    String.format("Controller exception while reading by user id: %s", e.getMessage())
            );
        }
    }

    public List<User> readAllUsers() throws UsersEditorControllerException {
        try {
            return repository.readAllUsers();
        }
        catch (UsersRepositoryException e) {
            throw new UsersEditorControllerException(
                    String.format("Controller exception while reading all users: %s", e.getMessage())
            );
        }
    }
}
