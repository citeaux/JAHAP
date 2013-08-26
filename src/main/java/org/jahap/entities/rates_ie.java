/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author russ
 */
public interface rates_ie {

    @XmlTransient
    Collection<AccountPosition> getAccountPositionCollection();

    String getCode();

    Long getId();

    String getName();
    
    long getRevaccount();

    double getPrice();

    void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection);

    void setCode(String code);

    void setRevaccount(Long revaccount);
       
    void setId(Long id);

    void setName(String name);

    void setPrice(double price);
    
}
