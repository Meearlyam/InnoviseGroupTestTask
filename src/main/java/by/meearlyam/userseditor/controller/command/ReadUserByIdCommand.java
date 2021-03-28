package main.java.by.meearlyam.userseditor.controller.command;

import main.java.by.meearlyam.userseditor.controller.UsersEditorController;
import main.java.by.meearlyam.userseditor.exception.CommandExecutionException;
import main.java.by.meearlyam.userseditor.exception.UsersEditorControllerException;
import main.java.by.meearlyam.userseditor.model.User;

public class ReadUserByIdCommand implements Command {

    private UsersEditorController controller;
    private User user;
    private int userId;

    public ReadUserByIdCommand(UsersEditorController controller, User user, int userId) {
        this.controller = controller;
        this.user = user;
        this.userId = userId;
    }

    public void execute() throws CommandExecutionException {
        try {
            User readUser = controller.readUserById(userId);
            user.setName(readUser.getName());
            user.setSurname(readUser.getSurname());
            user.setEmail(readUser.getEmail());
            user.setRoles(readUser.getRoles());
            user.setMobilePhones(readUser.getMobilePhones());
        }
        catch (UsersEditorControllerException e) {
            throw new CommandExecutionException(
                    String.format("Read user by ID command exception: %s", e.getMessage())
            );
        }
    }
}
