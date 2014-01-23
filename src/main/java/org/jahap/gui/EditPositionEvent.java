/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

/**
 *
 * @author russ
 */
public class EditPositionEvent {
    private final viewAccountPositionsProperty position;
    
    public EditPositionEvent(viewAccountPositionsProperty position){
        this.position=position;
    }
    
    public viewAccountPositionsProperty getPosition(){
         return position;
    }
}
