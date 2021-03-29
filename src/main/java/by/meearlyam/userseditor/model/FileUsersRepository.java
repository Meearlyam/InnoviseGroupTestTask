package main.java.by.meearlyam.userseditor.model;

import main.java.by.meearlyam.userseditor.exception.UserParsingException;
import main.java.by.meearlyam.userseditor.exception.UsersRepositoryException;
import main.java.by.meearlyam.userseditor.util.UserParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUsersRepository implements UsersRepository {
    private String filename;
    private UserParser userParser;

    public FileUsersRepository(UserParser userParser, String filename) {
        this.filename = filename;
        this.userParser = userParser;
    }

    private String getStringFromUser(User user) {
        String roles = user.getRoles().size() > 1 ?
                String.format("%s+%s", user.getRoles().get(0), user.getRoles().get(1)) :
                user.getRoles().get(0).toString();

        StringBuilder phoneNumbers = new StringBuilder();
        for(String phone : user.getMobilePhones()) {
            phoneNumbers.append(" ").append(phone);
        }

        return String.format("%s %s %s %s %s", user.getName(),
                user.getSurname(), user.getEmail(), roles, phoneNumbers.toString());
    }

    private void updateUsersFile(List<User> users) throws UsersRepositoryException {
        try (BufferedWriter usersFile = new BufferedWriter(new FileWriter(filename))) {
            for(User user: users) {
                usersFile.append(getStringFromUser(user));
                usersFile.append("\n");
            }
        }
        catch (IOException e) {
            throw new UsersRepositoryException("Cannot update users file!");
        }
    }

    public void createUser(User user) throws UsersRepositoryException {
        try(BufferedWriter usersFile = new BufferedWriter(new FileWriter(filename, true))) {
            String userLine = getStringFromUser(user);
            usersFile.append(userLine);
            usersFile.append("\n");
        }
        catch(IOException e) {
            throw new UsersRepositoryException("Cannot add new user to the repository!");
        }
    }

    public User readUser(int userId) throws UsersRepositoryException {
        List<User> users = readAllUsers();
        for(User user : users) {
            if(user.getId() == userId) {
                return user;
            }
        }
        throw new UsersRepositoryException(String.format("There is no user with id=%d", userId));
    }

    public List<User> readAllUsers() throws UsersRepositoryException {
        try (BufferedReader usersFile = new BufferedReader(new FileReader(filename))) {
            List<User> users = new ArrayList<>();
            String userLine = usersFile.readLine();
            int userId = 0;
            while(userLine != null) {
                if(userLine.length() == 0 || (userLine.split("[ ]+").length == 0)) {
                    userLine = usersFile.readLine();
                    continue;
                }
                users.add(
                        userParser.parseUser(userId, userLine)
                );
                userLine = usersFile.readLine();
                userId++;
            }
            return users;
        }
        catch (UserParsingException | IOException e) {
            throw new UsersRepositoryException(
                    String.format("Cannot read all users from file: %s!", e.getMessage()));
        }
    }

    public void updateUser(int userId, User editedUser) throws UsersRepositoryException {
        List<User> users = readAllUsers();
        for(User user : users) {
            if(user.getId() == userId) {
                user.setName(editedUser.getName());
                user.setSurname(editedUser.getSurname());
                user.setEmail(editedUser.getEmail());
                user.setRoles(editedUser.getRoles());
                user.setMobilePhones(editedUser.getMobilePhones());
                break;
            }
        }
        updateUsersFile(users);
    }

    public void deleteUser(int userId) throws UsersRepositoryException {
        List<User> users = readAllUsers();
        for(User user : users) {
            if(user.getId() == userId) {
                users.remove(userId);
                break;
            }
        }
        updateUsersFile(users);
    }
}
