/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author russ
 */
public interface accounts_ie {

    Collection<AccountPosition> getAccountPositionCollection();

    Integer getBalance();

    String getCheckindate();

    boolean getCheckout();

    String getCheckoutdate();

    Long getId();

    @XmlTransient
    Res getReservation();

    // @todo get it done
    void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection);

    void setBalance(Integer balance);

    void setCheckindate(String checkindate);

    void setCheckout(boolean checkout);

    void setCheckoutdate(String checkoutdate);

    void setReservation(Res reservation);
    
}
