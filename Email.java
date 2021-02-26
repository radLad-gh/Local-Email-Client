import java.time.LocalDateTime;  
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileNotFoundException;

public class Email {

	private Long ID;
	private String sender;
	private String recipient;
	private String subject;
	private String signature;
	private String message;
	private LocalDateTime date;
	private Scanner scan;
	private DateTimeFormatter dtf;
	private Scanner emailScanner;
	private String dateString;

	// create new email from composed
	public Email(String sender) {
		ID = System.currentTimeMillis();
		this.sender = sender;
    scan = new Scanner(System.in);
		System.out.println("Who are you sending this email to?");
		recipient = scan.nextLine();
		System.out.println("Enter Subject");
    subject = scan.nextLine();
		System.out.println("Enter message");
		message = scan.nextLine();
		System.out.println("Enter signature");
    signature = scan.nextLine();


		// update to current time zone
		dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		date = LocalDateTime.now();
		dateString = dtf.format(date);
  }

	// create new email from existing files
	public Email(File email) {
		try {
			emailScanner = new Scanner(email);
			// ID
			System.out.println(Long.parseLong(email.getName().substring(0, 12)));
			ID = Long.parseLong(email.getName().substring(0,12));
			
			// get rid of buffer
			//emailScanner.nextLine();
			// sender
			sender = emailScanner.nextLine();
			// recipient
			recipient = emailScanner.nextLine();
      // DateTime
      dateString = emailScanner.nextLine();
			// subject
			subject = emailScanner.nextLine();
			// message
			message = emailScanner.nextLine();
			// signature
			signature = emailScanner.nextLine();

			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			date = LocalDateTime.now();
		} catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}

  public long getID() {
    return ID;
  }

  public String getSender() {
    return sender;
  }

  public String getRecipient() {
    return recipient;
  }

  public String getSubject() {
    return subject;
  }

  public String getMessage() {
    return message;
  }

  public String getSignature() {
    return signature;
  }

  public String getDateTime() {
    return dateString;
  }
}