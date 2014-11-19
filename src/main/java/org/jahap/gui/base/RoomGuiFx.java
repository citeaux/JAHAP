/*
 * The MIT License
 *
 * Copyright 2014 Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>.
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



package org.jahap.gui.base;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;
import org.jahap.business.base.roomsbean;
import org.jahap.entities.base.Rooms;
import org.jahap.sreport.roomreports;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class RoomGuiFx implements Initializable, RoomSearchResultListener {
    @FXML
    private TextField roomcode_fxtextfield;
    @FXML
    private TextField roomname_fxtextfield;
    @FXML
    private ChoiceBox<?> roomcategory_fxtextfield;
    
    @FXML
    private Button firstRecord_fxbutton;
    @FXML
    private Button oneRecordBackward_fxbutton;
    @FXML
    private Button oneRecordForward_fxbutton;
    @FXML
    private Button lastRecord_fxbutton;
    @FXML
    private Button search;
    @FXML
    private Button printAdress;

    private static roomsbean rooms;
    private List<TextField> textfields;
    private RoomSearchResult searchresults;
    private long ratesid=0;
    @FXML
    private Button newRoom;
    @FXML
    private Button saveRoom;
    static Logger log = Logger.getLogger(RoomGuiFx.class.getName());
    @FXML
    private ToggleButton Housekeeping_dirty;
    @FXML
    private ToggleGroup HouseKeeping;
    @FXML
    private ToggleButton Housekeeping_clean;
    @FXML
    private ToggleButton maintenance_blocked;
    @FXML
    private ToggleGroup maintenance;
    @FXML
    private ToggleButton maintenance_free;
    @FXML
    private ChoiceBox<String> location;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        log.debug("Function entry initialize ");
        textfields=new ArrayList<TextField>();
         
         textfields.add(roomcode_fxtextfield);
         textfields.add(roomname_fxtextfield);
       
         rooms= new roomsbean();
         searchresults = new RoomSearchResult();
         searchresults.addIDListener(this);
         log.debug("Function entry initialize");
    }    

    @FXML
    private void goFirstRecord(ActionEvent event) {
    }

    @FXML
    private void goOneRecordBackward(ActionEvent event) {
        rooms.nextRecordBackward();
        FillWithSelectedData();
        
        
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
        rooms.nextRecordForeward();
        FillWithSelectedData();
    }

    @FXML
    private void goLastRecord(ActionEvent event) {
    }
       @FXML
        private void printRoom(ActionEvent event) throws JRException {
         List<Rooms> adl= new ArrayList<Rooms>();
       adl= rooms.getCurrentRoom();
        
        roomreports ARP = new roomreports();
        ARP.singleRoomReport(adl);
        
    }
    
    
    public void init(long id){
        log.debug("Function exit init  ");
        rooms = new roomsbean();
        rooms.setDataRecordId(id);
                      
              FillWithSelectedData();
        
        log.debug("Function entry init");  
        
    }

    

    private void FillWithSelectedData() {
        log.debug("Function exit FillWithSelectedData");
        roomname_fxtextfield.setText(rooms.getName());
        roomcode_fxtextfield.setText(rooms.getCode());
        
        log.debug("Function entry   FillWithSelectedData");
    }

    public void idinfo(RoomSearchResultEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void searchRoom(ActionEvent event) {
    }

    @FXML
    private void newRoom(ActionEvent event) {
    }

    @FXML
    private void saveRoom(ActionEvent event) {
    }



}
