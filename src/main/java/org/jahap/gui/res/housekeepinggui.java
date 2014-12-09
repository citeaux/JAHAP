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
import org.jahap.business.base.roomsbean;
import org.jahap.business.res.HousekeepingBean;
import org.jahap.business.res.occbean;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class housekeepinggui implements Initializable {
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
    private Button newRate;
    @FXML
    private Button saveRate;
    private roomsbean rbean;
    private HousekeepingBean hskbean;
    private occbean occbean;
    private boolean newblockcreated=false;
    private   Date FromDate;
    private	    Date ToDate;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void filldialog(){
	  room.setText(occbean.getRoom().getCode());
	    block_from.setValue(occbean.getArrivaldate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	    block_to.setValue(occbean.getDeparturedate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());  
	    blockReason.setText(occbean.getHousekeepingblock().getComment());
	    
    }
    
    
    public void init(long roomid){
	    LocalDate today=LocalDate.now();
	    hskbean = new HousekeepingBean();
	    occbean= new occbean();
	    rbean = new roomsbean();
	    room.setText(rbean.getDataRecord(roomid).getCode());
	    block_from.setValue(today);
	    block_to.setValue(today.plusDays(1));
	    
    }

    @FXML
    private void goFirstRecord(ActionEvent event) {
	    hskbean.jumpToFirstRecord();
	    filldialog();
    }

    @FXML
    private void goOneRecordBackward(ActionEvent event) {
	    hskbean.nextRecordBackward();
	    filldialog();
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
	    hskbean.nextRecordForeward();
	    filldialog();
    }

    @FXML
    private void goLastRecord(ActionEvent event) {
	    hskbean.jumpToLastRecord();
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
	    hskbean.createNewEmptyRecord();
	    occbean.createNewEmptyRecord();
		   
	    
    }

    @FXML
    private void saveBlock(ActionEvent event) {
    }
    
}
