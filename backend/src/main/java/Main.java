package database;


import output.Email;
import output.Logging;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {


        HashMap<Integer, CustomerTable> custWithTX;
        HashMap<Integer, CustomerTable> custNotTX;

        custWithTX = DbCommunicator.getCustomerTable("SELECT * FROM customers WHERE state = 'TX'");
        custNotTX = DbCommunicator.getCustomerTable("SELECT * FROM customers WHERE state != 'TX'");

        Logging.printLog(custWithTX, custNotTX);

        Email.sendManager(custWithTX, custNotTX); //hashmaps are empty here

    }

}
