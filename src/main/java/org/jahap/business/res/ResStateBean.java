/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.res;

import java.util.ArrayList;
import java.util.List;
import org.jahap.business.base.DatabaseOperations;
import org.jahap.entities.resstate;

/**
 *
 * @author russ
 */
public class ResStateBean extends DatabaseOperations  implements resstate_i{

 

    private static List<resstate> ressttatelist;
    
    public ResStateBean() {
        ressttatelist=new ArrayList<resstate>();   
        resstate zw=new resstate();
        zw.setState("option");
        ressttatelist.add(zw);
        
        zw=new resstate();
        zw.setState("confirmed");
        ressttatelist.add(zw);
        
        zw=new resstate();
        zw.setState("cancel");
        ressttatelist.add(zw);
        
        
    }
    
    public List<resstate>SearchForState(String searchstring){
        return this.ressttatelist;
    }
    
    
    public void createNewEmptyRecord() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void nextRecordBackward() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void nextRecordForeward() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void saveRecord() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void quitDBaccess() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getState() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setState(String state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
