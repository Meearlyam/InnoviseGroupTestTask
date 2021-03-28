package main.java.by.meearlyam.userseditor.controller.command;

import main.java.by.meearlyam.userseditor.controller.UsersEditorController;
import main.java.by.meearlyam.userseditor.exception.CommandExecutionException;
import main.java.by.meearlyam.userseditor.exception.UsersEditorControllerException;

public class UpdateUserCommand implements Command {

    private UsersEditorController controller;
    private int userId;
    private String userString;

    public UpdateUserCommand(UsersEditorController controller, int userId, String userString) {
        this.controller = controller;
        this.userId = userId;
        this.userString = userString;
    }

    public void execute() throws CommandExecutionException {
        try {
            controller.updateUser(userId, userString);
        }
        catch (UsersEditorControllerException e) {
            throw new CommandExecutionException(
                    String.format("Update user command exception: %s", e.getMessage())
            );
        }
    }
}
