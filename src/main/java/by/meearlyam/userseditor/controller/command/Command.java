package main.java.by.meearlyam.userseditor.controller.command;

import main.java.by.meearlyam.userseditor.exception.CommandExecutionException;

public interface Command {
    void execute() throws CommandExecutionException;
}
