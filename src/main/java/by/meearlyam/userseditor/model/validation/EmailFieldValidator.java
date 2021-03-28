package main.java.by.meearlyam.userseditor.model.validation;

public class EmailFieldValidator implements FieldValidator {
    public boolean isValid(String input) {
        return input.matches("(.+)@(.+)\\.(.+)");
    }
}
