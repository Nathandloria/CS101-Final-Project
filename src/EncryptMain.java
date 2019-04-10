import java.util.Scanner;
import java.io.IOException;
import javax.mail.Multipart;

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
    System.out.println();
    for(int i = limit - 1; i > -1; i--) {
      System.out.println("-----------------------------------------------------------------");
      System.out.println("\n<Message " + (i + 1) + ">");
      System.out.println("From: " + email.getSenders()[i]);
      System.out.println("On: " + email.getDates()[i]);
      System.out.println("Subject: " + email.getSubjects()[i]);
      try {
        System.out.println("Message: \n\n" + ((Multipart)email.getMessages()[email.getMessages().length - 1 - i].getContent()).getBodyPart(0).getContent() + "\n");
      } catch (Exception x) {
        System.out.println(x);
      }
    }
    System.out.println("-----------------------------------------------------------------");
    email.closeStore();
    email.closeFolder();


    char[] word = email.setMessageArray.toCharArray();
    int num = 5;

    String code;
    

    System.out.println("\nEncrypting your email... ");
    encrypt(word, email.setMessageArray, num);

  }
}

static String
