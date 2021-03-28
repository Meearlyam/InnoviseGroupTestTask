package main.java.by.meearlyam.userseditor.controller.command;

import main.java.by.meearlyam.userseditor.controller.UsersEditorController;
import main.java.by.meearlyam.userseditor.exception.CommandExecutionException;
import main.java.by.meearlyam.userseditor.exception.UsersEditorControllerException;

public class DeleteUserCommand implements Command {

    private UsersEditorController controller;
    private int userId;

    public DeleteUserCommand(UsersEditorController controller, int userId) {
        this.controller = controller;
        this.userId = userId;
    }

    public void execute() throws CommandExecutionException {
        try {
            controller.deleteUser(userId);
        }
        catch (UsersEditorControllerException e) {
            throw new CommandExecutionException(
                    String.format("Delete user command exception: %s", e.getMessage())
            );
        }
    }
}
