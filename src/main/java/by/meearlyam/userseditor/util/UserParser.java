package main.java.by.meearlyam.userseditor.util;

import main.java.by.meearlyam.userseditor.exception.UserParsingException;
import main.java.by.meearlyam.userseditor.model.User;

public interface UserParser {
    User parseUser(int userParser, Object input) throws UserParsingException;
}
