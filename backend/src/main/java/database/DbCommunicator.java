package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class DbCommunicator {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/customer_db?zeroDateTimeBehavior=convertToNull&verifyServerCertificate=false&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "willow123";

    private static Connection connection = null;

    //Return connection to db, if failure return null
    private static Connection getConnection() throws Exception {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println("Connected to database " + DATABASE_URL);
            return connection;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static HashMap<Integer, CustomerTable> getCustomerTable(String sql) throws Exception {
        HashMap<Integer, CustomerTable> customers = new HashMap();
        PreparedStatement query = getConnection().prepareStatement(sql);
        ResultSet result = query.executeQuery();
        try {
            while(result.next()) {
                CustomerTable customer = new CustomerTable();
                customer.setLastName(result.getString(1));
                customer.setFirstName(result.getString(2));
                customer.setEmail(result.getString(3));
                customer.setAddress(result.getString(4));
                customer.setCity(result.getString(5));
                customer.setState(result.getString(6));
                customer.setZip(result.getString(7));
                customer.setLstEmailSent(result.getTimestamp(8)); //might be issue with updating this value
                customer.setCustID(result.getInt(9));
                customers.put(customer.getCustID(),customer);
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if(result != null) {result.close();}
            if(result != null) {query.close();}
            if(result != null) {connection.close();}
        }
        return customers;
    }


}
