package com.example.oopcw.threads;
import com.example.oopcw.core.AbstractTicketHandler;
import com.example.oopcw.core.TicketPool;
import com.example.oopcw.logging.Logger;
public class Customer extends AbstractTicketHandler implements Runnable {
    public Customer(TicketPool ticketPool) {
        super(ticketPool);
    }
    @Override
    public void run() {
        while (true) {
            String ticket = ticketPool.removeTicket();
            if (ticket != null) {
                Logger.log("Customer retrieved: " + ticket);
            } else {
                Logger.log("Customer found no tickets available.");
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Logger.log("Customer interrupted.");
            }
        }
    }
    @Override
    public void handleTickets() {
        run();
    }
}