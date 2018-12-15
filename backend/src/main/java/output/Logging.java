package output;

import database.CustomerTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class Logging {

    @SuppressWarnings("Duplicates")
    public static void printLog(HashMap<Integer, CustomerTable> custWithTX, HashMap<Integer, CustomerTable> custNotTX) {

        Logger logger = LogManager.getRootLogger();
        logger.info("===Texas Customers===\n\n");

        try {
            for( Integer key : custWithTX.keySet()) {
                logger.info(custWithTX.get(key).toString());
            }
            logger.info("\n");

            logger.info("===Out of State Customers===\n\n");
            for( Integer key : custNotTX.keySet()) {
                logger.info(custNotTX.get(key).toString());
            }
            logger.info("\n");

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
