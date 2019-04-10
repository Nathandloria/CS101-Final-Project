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
import javax.mail.Multipart;
import javax.mail.internet.*;
import javax.activation.*;

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
  private String[] messagecontent;
  private MimeMessage usermessage;

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
    for (int i = messages.length-1; i < messages.length; i--) {
      try {
        if (((Multipart)(messages[i].getContent())).getBodyPart(0).getContentType().toUpperCase().equals("TEXT/PLAIN; CHARSET=UTF-8")) {
          messagecontent[index] = (String)((Multipart)messages[i].getContent()).getBodyPart(0).getContent();
        } else {
          index--;
        }
      } catch (Exception x) {
        System.out.println(x);
      }
      index++;
      if(index == Limit) {
        break;
      }
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
        if (((Multipart)(messages[i].getContent())).getBodyPart(0).getContentType().toUpperCase().equals("TEXT/PLAIN; CHARSET=UTF-8")) {
          messageDates[index] = messages[i].getSentDate();
        } else {
          index--;
        }
      } catch (Exception x) {
        System.out.println(x);
      }
      index++;
      if(index == Limit) {
        break;
      }
    }
  }

  public void setMessageSender() {
    index = 0;
    senders = new String[Limit];
    for (int i = messages.length-1; i < messages.length; i--) {
      try {
        if (((Multipart)(messages[i].getContent())).getBodyPart(0).getContentType().toUpperCase().equals("TEXT/PLAIN; CHARSET=UTF-8")) {
          senders[index] = InternetAddress.toString(messages[i].getFrom());
        } else {
          index--;
        }
      } catch (Exception x) {
        System.out.println(x);
      }
      index++;
      if(index == Limit) {
        break;
      }
    }
  }

  public void setMessageSubject() {
    index = 0;
    subjects = new String[Limit];
    for (int i = messages.length-1; i < messages.length; i--) {
      try {
        if (((Multipart)(messages[i].getContent())).getBodyPart(0).getContentType().toUpperCase().equals("TEXT/PLAIN; CHARSET=UTF-8")) {
          subjects[index] = messages[i].getSubject();
        } else {
          index--;
        }
      } catch (Exception x) {
        System.out.println(x);
      }
      index++;
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

  public void sendEmail(String username, String recipient, String subject, String message) {
    try {
      usermessage = new MimeMessage(emailSession);
      usermessage.setFrom(new InternetAddress(username));
      usermessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
      usermessage.setSubject(subject);
      usermessage.setText(message);
      Transport.send(usermessage);
    } catch (Exception x) {
      System.out.println(x);
    }
  }
}
