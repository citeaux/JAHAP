/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

/**
 *
 * @author russ
 */
public interface res_ie {

    Address getAddresses();

    String getArrivaldate();

    String getArrivaltime();

    String getDeparturedate();

    String getDeparturetime();

    String getResno();

    void setAddresses(Address addresses);

    void setArrivaldate(String arrivaldate);

    void setArrivaltime(String arrivaltime);

    void setDeparturedate(String departuredate);

    void setDeparturetime(String departuretime);

    void setResno();
    
}
