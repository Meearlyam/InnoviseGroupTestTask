package main.java.by.meearlyam.userseditor.model;

import main.java.by.meearlyam.userseditor.exception.UsersRepositoryException;

import java.util.List;

public interface UsersRepository {
    void createUser(User user) throws UsersRepositoryException;
    User readUser(int userId) throws UsersRepositoryException;
    List<User> readAllUsers() throws UsersRepositoryException;
    void updateUser(int userId, User user) throws UsersRepositoryException;
    void deleteUser(int userId) throws UsersRepositoryException;
}
