package output;

import database.CustomerTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;

/**
 * Class that contains methods used to write to various files.
 */
public class Logging {

    private final static Logger LOGGER = LogManager.getRootLogger();

    /**
     * @param errMessage This is the exception message when an exception is thrown
     * @return Nothing.
     */
    public static void logError(String errMessage) {
        LOGGER.error(errMessage);
    }

    /**
     * This method prints the HashMaps to \custDB\logs\output.log
     * @param custWithTX HashMap of all customers outside of Texas. The key for each entry is their customer_id value.
     * @param custNotTX HashMap of all customers from Texas. The key for each entry is their customer_id value.
     */
    @SuppressWarnings("Duplicates")
    public static void logCustomers(HashMap<Integer, CustomerTable> custWithTX, HashMap<Integer, CustomerTable> custNotTX) {

        LOGGER.info("===Texas Customers===\n\n");

        try {
            for( Integer key : custWithTX.keySet()) {
                LOGGER.info(custWithTX.get(key).toString());
            }
            LOGGER.info("\n");

            LOGGER.info("===Out of State Customers===\n\n");
            for( Integer key : custNotTX.keySet()) {
                LOGGER.info(custNotTX.get(key).toString());
            }
            LOGGER.info("\n");

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            logError(e.toString());
        }
    }
}
