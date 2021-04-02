import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class User {
	// email
	private String email;
	// password
	private String password;
	private FileWriter writer;
	private Scanner fileScanner;
	private String userDir;
	private Cipher cipher;
	// user is a folder
	// Folders - Inbox, outbox, spam, favorites

  // Existing user 
	public User(File userFile) {
		try {
			cipher = new Cipher();
			fileScanner = new Scanner(new File(userFile.getPath() + "/details.txt"));
			this.password = cipher.decryptString(fileScanner.nextLine());
			this.email = fileScanner.nextLine();
			this.userDir = "Users/" + this.email;	
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}

  // Creating a new user
  public User(String Email, String Password) {
		cipher = new Cipher();
		this.email = Email;
    this.password = Password;

		//  EX. Users/Samuel.Menaged@knights.edu
		userDir = "Users/" + Email;
		try {

			new File(userDir).mkdir();
    	new File(userDir + "/Inbox").mkdir();
			new File(userDir + "/Outbox").mkdir();
			new File(userDir + "/Spam").mkdir();
			new File(userDir + "/Favorites").mkdir();
			writer = new FileWriter(userDir + "/details.txt");
			writer.write(cipher.encryptString(password) + "\n");
			writer.write(Email + "\n");

			writer.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    
  }

  public String getEmail() {
    return email;
  }
	public String getDir() {
		return userDir;
	}
}
