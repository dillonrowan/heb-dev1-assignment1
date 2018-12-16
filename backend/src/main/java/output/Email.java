package output;

import database.CustomerTable;
import database.DbCommunicator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;


public class Email {

   private static final Properties PROPERTIES = System.getProperties();
   private static final String SENDER = "rowan.dillon@heb.com";

    @SuppressWarnings("Duplicates")
    public static void sendManager(HashMap<Integer, CustomerTable> custWithTX, HashMap<Integer, CustomerTable> custNotTX) throws Exception {
        StringBuilder content = new StringBuilder();

        try {
            PROPERTIES.put("mail.smtp.host", "exchange.heb.com");
            Session session = Session.getInstance(PROPERTIES, null);

            content.append("===Texas Customers===\n\n");

            for( Integer key : custWithTX.keySet()) {
                content.append(custWithTX.get(key).toString());
                custWithTX.get(key).setLstEmailSent(new java.sql.Timestamp(new Date().getTime()));
                DbCommunicator.updateLstEmailSent(custWithTX.get(key));
                content.append("\n");
            }

            content.append("===Out of State Customers===\n\n");

            for( Integer key : custNotTX.keySet()) {
               content.append(custNotTX.get(key).toString());
               custNotTX.get(key).setLstEmailSent(new java.sql.Timestamp(new Date().getTime()));
               DbCommunicator.updateLstEmailSent(custNotTX.get(key));
           }
            content.append("\n");

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(SENDER));
            msg.setRecipients(Message.RecipientType.TO,
                    "rowan.dillon@heb.com");
            msg.setSubject("Assignment #1 Customer Lists");
            msg.setSentDate(new Date());
            msg.setText(content.toString());
            Transport.send(msg);
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
            Logging.logError(mex.toString());
        }

    }
}
