import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Properties;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.InternetAddress;

public class EmailData {
  private String Username;
  private String Password;
  private String Host;
  private String MailStoreType;
  private Folder EmailFolder;
  private BufferedReader Reader;
  private Store store;
  private Session emailSession;
  private Date[] messageDates;
  private String[] senders;
  private String[] subjects;
  private Message[] messages;
  private Message message;
  private int Limit;
  private Properties properties;
  private Part part;
  private int index;

  public void createProperties() {
    properties = new Properties();
  }

  public void setMailStoreType(String mailStoreType) {
    try {
      MailStoreType = mailStoreType;

      throw new NoSuchProviderException();
    } catch (NoSuchProviderException e) {
    }
  }

  public void setHost(String host) {
    Host = host;
  }

  public void setUsername(String username) {
    Username = username;
  }

  public void setPassword(String password) {
    Password = password;
  }

  public void createSession() {
    emailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(Username, Password);
      }
    });
  }

  public void imapsConnect() {
    try {
      store = emailSession.getStore("imaps");
      store.connect(Host, Username, Password);

      throw new MessagingException();
    } catch (MessagingException e) {
    }
  }

  public void createFolder() {
    try {
      EmailFolder = store.getFolder("INBOX");
      EmailFolder.open(Folder.READ_ONLY);
      Reader = new BufferedReader(new InputStreamReader(System.in));

      throw new MessagingException();
    } catch (MessagingException e) {
    }
  }

  public void setMessageArray() {
    try {
      messages = EmailFolder.getMessages();

      throw new MessagingException();
    } catch (MessagingException e) {
    }
  }

  public void setLimit(int limit) {
    Limit = limit;
  }

  public void setMessageDate() {
    index = 0;
    messageDates = new Date[Limit];
    for (int i = messages.length-1; i < messages.length; i--) {
      try {
        messageDates[index] = messages[i].getSentDate();

        throw new MessagingException();
      } catch (MessagingException e) {
      }
      index++;
      if(index == Limit) { //how many recent messages you want to collect
        break;
      }
    }
  }

  public void setMessageSender() {
    index = 0;
    senders = new String[Limit];
    for (int i = messages.length-1; i < messages.length; i--) {
      try {
        senders[index] = InternetAddress.toString(messages[i].getFrom());

        throw new MessagingException();
      } catch (MessagingException e) {
      }
      index++;
      if(index == Limit) { //how many recent messages you want to collect
        break;
      }
    }
  }

  public void setMessageSubject() {
    index = 0;
    subjects = new String[Limit];
    for (int i = messages.length-1; i < messages.length; i--) {
      try {
        subjects[index] = messages[i].getSubject();

        throw new MessagingException();
      } catch (MessagingException e) {
      }
      index++;
      if(index == Limit) { //how many recent messages you want to collect
        break;
      }
    }
  }

  public void closeStore() {
    try {
      EmailFolder.close(false);

      throw new MessagingException();
    } catch (MessagingException e) {
    }
  }

  public void closeFolder() {
    try {
      store.close();

      throw new MessagingException();
    } catch (MessagingException e) {
    }
  }

  public String[] getSenders() {
    return senders;
  }

  public Date[] getDates() {
    return messageDates;
  }

  public String[] getSubjects() {
    return subjects;
  }

  public Message[] getMessages() {
    return messages;
  }
}
