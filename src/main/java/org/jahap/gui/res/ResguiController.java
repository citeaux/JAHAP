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


package org.jahap.gui.res;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;
import org.jahap.business.acc.accountsbean;
import org.jahap.business.acc.cscbean;
import org.jahap.business.base.Hotelbean;
import org.jahap.business.base.addressbean;
import org.jahap.business.base.ratesbean;
import org.jahap.business.base.roomsbean;
import org.jahap.business.res.occbean;
import org.jahap.business.res.resbean;
import org.jahap.entities.acc.Accounts;
import org.jahap.entities.res.Occ;
import org.jahap.gui.acc.SimpelAccountingController;
import org.jahap.gui.base.AdressGuiFx;
import org.jahap.gui.base.ListDialogAddressController;
import org.jahap.gui.base.RateGuiFx;
import org.jahap.gui.base.RateListController;
import org.jahap.gui.base.RoomListController;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;
import org.jahap.sreport.resreports;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class ResguiController implements Initializable, InterResSearchResultListener {
    //private CalendarView clview;
    
    
    
    static Logger log = Logger.getLogger(ResguiController.class.getName());
    @FXML
    private DatePicker datapickerFrom;
    @FXML
    private DatePicker datapickerTo;
    @FXML
    private Label DASH_ResOrderer_txt;
    @FXML
    private Font x1;
    @FXML
    private Label DASH_ResNo_fxtxt;
    @FXML
    private Label DASH_ResArrival_fxtxt;
    @FXML
    private Font x2;
    @FXML
    private Label DASH_ResState_fxtxt;
    @FXML
    private Label DASH_ResPax_fxtxt;
    @FXML
    private Label DASH_ResDeparture_fxtxt;
    @FXML
    private TextField Orderer_Name_fxtxtfield;
    @FXML
    private TextField Orderer_FirstName_fxtxtfield;
    @FXML
    private TextField Orderer_Street_fxtxtfield;
    @FXML
    private TextField Orderer_ZipCode_fxtxtfield;
    @FXML
    private TextField Orderer_City_fxtxtfield;
    @FXML
    private TextField Orderer_Country_fxtxtfield;
    @FXML
    private Button Orderer_Seach_fxbuton;
    @FXML
    private Button Orderer_Details_fxbutton;
    @FXML
    private TextField Guest_firstName_fxtxtfield;
    @FXML
    private TextField Guest_Country_fxtxtfield;
    @FXML
    private TextField Guest_City_fxtxtfield;
    @FXML
    private TextField Guest_Street_fxtxtfield;
    @FXML
    private TextField Guest_ZipCode_fxtxtfield;
    @FXML
    private TextField Guest_Name_fxtxtfield;
    @FXML
    private Button Guest_Search_fxbutton;
    @FXML
    private Button Guest_Details_fxbutton;
    @FXML
    private TextField Room_Code_fxtxtfield;
    @FXML
    private Button Room_Search_fxbutton;
    @FXML
    private TextField ACC_No_fxtxtfield;
    @FXML
    private TextField ACC_Balance_fxtxtfield;
    @FXML
    private Button ACC_OpenAcc_fxbutton;
    @FXML
    private Button Save_fxbutton;
    @FXML
    private Button Exit_fxbutton;
    @FXML
    private Button Print_fxbutton;
    @FXML
    private Button Search_fxbutton;
    @FXML
    private Button Toolbox_FirstRecord_fxButton;
    @FXML
    private Button Toolbox_BackwardRecord_fxbutton;
    @FXML
    private ChoiceBox<?> Toolbox_Sorting_fxchoicebox;
    @FXML
    private Button Toolbox_ForewardRecord_fxbutton;
    @FXML
    private Button Toolbox_LastRecord_fxbutton;
    
    private InterResSearchResult ressearchresult;
    private resbean res;
    private ratesbean rate;
    private occbean occ;
    private roomsbean room;
    private addressbean address;
    private accountsbean accs;
    private cscbean cscs;
    @FXML
    private Font x3;
    @FXML
    private Button New_fxbutton;
    @FXML
    private TextField RATE_Name_fxtxtfield;
    @FXML
    private Button RATE_Search_fxbutton;
    @FXML
    private Button RATE_Details_fxtxtfield;
    @FXML
    private Label DASH_ResNewCreated_fxtxt;

    /**
     * Initializes the controller class.
     */
     private boolean createNewRecord=false;
     private long roomid=0;
     private long ordererid=0;
     private long guestid=0;
     private long rateid=0;
     private long accountid=0;
     private List<TextField>textfields;
     private  ResourcenManager jk;
 	 private Hotelbean hbean;
	
     private  DecimalFormat DecFormatter;
     private  SimpleDateFormat DFormat;
     
    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	    log.debug("Function entry initialize");
		    
	    hbean= new Hotelbean();
        LocalDate today=LocalDate.now();
        DecFormatter= new DecimalFormat(hbean.getHotelNumberformat());
        
        DFormat= new SimpleDateFormat(hbean.getHoteldateformat());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(hbean.getHoteldateformat());
        textfields=new ArrayList<TextField>();
        DASH_ResArrival_fxtxt.setText(today.format(formatter));
        DASH_ResDeparture_fxtxt.setText(today.plusDays(1).format(formatter));
        datapickerFrom.setValue(today);
        datapickerTo.setValue(today.plusDays(1));
        res = new resbean();
        address=new addressbean();
        occ=new occbean();
        room=new roomsbean();
        rate=new ratesbean();
        accs=new accountsbean();
        cscs=new cscbean();
        ressearchresult=new InterResSearchResult();
        createNewRecord=true;
         ressearchresult.addIDListener(this);
	    log.debug("Function exit initialize");
    }    

    @FXML
    private void Search_Orderer(ActionEvent event) throws IOException {
    	Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
	    log.debug("Function entry Search_Orderer");  
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	       
        Stage stage = new Stage();
        
       
        URL fxmlURL = classLoader.getResource("fxml/base/AddressList.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.AddressList"));   
        
        AnchorPane page = (AnchorPane) loader.load();
	    
        
        Scene scene = new Scene(page);
        stage.setTitle(jk.getFxLangString("window.Title")) ;   

        
      
       

        
        stage.setScene(scene);
        ListDialogAddressController controller= loader.<ListDialogAddressController>getController();
       controller.init(ressearchresult,this,"orderaddress");
       
        
        stage.showAndWait();
        
	    log.debug("Function exit Search_Orderer");
        
    }

    @FXML
    private void Details_Orderer(ActionEvent event) throws IOException {
	    log.debug("Function entry Details_Orderer");
           Stage stage = new Stage();
           Resourcen kk= new Resourcen();
   	    jk=kk.getResourcenManager();
           
   	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
 	   URL fxmlURL = classLoader.getResource("fxml/base/AdressGuiFx.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.AdressGuiFx"));     
        AnchorPane page = (AnchorPane) loader.load();
	    
        
        Scene scene = new Scene(page);
        stage.setTitle(jk.getFxLangString("window.Title")) ;   
        
  
       

        
        stage.setScene(scene);
        AdressGuiFx controller= loader.<AdressGuiFx>getController();
       controller.init(ordererid);
       
        
        stage.showAndWait();
        
        
        log.debug("Function entry Details_Orderer");
        
    }

    @FXML
    private void Search_Guest(ActionEvent event) throws IOException {
	    log.debug("Function entry Search_Guest ");
        Stage stage = new Stage();
        
        Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        
        
        String fxmlFile = "/fxml/base/AddressList.fxml";
       
        
       
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
 	   URL fxmlURL = classLoader.getResource("fxml/base/AddressList.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.AddressList"));   
 	    
 	    
     
        
      
         AnchorPane page = (AnchorPane) loader.load();
 	    
       
             Scene scene = new Scene(page);
             stage.setTitle(jk.getFxLangString("window.Title")) ;   
        
        

        
        stage.setScene(scene);
        ListDialogAddressController controller= loader.<ListDialogAddressController>getController();
       controller.init(ressearchresult,this,"guestaddress");
       
        
        stage.showAndWait();
        log.debug("Function exit Search_Guest");
    }

    @FXML
    private void Details_guest(ActionEvent event) throws IOException {
        
	    log.debug("Function entry Details_guest");
	    Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
         Stage stage = new Stage();
     
       
        

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
 	   URL fxmlURL = classLoader.getResource("fxml/base/AdressGuiFx.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.AdressGuiFx"));   
 	    
 	    
     
        
      
         AnchorPane page = (AnchorPane) loader.load();
 	    
       
             Scene scene = new Scene(page);
             stage.setTitle(jk.getFxLangString("window.Title")) ;   
      
       

        
        stage.setScene(scene);
        AdressGuiFx controller= loader.<AdressGuiFx>getController();
       controller.init(guestid);
       
        
        stage.showAndWait();
       log.debug("Function exit Details_guest");	
    }

    @FXML
    private void Search_room(ActionEvent event) throws IOException {
	    log.debug("Function entry Search_room");
	    
	 Stage stage = new Stage();
       
         Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
	    
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
       
	   URL fxmlURL = classLoader.getResource("fxml/base/RoomList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.RoomList"));   
	    
	    
    
       
     
        AnchorPane page = (AnchorPane) loader.load();
	    
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Title")) ;   
	    
	    
	    
	    
	    
       
       

        
        stage.setScene(scene);
        RoomListController controller= loader.<RoomListController>getController();
       controller.init(ressearchresult,this,"rooms",datapickerFrom.getValue().atStartOfDay().toLocalDate(),datapickerTo.getValue().atStartOfDay().toLocalDate());
       
   
        
        stage.showAndWait();
	    log.debug("Function exit Search_room");
    }

    @FXML
    private void Open_Account(ActionEvent event) throws IOException {
        
	    
	    
	    
        log.debug("Function entry Open_Account");
        
         Stage stage = new Stage();
             ClassLoader classLoader = Thread.currentThread().getContextClassLoader(); 
       URL fxmlURL = classLoader.getResource("fxml/acc/simpelAccounting.fxml");
        Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
	    
	FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.acc.i18n.simpelAccounting"));   

          AnchorPane page = (AnchorPane) loader.load();
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
	 stage.setTitle(jk.getFxLangString("window.Title")) ; 
        SimpelAccountingController controller= loader.<SimpelAccountingController>getController();
       controller.init(accountid);
         
       
        stage.showAndWait();
        
        log.debug("Function exit Open_Account");
    }

    @FXML
    private void Save(ActionEvent event) {
       // only for changing Res
        
        log.debug("Function entry Save, CreateNewRecord=" + createNewRecord);
        
	//*********************** If  res exits ******************
	
        if(createNewRecord=false){
            log.debug("Function Save: oldrecord");
        Date SaveFromDate=occ.getArrivaldate();
        Date SaveToDate=occ.getDeparturedate();
        long SaveRoomId=occ.getRoom().getId();
        long SaveAddressId=occ.getGuest().getId();
        
            
      
        
         res.setAddresses(address.getDataRecord(ordererid));  // Set Addressrecord
                      
   
                     

                      
                      
                    
        
       
        
        if(datapickerFrom.getValue()!=LocalDateTime.ofInstant(occ.getArrivaldate().toInstant(),ZoneId.systemDefault()).toLocalDate()){
            
            occ.setArrivaldate(Date.from(datapickerFrom.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        
        }
        if(datapickerTo.getValue()!=LocalDateTime.ofInstant(occ.getDeparturedate().toInstant(),ZoneId.systemDefault()).toLocalDate()){
          
            occ.setDeparturedate(Date.from(datapickerTo.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        }
        if(roomid!=occ.getRoom().getId()){
            
            occ.setRoom(room.getDataRecord(roomid));
        
        }
        if(guestid!=occ.getGuest().getId()){
            
            occ.setGuest(address.getDataRecord(guestid));
        
        }
        
        if(rateid!=cscs.getRate().getId()){
             cscs.setRate(rate.getDataRecord(rateid));
        }
        
        // checks for overlaps 
	
	
	
         List<String>overlaps=new ArrayList<String>();  
         overlaps=occ.CheckForOverlappingReservations();  
         if(overlaps==null){
             overlaps=occ.saveRecord(true);
             res.saveRecord();
             cscs.saveRecord();
             System.out.println("Ok");
         }
         
          if(overlaps!=null){
               log.debug("Function Save:OldRecord:Overlaps-no");
                        if(overlaps.size()>=1){
                            String Test=overlaps.get(1);
                            int i;
                            for (i=0;i==overlaps.size();i++){
                               Test=Test+ ", " +overlaps.get(i);
                            }
                            
                           int iol=  JOptionPane.showOptionDialog(null, Test, "Rooms is Occupied by",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE,null,new String[]{"create Reservation","drop Reservation","Cancel"},"Cancel");
                            
                               switch(iol ){
                                   case 0:{
                                      
                                        res.setArrivaldate(occ.getArrivaldate());
                                        res.setDeparturedate(occ.getDeparturedate());
                                        res.saveRecord();
                                        cscs.saveRecord();
                                       System.out.println("res adjusted");
                                       
                                   }
                                   case 1:{
                                       System.out.println("res removed");
                                       occ.setArrivaldate(SaveFromDate);
                                       occ.setDeparturedate(SaveToDate);
                                       occ.setRoom(room.getDataRecord(SaveRoomId));
                                       occ.setGuest(address.getDataRecord(SaveAddressId));
                                       res.setArrivaldate(occ.getArrivaldate());
                                        res.setDeparturedate(occ.getDeparturedate());
                                         overlaps=occ.saveRecord(true);
                                         res.saveRecord();
                                         cscs.saveRecord();
                                   }
                                   case 2:{
                                        System.out.println("res");
                                        
                                   }
                               }
                        }
               }
            log.debug("Function Save:OldRecord exit");
        }
        
	// ************************ If Res does not exits already ************************
	
         if(createNewRecord=true){
             log.debug("Function Save: Newrecord");
        Date SaveFromDate=occ.getArrivaldate();
        Date SaveToDate=occ.getDeparturedate();
        long SaveRoomId=occ.getRoom().getId();
        long SaveAddressId=occ.getGuest().getId();
        long ResNo=res.getNewResNumber();
            
      
         res.createNewEmptyRecord();
         res.setAddresses(address.getDataRecord(ordererid));  // Set Addressrecord
                      

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
          // Set dates for  OCC
            occ.createNewEmptyRecord();
            occ.setArrivaldate(Date.from(datapickerFrom.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            occ.setDeparturedate(Date.from(datapickerTo.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            occ.setRoom(room.getDataRecord(roomid));
            occ.setGuest(address.getDataRecord(guestid));
            cscs.setRate(rate.getDataRecord(rateid));
            accs.createNewEmptyRecord();
            accs.setAddress(address.getDataRecord(guestid));
            accs.saveRecord();
            occ.setAccount(accs.getLastRecord());
             
             
         List<String>overlaps=new ArrayList<String>();  
         overlaps=occ.CheckForOverlappingReservations();  
         if(overlaps==null){
            res.setResno(String.valueOf(ResNo));
             
             res.setArrivaldate(occ.getArrivaldate());
             res.setDeparturedate(occ.getDeparturedate());
             
             
             res.saveRecord();
             accs.setReservation(res.GetCurrentRes());
             accs.saveRecord();
             occ.setRes(res.getLastRecord());
             overlaps=occ.saveRecord(true);
             
             
             cscs.saveRecord();
             System.out.println("Ok");
         }
             
                 
          if(overlaps!=null){
              log.debug("Function Save:NewRecord:Overlaps-no");
                        if(overlaps.size()>=1){
                            String Test=overlaps.get(1);
                            int i;
                            for (i=0;i==overlaps.size();i++){
                               Test=Test+ ", " +overlaps.get(i);
                            }
                            
                           int iol=  JOptionPane.showOptionDialog(null, Test, "Rooms is Occupied by",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE,null,new String[]{"create Reservation","drop Reservation","Cancel"},"Cancel");
                            
                               switch(iol ){
                                   case 0:{
                                      
                                        res.setArrivaldate(occ.getArrivaldate());
                                        res.setDeparturedate(occ.getDeparturedate());
                                        res.saveRecord();
                                        cscs.saveRecord();
                                       System.out.println("res adjusted");
                                       
                                   }
                                   case 1:{
                                       System.out.println("res removed");
                                       occ.setArrivaldate(SaveFromDate);
                                       occ.setDeparturedate(SaveToDate);
                                       occ.setRoom(room.getDataRecord(SaveRoomId));
                                       occ.setGuest(address.getDataRecord(SaveAddressId));
                                       res.setArrivaldate(occ.getArrivaldate());
                                        res.setDeparturedate(occ.getDeparturedate());
                                         overlaps=occ.saveRecord(true);
                                         res.saveRecord();
                                         cscs.saveRecord();
                                   }
                                   case 2:{
                                        System.out.println("res");
                                        
                                   }
                               }
                        }
                       log.debug("Function Save:newrecord exit");
                           
               }
        }
        log.debug("Function exit Save");
    }

    @FXML
    private void Exit(ActionEvent event) {
    }

    @FXML
    private void Print(ActionEvent event) throws JRException, SQLException {
	    resreports k=new resreports();
	    k.singleResReport(res.getResno());
	    
    }

    @FXML
    private void Search(ActionEvent event) {
    }

    @FXML
    private void Toolbox_FirstRecord(ActionEvent event) {
    }

    @FXML
    private void Toolbox_BackwardRecord(ActionEvent event) {
        res.nextRecordBackward();
        FillWithSelectedData();
    }

    @FXML
    private void Toolbox_ForewardRecord(ActionEvent event) {
        res.nextRecordForeward();
        FillWithSelectedData();
        
    }

    @FXML
    private void Toolbox_LastRecord(ActionEvent event) {
    }
    
    /**
     *
     * @param id
     */
    public void init(long id){
     log.debug("Function entry init id" + String.valueOf(id));
        textfields=new ArrayList<TextField>();
        
        
        
        
        res = new resbean();
        address=new addressbean();
        occ=new occbean();
        room=new roomsbean();
        rate=new ratesbean();
        accs=new accountsbean();
        cscs=new cscbean();
        ressearchresult=new InterResSearchResult();
        res.setDataRecordId(id);
                      ressearchresult.addIDListener(this);
              FillWithSelectedData();
        
        
        // Add Textfields 
              
          textfields.add(Orderer_Name_fxtxtfield);
          textfields.add(Orderer_FirstName_fxtxtfield);
          textfields.add(Orderer_Street_fxtxtfield);
          textfields.add(Orderer_ZipCode_fxtxtfield);
          textfields.add(Orderer_City_fxtxtfield);
          textfields.add(Guest_firstName_fxtxtfield);
          textfields.add(Guest_City_fxtxtfield);
          textfields.add(Guest_Street_fxtxtfield);
          textfields.add(Guest_ZipCode_fxtxtfield);
          textfields.add(Guest_Name_fxtxtfield);
          textfields.add(Room_Code_fxtxtfield);
          textfields.add(ACC_No_fxtxtfield);
          textfields.add(ACC_Balance_fxtxtfield);
          textfields.add(RATE_Name_fxtxtfield);
          
          
          
          log.debug("Function exit init");
        
    }

    private void FillWithSelectedData() {
        // init DASH Board
        log.debug("Function entry FillWithSelectedData ");
        DASH_ResArrival_fxtxt.setText(DFormat.format(res.getArrivaldate()));
        DASH_ResDeparture_fxtxt.setText(DFormat.format(res.getDeparturedate()));
        DASH_ResNo_fxtxt.setText(res.getResno());
        DASH_ResOrderer_txt.setText(res.getAddresses().getName());
        DASH_ResPax_fxtxt.setText("1"); //DEV : Paximplenetion
        DASH_ResState_fxtxt.setText("Definitv"); //DEV : State implemnetation
        
        // init Order infos
        fillOrderer();
        
        //DEV :Init Guest infos
       
        fillGuest();
        //Res
        List<Occ> gh=new ArrayList<Occ>();
	Occ mb= new Occ();
        gh=occ.SearchForOccforRes(res.GetCurrentRes());
	mb=gh.get(0);
        roomid=occ.getRoom().getId();
        Room_Code_fxtxtfield.setText(mb.getRoom().getCode()+" "+mb.getRoom().getName());
        
        //RATE_Name_fxtxtfield.setText(;
        fillDates();
        
       
        // ACC
        
        
        ACC_Balance_fxtxtfield.setText("33");
        accs.moveToRecordwithRes(res.GetCurrentRes());
        Accounts hhf=new Accounts();
        hhf=accs.getAccount();
        cscs.moveToRecordwithAccount(accs.getAccount());
        RATE_Name_fxtxtfield.setText(cscs.getRate().getCode() + "  " + cscs.getRate().getName());
        ACC_No_fxtxtfield.setText(accs.getId().toString() + " " + accs.getAddress().getName() );
        this.rateid=cscs.getRate().getId();
        this.accountid=accs.getId();
	
	    log.debug("Function exit FillWithSelectedData");
       
    }

    private void fillOrderer(){
	    log.debug("Function entry fillOrderer");   
	    
         Orderer_Name_fxtxtfield.setText(res.getAddresses().getName());
        Orderer_FirstName_fxtxtfield.setText(res.getAddresses().getChristianname());
        Orderer_Street_fxtxtfield.setText(res.getAddresses().getStreet());
        Orderer_ZipCode_fxtxtfield.setText(res.getAddresses().getZipcode());
        Orderer_City_fxtxtfield.setText(res.getAddresses().getCity());
        ordererid=res.getAddresses().getId();
	
	    log.debug("Function exit fillOrderer");
    }
    
    private void fillOrderer(long addressid){
	    
	    log.debug("Function entry fillOrderer" + String.valueOf(addressid));
        
         Orderer_Name_fxtxtfield.setText(address.getDataRecord(addressid).getName());
        Orderer_FirstName_fxtxtfield.setText(address.getDataRecord(addressid).getChristianname());
        Orderer_Street_fxtxtfield.setText(address.getDataRecord(addressid).getStreet());
        Orderer_ZipCode_fxtxtfield.setText(address.getDataRecord(addressid).getZipcode());
        Orderer_City_fxtxtfield.setText(address.getDataRecord(addressid).getCity());
        ordererid=addressid;
	    log.debug("Function exit fillOrderer");
    }
    
    
    private void fillGuest(){
	    log.debug("Function entry fillGuest ");
         Guest_Name_fxtxtfield.setText(occ.getGuest().getName());
        Guest_firstName_fxtxtfield.setText(occ.getGuest().getChristianname());
        Guest_Street_fxtxtfield.setText(occ.getGuest().getStreet());
        Guest_ZipCode_fxtxtfield.setText(occ.getGuest().getZipcode());
        Guest_City_fxtxtfield.setText(occ.getGuest().getCity());
        guestid=occ.getGuest().getId();
              log.debug("Function exit fillGuest");
    }
    
    private void fillGuest(long addressid){
	    log.debug("Function entry fillGuest" + String.valueOf(addressid));
         Guest_Name_fxtxtfield.setText(address.getDataRecord(addressid).getName());
        Guest_firstName_fxtxtfield.setText(address.getDataRecord(addressid).getChristianname());
        Guest_Street_fxtxtfield.setText(address.getDataRecord(addressid).getStreet());
        Guest_ZipCode_fxtxtfield.setText(address.getDataRecord(addressid).getZipcode());
        Guest_City_fxtxtfield.setText(address.getDataRecord(addressid).getCity());
       guestid=addressid;
       log.debug("Function exit fillGuest");
    }
    
    private void fillRoom(long roomid){
        
        Room_Code_fxtxtfield.setText(room.getDataRecord(roomid).getCode()+ " " + room.getDataRecord(roomid).getName());
        this.roomid=roomid;
    }
    
     private void fillRate(long rateid){
        RATE_Name_fxtxtfield.setText(rate.getDataRecord(rateid).getCode()+ " " + rate.getDataRecord(rateid).getName());
        this.rateid=rateid;
     }
     
     
     
     private void fillDates(){
         datapickerFrom.setValue(LocalDateTime.ofInstant(occ.getArrivaldate().toInstant(),ZoneId.systemDefault()).toLocalDate());
         datapickerTo.setValue(LocalDateTime.ofInstant(occ.getDeparturedate().toInstant(),ZoneId.systemDefault()).toLocalDate());
     }
    
    /**
     *
     * @param e
     */
    public void idinfo(InterResSearchResultEvent e) {
     if(e.getTableNameofSource()=="orderaddress"){
         fillOrderer(e.getDbRecordId());
     }   
     
     if(e.getTableNameofSource()=="guestaddress"){
           fillGuest(e.getDbRecordId());
     }
     if(e.getTableNameofSource()=="rooms"){
           fillRoom(e.getDbRecordId());
     }
     
     if(e.getTableNameofSource()=="rate"){
           fillRate(e.getDbRecordId());
     }
     
     
    }

    @FXML
    private void New(ActionEvent event) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        for(TextField kk:textfields){
            kk.setText("");
        }
       
        createNewRecord=true;
        
    }

    @FXML
    private void SearchForRate(ActionEvent event) throws IOException {
	    log.debug("Function entry SearchForRate");
        Stage stage = new Stage();
        Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        
        
        String fxmlFile = "/fxml/base/RatesList.fxml";
        
       
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
 	   URL fxmlURL = classLoader.getResource("fxml/base/RatesList.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.RatesList"));   
 	    
 	    
     
        
      
         AnchorPane page = (AnchorPane) loader.load();
 	    
       
             Scene scene = new Scene(page);
             stage.setTitle(jk.getFxLangString("window.Title")) ;   

       

        
        stage.setScene(scene);
        RateListController controller= loader.<RateListController>getController();
       controller.init(ressearchresult,this,"rate");
       
        
        stage.showAndWait();
	    log.debug("Function exit SearchForRate");
    }

    @FXML
    private void RateDetails(ActionEvent event) throws IOException {
	    log.debug("Function entry RateDetails ");
        Stage stage = new Stage();
        Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        
        
        
        
        String fxmlFile = "/fxml/base/RateGuiFx.fxml";
       
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
 	   URL fxmlURL = classLoader.getResource("fxml/base/RateGuiFx.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.RateGuiFx"));   
 	    
 	    
     
        
      
         AnchorPane page = (AnchorPane) loader.load();
 	    
       
             Scene scene = new Scene(page);
             stage.setTitle(jk.getFxLangString("window.Title")) ;   
       

        
        stage.setScene(scene);
        RateGuiFx controller= loader.<RateGuiFx>getController();
       controller.init(rateid);
       
        
        stage.showAndWait();
	    log.debug("Function exit RateDetails");
        
        
    }

    @FXML
    private void Action_From_Date(ActionEvent event) {
        
       
    }

    @FXML
    private void Action_To_Date(ActionEvent event) {
        
        
    }
    
}
