import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
  private String sender = "Samuel.Menaged@knights.ucf.edu";
	private final File outbox;
	private final File sent;
	private final File spam;
	private File currentFolder;
	private FileWriter composer;
	private ArrayList<Email> emails;
  private Scanner scan;

  public Client() {
  	emails = new ArrayList<Email>();
		outbox = new File("Outbox");
		System.out.println(outbox);
		sent = new File("sent");
		spam = new File("spam");
    scan = new Scanner(System.in);
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		for (File emailFile: outbox.listFiles()) {
			emails.add(new Email(emailFile));
		} 
  }

  public void displayOptions() {
    System.out.println("\nWelcome to your Mail Client\n"
                      + "What would you like to do?\n"
                      + "\t1 - Display Inbox\n"
                      + "\t2 - Display Outbox\n"
                      + "\t3 - Open email\n"
                      + "\t4 - Compose email\n"
                      + "\t5 - Delete email\n"
                      + "\t6 - Search for email\n");

  }

/*
1 recipient datetime
  subject
2 recipient datetime
  subject
*/


  public void displayOutbox() {
		int i = 1;
		for (Email email: emails) {
			System.out.print(i + " ");
			// System.out.println(email.getSender());
			System.out.print(email.getRecipient() + " ");
      System.out.println(email.getDateTime());
			System.out.println("  " + email.getSubject());
			// System.out.println(email.getMessage());
			// System.out.println(email.getSignature());
      ++i;
		}
  }

  public void openEmail() {
      int choice;
      System.out.print("Select an email ID: ");
      choice = scan.nextInt();
			Email chosenEmail = emails.get(choice - 1);
      // System.out.println(email.getID());
			System.out.println("From: " + chosenEmail.getSender());
			System.out.println("To: " + chosenEmail.getRecipient());
      System.out.println("Date: " + chosenEmail.getDateTime());
			System.out.println("Subject: " + chosenEmail.getSubject());
			System.out.println("Message:\n" + chosenEmail.getMessage());
			System.out.println("Sincerely,\n" + chosenEmail.getSignature());
  }

  public Email composeEmail() {
    Email newEmail = new Email(sender);
		emails.add(newEmail);
		System.out.println("ID: " + newEmail.getID());
		try {
			composer = new FileWriter("Outbox/" + newEmail.getID());
			System.out.println(newEmail.getSender() + "\n");
			composer.write(newEmail.getSender() + "\n");
			composer.write(newEmail.getRecipient() + "\n");
			composer.write(newEmail.getDateTime() + "\n");
			composer.write(newEmail.getSubject() + "\n");
    	composer.write(newEmail.getMessage() + "\n");
    	composer.write(newEmail.getSignature() + "\n");
			composer.close();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		

    return newEmail;  
  }

  public void deleteEmail() {
    
    
    //displayInbox(); Show updated inbox
  }

  public void search() {

  }

  
  
}