import java.util.Scanner;

public class EmailMain {
  public static void main(String[] args) {
    String username;
    String password;
    int limit;
    int num;
    EmailData email = new EmailData();
    Scanner scan = new Scanner(System.in);
    System.out.println("\nPlease enter your e-mail address: ");
    username = scan.next();
    System.out.println("\nPlease enter your app password: ");
    password = scan.next();
    System.out.println("\nPlease enter the amount of e-mails to read: ");
    limit = scan.nextInt();
    email.setUsername(username);
    email.setPassword(password);
    email.setLimit(limit);
    email.createProperties();
    email.setMailStoreType("pop3");
    email.setHost("imap.gmail.com");
    email.createSession();
    email.imapsConnect();
    email.createFolder();
    email.setMessageArray();
    email.setMessageDate();
    email.setMessageSender();
    email.setMessageSubject();
    email.closeStore();
    email.closeFolder();
    System.out.println();
    for(int i = 0; i < limit; i++) {
      System.out.println("-----------------------------------------------------------------\n");
      System.out.println("<Message " + (i + 1) + ">");
      System.out.println("From: " + email.getSender()[i]);
      System.out.println("On: " + email.getDate()[i]);
      System.out.println("Message: \n" + email.getMessageArray()[i] + "\n");
    }
    System.out.println("-----------------------------------------------------------------\n");
  }
}
