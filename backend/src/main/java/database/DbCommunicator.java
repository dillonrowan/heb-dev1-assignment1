package database;


import output.Logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;


/**
 * Class that contains all methods used to pull and push data from database.
 */
public class DbCommunicator {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/customer_db?zeroDateTimeBehavior=convertToNull&verifyServerCertificate=false&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "willow123";

    private static Connection connection = null;

    /**
     * This method connects to the database using the DATABASE_URL defined above.
     * @return connection to database if connected successfully, otherwise returns null
     * @throws Exception If database it is connecting to is not jdbc:mysql://localhost:3306/customer_db with
     * username = "root" and password = "willow123"
     */
    private static Connection getConnection() throws Exception {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            return connection;
        } catch (Exception e) {
            System.out.println(e);
            Logging.logError(e.toString());
        }
        return null;
    }

    /**
     * This method retrieves all customer data from the customers table. It will query Texas or Non-Texas customers
     * depending on query.
     * @param MySQL string to query customers from the customers table.
     * @return HashMap of returned customers.
     * @throws Exception when querying less than or more than 9 columns from the table.
     */
    public static HashMap<Integer, CustomerTable> getCustomerTable(String sql) throws Exception {
        HashMap<Integer, CustomerTable> customers = new HashMap();
        PreparedStatement query = null;
        ResultSet result = null;

        try {
            query = getConnection().prepareStatement(sql);
            result = query.executeQuery();
            while (result.next()) {
                CustomerTable customer = new CustomerTable();
                customer.setLastName(result.getString(1));
                customer.setFirstName(result.getString(2));
                customer.setEmail(result.getString(3));
                customer.setAddress(result.getString(4));
                customer.setCity(result.getString(5));
                customer.setState(result.getString(6));
                customer.setZip(result.getString(7));
                if(result.getTimestamp(8) == null) {
                    Calendar calendar = Calendar.getInstance();
                    Timestamp defaultTimestamp = new Timestamp(calendar.getTime().getTime());
                    customer.setLstEmailSent(defaultTimestamp);
                } else {
                    customer.setLstEmailSent(result.getTimestamp(8));
                }
                customer.setCustID(result.getInt(9));
                customers.put(customer.getCustID(), customer);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Logging.logError(e.toString());
        } finally {
            if (result != null) {result.close();}
            if (connection != null) {connection.close();}
        }
        return customers;
    }

    /**
     * This method updates the database with the new last_email_sent value when the email to the manager is sent.
     * @param custData Object containing updated last_email_sent value.
     * @throws Exception When table does not have the customer's record to update or customer table is invalid.
     */
    public static void updateLstEmailSent(CustomerTable custData) throws Exception {
        try {
            int custID = custData.getCustID();
            String sqlUpdate = "UPDATE customers SET last_email_sent = " + "\'" + custData.getLstEmailSent() + "\'" + " where customer_id = " + custID;
            PreparedStatement pstmt = getConnection().prepareStatement(sqlUpdate);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Logging.logError(e.toString());
        } finally {
            if (connection != null) {
                connection.close();
            }

        }


    }
}
