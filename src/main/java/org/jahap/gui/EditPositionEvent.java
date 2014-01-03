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
    private final viewAccountPositions position;
    
    public EditPositionEvent(viewAccountPositions position){
        this.position=position;
    }
    
    public viewAccountPositions getPosition(){
         return position;
    }
}
