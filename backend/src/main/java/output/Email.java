package output;

import database.CustomerTable;
import database.DbCommunicator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;


public class Email {

   private static final Properties props = System.getProperties();

    @SuppressWarnings("Duplicates")
    public static void sendManager(HashMap<Integer, CustomerTable> custWithTX, HashMap<Integer, CustomerTable> custNotTX) throws Exception {

        StringBuilder content = new StringBuilder();
       try {
            props.put("mail.smtp.host", "exchange.heb.com");
            Session session = Session.getInstance(props, null);
            String from = "from@test.com";

            content.append("===Texas Customers===\n\n");

            for( Integer key : custWithTX.keySet()) {
                java.util.Date date = new Date();
                System.out.println("TODAYS DATE:  "+ date.getTime());
                content.append(custWithTX.get(key).toString());
                custWithTX.get(key).setLstEmailSent(new java.sql.Timestamp(new Date().getTime()));
                //System.out.println(custWithTX.get(key).getLstEmailSent());
                DbCommunicator.updateLstEmailSent(custWithTX.get(key));
                content.append("\n");
            }

            content.append("===Out of State Customers===\n\n");

            for( Integer key : custNotTX.keySet()) {
                content.append(custNotTX.get(key).toString());
            }
            content.append("\n");

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO,
                    "rowan.dillon@heb.com");
            msg.setSubject("JavaMail hello world example");
            msg.setSentDate(new Date());
            msg.setText(content.toString());
            //Transport.send(msg);

        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }

    }
}
