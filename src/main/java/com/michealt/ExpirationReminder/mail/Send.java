package com.michealt.ExpirationReminder.mail;


import com.michealt.ExpirationReminder.ExpirationReminderApplication;
import com.michealt.ExpirationReminder.model.License;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

public class Send {

    public static void SendMail(License contact, int days) throws Exception {
        /*final String username = "micheal.thompson@claratti.com";
        final String password = "Ak4K3OL1";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        //message instance
        Message message = new MimeMessage(session);
        //set who the message is coming from
        message.setFrom(new InternetAddress("micheal.thompson@claratti.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(contact.getContactEmail()));

        Date date = new Date();

        message.setSubject("License Due to Expire in " + days + " days from " + date.toString().substring(0, 10));
        message.setDataHandler(new DataHandler(new HTMLDataSource(TestApplication.htmlMessage.replace("null", "").
                                                                    replace("_CONTACT_", contact.getLicenseContact()).
                                                                    replace("_SERIAL_", contact.getLicenseSerial()).
                                                                    replace("_LICENSE_", contact.getLicenseName()).
                                                                    replace("_CLIENT_", contact.getClientName()).
                                                                    replace("_DATE_", contact.getDisplayedDate()).replace("_DAYS_", "" + days))));

        Transport.send(message);*/

        final String username = "micheal.thompson@claratti.com";
        final String password = "Cl0uddcc!";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        //message instance
        Message message = new MimeMessage(session);
        //set who the message is coming from
        message.setFrom(new InternetAddress(username));

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(contact.getContactEmail()));


        /**
         * Create a new entry in db for cc contacts for every contact after contacts[0] add to the cc database and then refer to them
         * in the RecipientType.CC to send to them also ....
         */

        Date date = new Date();

        message.setSubject(contact.getLicenseName() + " is Due to Expire in " + days + " days from " + date.toString().substring(0, 10));
        message.setDataHandler(new DataHandler(new HTMLDataSource(ExpirationReminderApplication.htmlMessage.replace("null", "").
                replace("_CONTACT_", contact.getLicenseContact()).
                replace("_SERIAL_", contact.getLicenseSerial()).
                replace("_LICENSE_", contact.getLicenseName()).
                replace("_VENDOR_", contact.getVendorName()).
                replace("_CLIENT_", contact.getClientName()).
                replace("_DATE_", contact.getDisplayedDate()).replace("_DAYS_", "" + days))));

        Transport.send(message);

        System.out.println("Email sent");
    }

    static class HTMLDataSource implements DataSource {

        private String html;

        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            if (html == null) throw new IOException("html message is null");
            return new ByteArrayInputStream(html.getBytes());
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }

        @Override
        public String getContentType() {
            return "text/html";
        }

        @Override
        public String getName() {
            return "HTMLDataSource";
        }
    }
}
