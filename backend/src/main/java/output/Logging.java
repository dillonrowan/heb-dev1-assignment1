package output;

import database.CustomerTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Logging {

    @SuppressWarnings("Duplicates")
    public static void printLog(HashMap<Integer, CustomerTable> custWithTX, HashMap<Integer, CustomerTable> custNotTX) {

        Logger logger = LogManager.getRootLogger();

        try {

            logger.info("===Texas Customers===\n\n");
            Iterator itTX = custWithTX.entrySet().iterator();
            while (itTX.hasNext()) {
                Map.Entry<Integer, CustomerTable> pair = (Map.Entry<Integer, CustomerTable>)itTX.next();
                logger.info("Customer #" + pair.getKey());
                logger.info(pair.getValue().getLastName() + " " + pair.getValue().getFirstName());
                logger.info(pair.getValue().getEmail());
                logger.info(pair.getValue().getAddress());
                logger.info(pair.getValue().getCity() + ", " + pair.getValue().getState() + " " + pair.getValue().getZip());
                logger.info('\n');
                itTX.remove();
            }

            logger.info("===Out of State Customers===\n\n");
            Iterator itNotTX = custNotTX.entrySet().iterator();
            while(itNotTX.hasNext()) {
                Map.Entry<Integer, CustomerTable> pair = (Map.Entry<Integer, CustomerTable>)itNotTX.next();
                logger.info("Customer #" + pair.getKey());
                logger.info(pair.getValue().getLastName() + " " + pair.getValue().getFirstName());
                logger.info(pair.getValue().getEmail());
                logger.info(pair.getValue().getAddress());
                logger.info(pair.getValue().getCity() + ", " + pair.getValue().getState() + " " + pair.getValue().getZip());
                logger.info('\n');
                itNotTX.remove();
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
