
package com.example.oopcw;
import com.example.oopcw.core.TicketPool;
import com.example.oopcw.threads.Customer;
import com.example.oopcw.threads.Vendor;
import com.example.oopcw.config.Configuration;
import com.example.oopcw.logging.Logger;
import com.example.oopcw.ui.CommandLineInterface;

public class Main {
    public static void main(String[] args) {

        Configuration config = CommandLineInterface.configureSystem();
        TicketPool ticketPool = new TicketPool();
        Thread vendor = new Thread(new Vendor(ticketPool,
                config.getTicketReleaseRate()));
        Thread customer = new Thread(new Customer(ticketPool));
        vendor.start();
        customer.start();
        try {
            vendor.join();
            customer.join();
        } catch (InterruptedException e) {
            Logger.log("Main thread interrupted.");
        }
        Logger.log("System terminated.");
    }
}