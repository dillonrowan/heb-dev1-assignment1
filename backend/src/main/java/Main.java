import database.*;
import output.*;

import java.util.HashMap;

/**
 * Queries are sent to database to retrieve lists of all Texas and non-Texas customers.
 * These lists are then placed into Hashmaps where the key is the customer_id and value is the customer data.
 * These HashMaps are then used to print to \custDB\logs\output.log
 * Thrown exceptions are printed to \custDB\logs\errors.log
 * The customer HashMaps are then printed to an email in the same format as ouput.log
 * The email is then sent to manager.
 *
 * @author Dillon Rowan
 * @version 1.0
 * @since 2018-12-17
 */
public class Main {

    private static final String ALL_TEXAS_CUSTOMERS = "SELECT * FROM customers WHERE state = 'TX'";
    private static final String ALL_NON_TEXAS_CUSTOMERS = "SELECT * FROM customers WHERE state != 'TX'";


    /**
     * This is the main method which utilizes other methods located in the database and output packages.
     * @param args unused.
     * @return Nothing.
     * @throws error On connection or invalid data from database.
     */
    public static void main(String[] args) throws Exception {


        HashMap<Integer, CustomerTable> custWithTX;
        HashMap<Integer, CustomerTable> custNotTX;

        custWithTX = DbCommunicator.getCustomerTable(ALL_TEXAS_CUSTOMERS);
        custNotTX = DbCommunicator.getCustomerTable(ALL_NON_TEXAS_CUSTOMERS);

        Logging.logCustomers(custWithTX, custNotTX);

        Email.sendManager(custWithTX, custNotTX);

    }

}
