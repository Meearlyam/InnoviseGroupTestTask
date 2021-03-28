package main.java.by.meearlyam.userseditor.model.validation;

import main.java.by.meearlyam.userseditor.model.RoleEnum;

public class RoleFieldValidator implements FieldValidator {
    public boolean isValid(String input) {
        for(RoleEnum role : RoleEnum.values()) {
            if(role.toString().equalsIgnoreCase(input)) {
                return true;
            }
        }

        return false;
    }
}
