package main.java.by.meearlyam.userseditor.model.validation;

import main.java.by.meearlyam.userseditor.model.validation.FieldValidator;

public class PhoneNumberFieldValidator implements FieldValidator {
    public boolean isValid(String input) {
        return input.matches("375[\\d]{9}");
    }
}
