package main.java.by.meearlyam.userseditor;

import main.java.by.meearlyam.userseditor.controller.UsersEditorController;
import main.java.by.meearlyam.userseditor.controller.command.*;
import main.java.by.meearlyam.userseditor.exception.CommandExecutionException;
import main.java.by.meearlyam.userseditor.model.FileUsersRepository;
import main.java.by.meearlyam.userseditor.model.User;
import main.java.by.meearlyam.userseditor.model.validation.EmailFieldValidator;
import main.java.by.meearlyam.userseditor.model.validation.PhoneNumberFieldValidator;
import main.java.by.meearlyam.userseditor.model.validation.RoleFieldValidator;
import main.java.by.meearlyam.userseditor.util.StringUserParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String MAIN_MENU_STRING = "\nEnter number of the option you want to perform: " +
            "\n\t(1) Create user " +
            "\n\t(2) Update user by id " +
            "\n\t(3) Delete user by id " +
            "\n\t(4) Get and print user by id" +
            "\n\t(5) Get and print all users " +
            "\n\t(6) Exit program";

    private static final String ENTER_USER_STRING =
            "\nEnter user data in the next order: " +
            "\n\tname surname email role1[+role2] phoneNumber1 [phoneNumber2 [phoneNumber3]]" +
            "\n\n Role valid values are: " +
            "\n\t Layer 1. USER, CUSTOMER" +
            "\n\t Layer 2. ADMIN, PROVIDER" +
            "\n\t Layer 3. SUPER_ADMIN" +
            "\n\n Caution: If you enter 2 roles, then one must belong to Layer 1, and the other - to Layer 2!";

    private static final String OPERATION_WITH_ID_TEMPLATE = "%s: range of existing IDs is 0-%d";

    private static final String ENTER_USER_ID_STRING = "\nPlease, enter user ID to perform the operation: ";
    private static final String TRY_ENTER_AGAIN_STRING = "You need to enter data according to the template! Try again.";
    private static final String NO_SUCH_USER_STRING = "There is no user with such ID!";


    private static final String USERS_FILE_NAME = "src/main/resources/users.txt";

    private static UsersEditorController controller;
    private static BufferedReader inputReader;
    private static PrintStream outputStream;

    private static void configureApp() {
        StringUserParser parser = new StringUserParser(
                new EmailFieldValidator(),
                new PhoneNumberFieldValidator(),
                new RoleFieldValidator()
        );
        controller = new UsersEditorController(parser, new FileUsersRepository(parser, USERS_FILE_NAME));
        inputReader = new BufferedReader(new InputStreamReader(System.in));
        outputStream = System.out;
    }

    private static int parseUserIdFromInput(String input) {
        int userId;
        try {
            userId = Integer.parseInt(input);
        } catch (Exception e) {
            userId = -1;
        }
        return userId;
    }

    public static void main(String[] args) {
        configureApp();

        String commandChoice;
        String userString;
        int userId;
        User user;
        Command command;
        boolean isInputInvalid;

        List<User> users = new ArrayList<>();

        try {
            new ReadAllUsersCommand(controller, users).execute();

            while (true) {

                outputStream.println(MAIN_MENU_STRING);
                commandChoice = inputReader.readLine();

                switch (commandChoice) {
                    case "1":
                        outputStream.println("CREATE USER: ");

                        do {
                            isInputInvalid = false;
                            try {
                                outputStream.println(ENTER_USER_STRING);
                                userString = inputReader.readLine();

                                command = new CreateUserCommand(controller, users.size(), userString);
                                command.execute();
                            } catch (CommandExecutionException | IOException e) {
                                isInputInvalid = true;
                                outputStream.println(TRY_ENTER_AGAIN_STRING);
                            }
                        } while (isInputInvalid);

                        outputStream.println("User has been created.");
                        break;

                    case "2":
                        outputStream.print(
                                String.format(OPERATION_WITH_ID_TEMPLATE, "UPDATE USER", users.size() - 1)
                        );
                        outputStream.println(ENTER_USER_ID_STRING);

                        userId = parseUserIdFromInput(inputReader.readLine());

                        if (userId >= 0 && userId < users.size()) {
                            do {
                                isInputInvalid = false;
                                try {
                                    outputStream.println(ENTER_USER_STRING);
                                    userString = inputReader.readLine();

                                    command = new UpdateUserCommand(controller, userId, userString);
                                    command.execute();
                                } catch (CommandExecutionException | IOException e) {
                                    isInputInvalid = true;
                                    outputStream.println(TRY_ENTER_AGAIN_STRING);
                                }
                            } while (isInputInvalid);

                            outputStream.println("User has been updated.");
                        } else {
                            outputStream.println(NO_SUCH_USER_STRING);
                        }
                        break;

                    case "3":
                        outputStream.print(
                                String.format(OPERATION_WITH_ID_TEMPLATE, "DELETE USER", users.size() - 1)
                        );
                        outputStream.println(ENTER_USER_ID_STRING);

                        userId = parseUserIdFromInput(inputReader.readLine());

                        if (userId >= 0 && userId < users.size()) {
                            command = new DeleteUserCommand(controller, userId);
                            command.execute();
                            outputStream.println("User has been deleted.");
                        } else {
                            outputStream.println(NO_SUCH_USER_STRING);
                        }
                        break;

                    case "4":
                        outputStream.print(
                                String.format(OPERATION_WITH_ID_TEMPLATE, "READ USER", users.size() - 1)
                        );
                        outputStream.println(ENTER_USER_ID_STRING);

                        userId = parseUserIdFromInput(inputReader.readLine());

                        if (userId >= 0 && userId < users.size()) {
                            user = new User(userId);
                            command = new ReadUserByIdCommand(controller, user, userId);
                            command.execute();
                            outputStream.println(user);
                        } else {
                            outputStream.println(NO_SUCH_USER_STRING);
                        }
                        break;

                    case "5":
                        outputStream.println("READ ALL USERS:");

                        command = new ReadAllUsersCommand(controller, users);
                        command.execute();
                        for (User usr : users) {
                            outputStream.println(usr);
                        }
                        break;

                    case "6":
                        return;
                    default:
                        break;
                }
            }
        }
        catch (CommandExecutionException | IOException e) {
            outputStream.println(
                    String.format("Exception was thrown in Main: %s.", e.getMessage())
            );
        }
    }
}
