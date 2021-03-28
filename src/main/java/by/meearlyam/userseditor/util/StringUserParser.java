package main.java.by.meearlyam.userseditor.util;

import main.java.by.meearlyam.userseditor.exception.UserParsingException;
import main.java.by.meearlyam.userseditor.model.RoleEnum;
import main.java.by.meearlyam.userseditor.model.User;
import main.java.by.meearlyam.userseditor.model.validation.FieldValidator;
import main.java.by.meearlyam.userseditor.model.validation.RoleFieldValidator;

import java.util.ArrayList;
import java.util.List;

public class StringUserParser implements UserParser {

    private FieldValidator emailValidator;
    private FieldValidator phoneNumberValidator;
    private FieldValidator roleFieldValidator;

    public StringUserParser(FieldValidator emailValidator,
                            FieldValidator phoneNumberValidator,
                            RoleFieldValidator roleFieldValidator) {

        this.emailValidator = emailValidator;
        this.phoneNumberValidator = phoneNumberValidator;
        this.roleFieldValidator = roleFieldValidator;
    }

    public User parseUser(int userId, Object input) throws UserParsingException {
        String name;
        String surname;
        String email;
        List<RoleEnum> roles;
        List<String> phoneNumbers;

        String[] tokens = ((String)input).split("[ ]+");

        if (tokens.length < 4) {
            throw new UserParsingException("Input must contain at least 4 values, separated by space, but have had less!");
        }
        if (tokens.length > 7) {
            throw new UserParsingException("Too many arguments: you can only set 2 roles and 3 phone numbers max!");
        }

        name = tokens[0];
        surname = tokens[1];

        email = tokens[2];
        if (!emailValidator.isValid(email)) {
            throw new UserParsingException("Email is not valid!");
        }

        roles = new ArrayList<>();
        String[] rolesString = tokens[3].split("\\+");
        if (rolesString.length > 2) {
            throw new UserParsingException("You can set 2 roles max!");
        }
        for (String rString : rolesString) {
            if(roleFieldValidator.isValid(rString)) {
                roles.add(RoleEnum.valueOf(rString));
            }
            else {
                throw new UserParsingException(String.format("There is no such role as %s!", rString));
            }
        }

        // if 1st of 2nd role layer value is 3 or their layer values are equal
        if (roles.size() > 1 && (roles.get(0).getLayer() == 3 || roles.get(1).getLayer() == 3 ||
                roles.get(0).getLayer() == roles.get(1).getLayer())) {
            throw new UserParsingException("User roles conflict!");
        }

        phoneNumbers = new ArrayList<>();
        for (int i = 4; i < tokens.length; i++) {
            if (phoneNumberValidator.isValid(tokens[i])) {
                phoneNumbers.add(tokens[i]);
            }
            else {
                throw new UserParsingException("Phone number format exception!");
            }
        }

        return new User(userId, name, surname, email, roles, phoneNumbers);
    }
}
