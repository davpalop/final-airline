package org.iesfm.airline.email;


import org.iesfm.airline.Email;
import org.iesfm.airline.Flight;
import org.iesfm.airline.Passenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

public class EmailListener {
    private final static Logger log = LoggerFactory.getLogger(EmailListener.class);
    private Flight flight;
    private Passenger passenger;

    @JmsListener(destination = "airline_email")
    public void onMessage(Email email) {
                email.setTitle("Bienvenido a bordo del vuelo " + flight.getId());
                email.setBody("Bienvenido " +passenger.getName()+ " " +passenger.getSurname()+ ", \n" +
                        "tu vuelo saldrá el día " +flight.getDate()+ " desde " +flight.getOrigin()+
                        " a " +flight.getDestiny()+ ". Tu asiento es " +passenger.getSeat()+ "\n" +
                        "No lo pierdas! \n" + "Hasta pronto.");
    }

}
