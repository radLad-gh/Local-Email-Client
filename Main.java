import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
		Client userClient = new Client();
		userClient.displayOutbox();
		
		int option;
    do {
			userClient.displayOptions();
			System.out.print("Enter a choice: ");
    	option = scan.nextInt();
      
      

      switch (option)
      {
        case 1: // display outbox
          userClient.displayOutbox();
          break;

        case 2: // open email
          // display iterator variable next to each inbox email 
          userClient.openEmail();
          break;

        case 3: // compose email
          userClient.composeEmail();
          break;

        case 4: // delete
          userClient.deleteEmail();
          //userClient.displayInbox(); // Display updated inbox
          break;

        case 5: // search
          userClient.search();
          break;

        case 6: // exit client
          break;
          
      }
    
    } while(option != 6);
    System.out.println("Logging out.");
    scan.close();
  }
}