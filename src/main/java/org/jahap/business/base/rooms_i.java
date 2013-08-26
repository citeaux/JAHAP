package org.jahap.business.base;

import org.jahap.entities.rooms_ie;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author russ
 */
public interface rooms_i extends  DatabaseOperations_i, rooms_ie
{
    public void createNewEmptyRecord(); 

    public void nextRecordBackward(); 
     

    public void nextRecordForeward() ;


    public void saveRecord() ;
    public void quitDBaccess();

}
