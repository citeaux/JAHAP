/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.acc;

import org.jahap.entities.revenue_ie;

/**
 *
 * @author russ
 */
public interface revenue_i extends DatabaseOperations_i, revenue_ie{
    public void createNewEmptyRecord(); 

    public void nextRecordBackward(); 
     

    public void nextRecordForeward() ;


    public void saveRecord() ;
    public void quitDBaccess(); 
}
