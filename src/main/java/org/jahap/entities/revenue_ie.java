/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

/**
 *
 * @author russ
 */
public interface revenue_ie {

    AccountPosition getAccountposition();

    boolean getDebit();

    Long getId();

    Revaccounts getRevaccount();
    
    double getAmount();

    void setAccountposition(AccountPosition accountposition);

    void setDebit(boolean debit);

    void setRevaccount(Revaccounts revaccount);
    
    
    
   void setAmount(double amount) ;
    
}
