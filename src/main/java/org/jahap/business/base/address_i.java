/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.base;

import javax.ejb.Remote;
import org.jahap.entities.address_ie;
/**
 *
 * @author russ
 */
@Remote
public interface address_i extends DatabaseOperations_i, address_ie{
    String getName();
    String getChristianname();
    String getStreet();
    String getZipcode();
    String getCity();
    String getPhone();
    String getEmail();
    void setName(String name);
    void setChristianname(String christianname);
    void setStreet(String street);
    void setZipcode(String zipcode);
    void setCity(String city);
    void setPhone(String phone);
    void setEmail(String email);
    void quitDBaccess();
}
