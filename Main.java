import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

// register
// login
class Main {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Client userClient;

    UserValidator userValidator = new UserValidator();

    User user = null;

    if (userValidator.isExistingUser()) {
      // Prompt existing user for password
      user = userValidator.validateExistingUserPassword();
    } else {
      user = userValidator.createNewUser();
    }

    // Instantiate client with user
    if (user != null) {
      userClient = new Client(user);
    } else {
      System.out.println("User error.");
      scan.close();
      return;
    }

    userClient.runMenu();

    System.out.println("Logging out.");
    scan.close();
  }
}
