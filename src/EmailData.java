import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.lang.Object;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Authenticator;

public class EmailData {
  private String Username;
  private String Password;
  private String Host;
  private String MailStoreType;
  private Folder EmailFolder;
  private BufferedReader Reader;
  private Store store;
  private Session emailSession;
  private Session session;
  private Date[] messageDates;
  private String[] senders;
  private String[] subjects;
  private Message[] messages;
  private Message message;
  private int Limit;
  private Properties properties;
  private Part part;
  private int index;
  private String[] messagecontent;
  private MimeMessage usermessage;
  private int count;

  public void createProperties() {
    properties = new Properties();
  }

  public void setMailStoreType(String mailStoreType) {
    try {
      MailStoreType = mailStoreType;
    } catch (Exception x) {
      System.out.println(x);
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
    } catch (Exception x) {
      System.out.println(x);
    }
  }

  public void createFolder() {
    try {
      EmailFolder = store.getFolder("INBOX");
      EmailFolder.open(Folder.READ_ONLY);
      Reader = new BufferedReader(new InputStreamReader(System.in));
    } catch (Exception x) {
      System.out.println(x);
    }
  }

  public void setMessageArray() {
    try {
      messages = EmailFolder.getMessages();
    } catch (Exception x) {
      System.out.println(x);
    }
  }

  public void setMessageContent() {
    index = 0;
    messagecontent = new String[Limit];
    for (int i = 20; i >= 0; i--) {
      try {
        if (messages[i].getDescription().equals("cs101alg")) {
          messagecontent[index] = (String)((Object)messages[i].getContent());
          index++;
        }
      } catch (Exception x) {
        System.out.println(x);
      }
      if(index == Limit) {
        break;
      }
    }
  }

  public void setLimit(int limit) {
    Limit = limit;
  }

  public void setCount() {
    count = 0;
    for (int i = 20; i >= 0; i--) {
      try {
        if (messages[i].getDescription().equals("cs101alg")) {
          count++;
        }
      } catch (Exception x) {
        System.out.println(x);
      }
    }
    System.out.println(count);
  }

  public int getCount() {
    return count;
  }

  public void setMessageDate() {
    index = 0;
    messageDates = new Date[Limit];
    for (int i = 20; i >= 0; i--) {
      try {
        if (messages[i].getDescription().equals("cs101alg")) {
          messageDates[index] = messages[i].getSentDate();
          index++;
        }
      } catch (Exception x) {
        System.out.println(x);
      }
      if(index == Limit) {
        break;
      }
    }
  }

  public void setMessageSender() {
    index = 0;
    senders = new String[Limit];
    for (int i = 20; i >= 0; i--) {
      try {
        if (messages[i].getDescription().equals("cs101alg")) {
          senders[index] = InternetAddress.toString(messages[i].getFrom());
          index++;
        }
      } catch (Exception x) {
        System.out.println(x);
      }
      if(index == Limit) {
        break;
      }
    }
  }

  public void setMessageSubject() {
    index = 0;
    subjects = new String[Limit];
    for (int i = 20; i >= 0; i--) {
      try {
        if (messages[i].getDescription().equals("cs101alg")) {
          subjects[index] = messages[i].getSubject();
          index++;
        }
      } catch (Exception x) {
        System.out.println(x);
      }
      if(index == Limit) {
        break;
      }
    }
  }

  public void closeStore() {
    try {
      EmailFolder.close(false);
    } catch (Exception x) {
      System.out.println(x);
    }
  }

  public void closeFolder() {
    try {
      store.close();
    } catch (Exception x) {
      System.out.println(x);
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

  public String[] getMessageContent() {
    return messagecontent;
  }

  public void sendEmail(String recipient, String subject, String message) {
    try {
      Properties prop = new Properties();
      prop.put("mail.smtp.auth", "true");
      prop.put("mail.smtp.starttls.enable", "true");
      prop.put("mail.smtp.host", "smtp.gmail.com");
      prop.put("mail.smtp.port", "587");
      prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

      session = Session.getInstance(prop, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(Username, Password);
        }
      });

      usermessage = new MimeMessage(session);
      usermessage.setFrom(new InternetAddress(Username));
      usermessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
      usermessage.setSubject(subject);
      usermessage.setText(message);
      usermessage.setDescription("cs101alg");
      usermessage.saveChanges();
      Transport.send(usermessage);
      System.out.println("\nMessage sent successfully!");
    } catch (Exception x) {
      System.out.println(x);
    }
  }
}
