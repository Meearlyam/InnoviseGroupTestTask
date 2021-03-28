## Innovise Group test task app

This console application provides the ability to manipulate user data via CRUD operations, 
while saving all the data to a file in human-readable form.

The data you entered is validated for compliance with the specified template.
Only valid data will allow you to create a new user or update the data of the existing one.

When you launch this app, you will see the following menu items:
 1. Create user
 2. Update user by id
 3. Delete user by id
 4. Get and print user by id
 5. Get and print all users
 6. Exit program
 
 To perform an operation, you need to enter its number, press "Enter", and follow the instructions. 
 Mind that entering any symbols other than the menu item numbers will not give any result.
 
 ### Users data file
All users data is stored in src/main/resources/users.txt file. It changes after each create, update or delete user operation.