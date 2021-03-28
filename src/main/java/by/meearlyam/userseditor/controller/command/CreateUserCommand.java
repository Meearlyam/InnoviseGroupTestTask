package main.java.by.meearlyam.userseditor.controller.command;

import main.java.by.meearlyam.userseditor.controller.UsersEditorController;
import main.java.by.meearlyam.userseditor.exception.CommandExecutionException;
import main.java.by.meearlyam.userseditor.exception.UsersEditorControllerException;

public class CreateUserCommand implements Command {

    private UsersEditorController controller;
    private int userId;
    private String inputString;

    public CreateUserCommand(UsersEditorController controller, int userId, String inputString) {
        this.controller = controller;
        this.userId = userId;
        this.inputString = inputString;
    }

    public void execute() throws CommandExecutionException {
        try {
            controller.createUser(userId, inputString);
        }
        catch (UsersEditorControllerException e) {
            throw new CommandExecutionException(
                    String.format("Create user command exception: %s", e.getMessage())
            );
        }
    }
}
