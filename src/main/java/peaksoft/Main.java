package peaksoft;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Config;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Config.getConnection();
        UserService userService = new UserServiceImpl();
        Scanner scanner = new Scanner(System.in);
        while (true){
        System.out.println("Enter command:"+"\n"+
                      "1. Create user table"+"\n"+
                      "2. Drop user table"+"\n"+
                      "3. Save user"+"\n"+
                      "4. Remove user by id"+"\n"+
                      "5. Get all users"+"\n"+
                      "6. Clean user table");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                userService.createUsersTable();
                break;
            case 2:
                userService.dropUsersTable();
                break;
            case 3:
                System.out.println("Enter name ");
                String name = scanner.next();
                System.out.println("Enter las name");
                String lastName = scanner.next();
                System.out.println("Enter age");
                byte age = scanner.nextByte();
                userService.saveUser(name, lastName, age);
                break;
            case 4:
                System.out.println("Enter id");
                long id = scanner.nextLong();
                userService.removeUserById(id);
                break;
            case 5:
                userService.getAllUsers();
                break;
            case 6:
                userService.cleanUsersTable();
                break;
           }
        }
    }
}
