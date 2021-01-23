/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkingmail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Bright
 */
public class CheckingMail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AddressException, MessagingException, UnsupportedEncodingException {
        try {
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
            Session session = Session.getDefaultInstance(properties);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", "your email here !!", "email account password here!!");
            Folder[] folder = store.getDefaultFolder().list("*");
            for (Folder folder1 : folder) {
                System.out.println(folder1.getFullName());
                if (folder1.getFullName().equals("[Gmail]/Trash")) {
                    folder1.open(Folder.READ_WRITE);
                    Message[] messages = folder1.getMessages();
                    for (Message message : messages) {
                        message.setFlag(Flags.Flag.DELETED, true);
                    }
                }
            }
            store.close();
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(CheckingMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(CheckingMail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
