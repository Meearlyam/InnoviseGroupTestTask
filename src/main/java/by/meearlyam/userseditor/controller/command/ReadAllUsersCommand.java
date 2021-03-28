package main.java.by.meearlyam.userseditor.controller.command;

import main.java.by.meearlyam.userseditor.controller.UsersEditorController;
import main.java.by.meearlyam.userseditor.exception.CommandExecutionException;
import main.java.by.meearlyam.userseditor.exception.UsersEditorControllerException;
import main.java.by.meearlyam.userseditor.model.User;

import java.util.List;

public class ReadAllUsersCommand implements Command {

    private UsersEditorController controller;
    private List<User> users;

    public ReadAllUsersCommand(UsersEditorController controller, List<User> users) {
        this.controller = controller;
        this.users = users;
    }

    public void execute() throws CommandExecutionException {
        users.clear();
        try {
            users.addAll(controller.readAllUsers());

        }
        catch (UsersEditorControllerException e) {
            throw new CommandExecutionException(
                    String.format("Read all users command exception: %s", e.getMessage())
            );
        }
    }
}
