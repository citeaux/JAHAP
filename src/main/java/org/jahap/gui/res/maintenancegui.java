/*
 * The MIT License
 *
 * Copyright 2014 Open Jahap Community.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jahap.gui.res;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import org.jahap.business.base.roomsbean;
import org.jahap.business.res.MaintenanceBean;
import org.jahap.business.res.occbean;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class maintenancegui implements Initializable {
	
    static Logger log = Logger.getLogger(maintenancegui.class.getName());		
	
    @FXML
    private Button firstRecord_fxbutton;
    @FXML
    private Button oneRecordBackward_fxbutton;
    @FXML
    private Button oneRecordForward_fxbutton;
    @FXML
    private Button lastRecord_fxbutton;
    @FXML
    private TextField room;
    @FXML
    private DatePicker block_from;
    @FXML
    private DatePicker block_to;
    @FXML
    private TextArea blockReason;
    @FXML
    private Button search;
    @FXML
    private Button printBlock;
    @FXML
    private Button newBlock;
    @FXML
    private Button saveBlock;

     private roomsbean rbean;
     private occbean occbean;
    private boolean newblockcreated=false;
    private   Date FromDate;
    private	    Date ToDate;
   private long roomid;
   private MaintenanceBean mbean;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    

     public void filldialog(){
	  room.setText(occbean.getRoom().getCode());
	    block_from.setValue(occbean.getArrivaldate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	    block_to.setValue(occbean.getDeparturedate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());  
	    blockReason.setText(occbean.getHousekeepingblock().getComment());
	    
    }
     
      public void init(long roomid,boolean createNewBlock){
	    LocalDate today=LocalDate.now();
	    mbean = new MaintenanceBean();
	    occbean= new occbean();
	    rbean = new roomsbean();
	    this.roomid=roomid;
	    room.setText(rbean.getDataRecord(roomid).getCode());
	    block_from.setValue(today);
	    block_to.setValue(today.plusDays(1));
	    if(createNewBlock==true){
		    newblockcreated=true;
		    
	    }
	    
    }
     
    
    @FXML
    private void goFirstRecord(ActionEvent event) {
	    mbean.jumpToFirstRecord();
	    filldialog();
    }

    @FXML
    private void goOneRecordBackward(ActionEvent event) {
	   mbean.nextRecordBackward();
	    filldialog(); 
	    
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
	      mbean.nextRecordForeward();
	    filldialog();
    }

    @FXML
    private void goLastRecord(ActionEvent event) {
	     mbean.jumpToLastRecord();
	    filldialog();
    }

    @FXML
    private void searchAdress(ActionEvent event) {
    }

    @FXML
    private void printBlock(ActionEvent event) {
    }

    @FXML
    private void newBlock(ActionEvent event) {
	      LocalDate today=LocalDate.now();
	    block_from.setValue(today);
	    block_to.setValue(today.plusDays(1));
	    room.setText(rbean.getDataRecord(roomid).getCode());	   
	     blockReason.setText("");
	     newblockcreated=true;
	    
    }

    @FXML
    private void saveBlock(ActionEvent event) {
	    String message;
	    log.debug("Function entry saveBlock");
	    if(newblockcreated==false){
		    log.debug("newblockcreated false");
		    mbean.adjustMaintenanceBlock(LocalDate.MIN, LocalDate.MIN, roomid, null, roomid);
	    }else if(newblockcreated==true){
		    log.debug("newblockcreated true");
		    message=mbean.newMaintenanceBlock(this.block_from.getValue(), this.block_to.getValue(), 
			    roomid,this.blockReason.getText());
	    }
	    
	    log.debug("Function exit saveBlock ");
	    
    }
    
}
