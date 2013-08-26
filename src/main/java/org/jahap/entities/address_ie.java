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
public interface address_ie {

    @XmlTransient
    Collection<Bill> getBillCollection();

    String getChristianname();

    String getCity();

    String getEmail();

    Long getId();

    String getName();

    String getPhone();

    /*
    public Bill getBill() {
    return bill;
    }
    public void setBill(Bill bill) {
    this.bill = bill;
    }
     */
    @XmlTransient
    Collection<Res> getResCollection();

    String getStreet();

    String getZipcode();

    void setBillCollection(Collection<Bill> billCollection);

    void setChristianname(String christianname);

    void setCity(String city);

    void setEmail(String email);

    void setName(String name);

    void setPhone(String phone);

    void setResCollection(Collection<Res> resCollection);

    void setStreet(String street);

    void setZipcode(String zipcode);
    
}
