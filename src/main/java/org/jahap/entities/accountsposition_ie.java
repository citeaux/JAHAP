/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author russ
 */
public interface accountsposition_ie {

    Accounts getAccount();

    double getAmount();

    BigInteger getArticle();

    @XmlTransient
    Collection<BillPos> getBillPosCollection();

    boolean getBilled();

    boolean getDebit();

    Long getId();

    String getPositionname();

    Rates getRate();

    Date getRatedate();

    @XmlTransient
    Collection<Revenue> getRevenueCollection();

    void setAccount(Accounts account);

    void setAmount(double amount);

   

    void setBillPosCollection(Collection<BillPos> billPosCollection);

    void setBilled(boolean billed);

    void setDebit(boolean debit);

    void setPositionname(String positionname);

    void setRate(Rates rate);

    void setRatedate(Date ratedate);
    
}
