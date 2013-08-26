/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

import java.util.Date;

/**
 *
 * @author russ
 */
public interface occ_ie {

    Date getArrivaldate();

    Date getArrivaltime();

    Date getDeparturedate();

    Date getDeparturetime();

    Res getRes();

    Rooms getRoom();

    void setArrivaldate(Date arrivaldate);

    void setArrivaltime(Date arrivaltime);

    void setDeparturedate(Date departuredate);

    void setDeparturetime(Date departuretime);

    void setRes(Res res);

    void setRoom(Rooms room);
    
}
