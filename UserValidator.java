import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class UserValidator {

    private Scanner loginScanner;
    private String line = "";
    private String loginEmail;
    private Scanner scan = new Scanner(System.in);
    private String tempPW;
    private User user = null;
    private boolean isExistingUser;
    private FileWriter writeNewEmail;
    private Cipher cipher;
    // Add cipher for encryption when testing new/existing password

    public UserValidator() {
        cipher = new Cipher();
        EmailValidator emailValidator = new EmailValidator();
        boolean isValid;
        do {
            System.out.print("Email: ");
            loginEmail = scan.nextLine();
            isValid = emailValidator.validEmail(loginEmail);
            if (!isValid) {
                System.out.println("Invalid Email, Please try again");
            }
        } while (!isValid);

        try {
            File loginsPath = new File("Logins.txt");
            writeNewEmail = new FileWriter("Logins.txt", true);
            loginScanner = new Scanner(loginsPath);
            isExistingUser = validateEmailLogin();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    // METHODS //
    public boolean validateEmailLogin() {
        while (loginScanner.hasNextLine()) {
            line = loginScanner.nextLine();
            if (line.equalsIgnoreCase(loginEmail)) {
                return true;
            }
        }
        return false;
    }

    public User createNewUser() {
        try {
            do {
                System.out.print("User does not exist. Would you like to create one?  (yes/no) ");
                String choice = scan.nextLine();

                if (choice.equalsIgnoreCase("yes")) {
                    passwordConfirmation();
                    user = new User(loginEmail, tempPW);
                    writeNewEmail.append(loginEmail + "\n");
                    System.out.println("User account successfully created!");
                    break;
                } else if (choice.equalsIgnoreCase("no")) {
                    System.out.println("Ok, goodbye!");
                    System.exit(0);
                } else {
                    System.out.println("Invalid response.");
                }
            } while (true);

            writeNewEmail.close();
            return user;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void passwordConfirmation() {
        System.out.print("Enter a password: ");
        tempPW = scan.nextLine();
        System.out.print("Enter password again: ");
        while (!tempPW.equals(scan.nextLine())) {
            System.out.println("Not a match. Try again!\nEnter a password: ");
            tempPW = scan.nextLine();
        }
    }

    public User validateExistingUserPassword() {
        try {
            System.out.print("Welcome back! Please enter your password.\n");
            // Users/GIVENEMAIL/details.txt
            System.out.println("Users/" + loginEmail + "/details.txt");
            File detailsPath = new File("Users/" + loginEmail + "/details.txt");
            Scanner detailsScanner = new Scanner(detailsPath);
            String userPassword = detailsScanner.nextLine();
            String passwordAttempt;
            do {
                System.out.print("Password: ");
                passwordAttempt = scan.nextLine();
                passwordAttempt = cipher.encryptString(passwordAttempt);
                if ((!userPassword.equals(passwordAttempt))) {
                    System.out.println("Incorrect password! Please try again.");
                }
            } while (!userPassword.equals(passwordAttempt));

            // Create user
            user = new User(new File("Users/" + loginEmail));
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean isExistingUser() {
        return isExistingUser;
    }
}
