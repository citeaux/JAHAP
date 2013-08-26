/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.acc;

import org.jahap.entities.revaccounts_ie;

/**
 *
 * @author russ
 */
public interface revaccounts_i extends DatabaseOperations_i, revaccounts_ie{
    public void createNewEmptyRecord(); 

    public void nextRecordBackward(); 
     

    public void nextRecordForeward() ;


    public void saveRecord() ;
}
