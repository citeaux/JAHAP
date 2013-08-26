/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.res;

import org.jahap.business.base.*;
import org.jahap.entities.resstate_ie;

/**
 *
 * @author russ
 */
public interface resstate_i extends DatabaseOperations_i, resstate_ie 
{
    public void createNewEmptyRecord(); 

    public void nextRecordBackward(); 
     

    public void nextRecordForeward() ;


    public void saveRecord() ;
    public void quitDBaccess();
}
