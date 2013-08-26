/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

import java.math.BigInteger;
import java.util.Collection;

/**
 *
 * @author russ
 */
public interface revaccounts_ie {

    Long getId();

    String getName();

    BigInteger getRevaccnumber();

    Collection<Revenue> getRevenueCollection();

    void setName(String name);

    void setRevaccnumber(BigInteger revaccnumber);

    void setRevenueCollection(Collection<Revenue> revenueCollection);
    
}
