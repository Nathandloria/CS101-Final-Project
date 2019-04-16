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
  private int Limit;
  private Properties properties;
  private int index;
  private String[] messagecontent;
  private MimeMessage usermessage;
  private int count;
  private AlgorithmOne alg1;
  private FileWriter writer;

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

  public void setCount() {
    count = 0;
    for (int i = messages.length - 11; i < messages.length; i++) {
      try {
        if (messages[i].getDescription().contains("cs101alg") == true) {
          count++;
        }
      } catch (Exception x) {
      }
    }
    if (count == 0) {
      System.out.println("No encrypted messages found!");
      System.exit(0);
    }
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

  public int getCount() {
    return count;
  }

  public void setMessageArray() {
    try {
      messages = EmailFolder.getMessages();
    } catch (Exception x) {
      System.out.println(x);
    }
  }

  public void setMessageContent() {
    alg1 = new AlgorithmOne();
    index = 0;
    messagecontent = new String[Limit];
    for (int i = messages.length - 11; i < messages.length; i++) {
      try {
        if (messages[i].getDescription().equals("cs101alg1") == true) {
          messagecontent[index] = alg1.unencript((String)((Object)messages[i].getContent()));
          index++;
        } else if (messages[i].getDescription().contains("cs101alg")){
          messagecontent[index] = (String)((Object)messages[i].getContent());
          index++;
        }
        if(index == Limit) {
          break;
        }
      } catch (Exception x) {
      }
    }
  }

  public void setLimit(int limit) {
    Limit = limit;
  }

  public void setMessageDate() {
    index = 0;
    messageDates = new Date[Limit];
    for (int i = messages.length - 11; i < messages.length; i++) {
      try {
        if (messages[i].getDescription().contains("cs101alg") == true) {
          messageDates[index] = messages[i].getSentDate();
          index++;
        }
      } catch (Exception x) {
      }
      if(index == Limit) {
        break;
      }
    }
  }

  public void setMessageSender() {
    index = 0;
    senders = new String[Limit];
    for (int i = messages.length - 11; i < messages.length; i++) {
      try {
        if (messages[i].getDescription().contains("cs101alg") == true) {
          senders[index] = InternetAddress.toString(messages[i].getFrom());
          index++;
        }
      } catch (Exception x) {
      }
      if(index == Limit) {
        break;
      }
    }
  }

  public void setMessageSubject() {
    index = 0;
    subjects = new String[Limit];
    for (int i = messages.length - 11; i < messages.length; i++) {
      try {
        if (messages[i].getDescription().contains("cs101alg") == true) {
          subjects[index] = messages[i].getSubject();
          index++;
        }
      } catch (Exception x) {
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

  public void sendEmail(String recipient, String subject, String message, String id) {
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
      usermessage.saveChanges();
      usermessage.setDescription(id);
      Transport.send(usermessage);
      System.out.println("\nMessage sent successfully!");
    } catch (Exception x) {
      System.out.println(x);
    }
  }

  public void keyLogger(int num, String key) {
    try {
      writer = new FileWriter("../data/log.txt", true);
      writer.write("<MESSAGE>" + "\n<KEY>" + key + "</KEY>" + "\n<TIME>"
      + usermessage.getSentDate() + "</TIME>" + "\n<ALG>" + num + "</ALG>"
      + "\n</MESSAGE>" + "\n");
      writer.close();
    } catch (Exception x) {
      System.out.println(x);
    }
  }

  public void keyLogger(int num, int key) {
    try {
      writer = new FileWriter("../data/log.txt", true);
      writer.write("<MESSAGE>" + "\n<KEY>" + key + "</KEY>" + "\n<TIME>"
      + usermessage.getSentDate() + "</TIME>" + "\n<ALG>" + num + "</ALG>"
      + "\n</MESSAGE>" + "\n");
      writer.close();
    } catch (Exception x) {
      System.out.println(x);
    }
  }
}
