/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javafx.beans.property.SimpleStringProperty;
import org.jahap.business.base.ratesbean;
import org.jahap.entities.AccountPosition;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author russ
 */
public class viewAccountPositionsProperty {

   
    
     private boolean billed=false;
     private boolean debit=false;
     private long id;
     private Date ratedate;
     private SimpleStringProperty rateDateString= new SimpleStringProperty() ;
     private boolean canceled=false;
     private long  canceledposition;
     private double cPrice;
     private double dPrice;
     
     private SimpleStringProperty cPriceString = new SimpleStringProperty();
     private SimpleStringProperty dPriceString = new SimpleStringProperty();
     
     private long cRateid;
     private long dRateid;
     private SimpleStringProperty cPositionname=new SimpleStringProperty();
     private SimpleStringProperty dPositionname= new SimpleStringProperty();
     private SimpleStringProperty cTotal = new SimpleStringProperty();
     private SimpleStringProperty dTotal = new SimpleStringProperty();
   
     private SimpleStringProperty cAmountString = new SimpleStringProperty();
     private SimpleStringProperty dAmountString = new SimpleStringProperty();
     private int cAmount;
     private int dAmount;
    private  DateFormat df ;
     
      public viewAccountPositionsProperty() {
    df = new SimpleDateFormat("MM.dd.yyyy");
    }
    
    public boolean isBilled() {
        return billed;
    }

     public String getRateDateString() {
        return rateDateString.get();
    }

    public void setRateDateString(String rateDateString) {
        this.rateDateString.set(rateDateString);
        
    }
    
    public void setBilled(boolean billed) {
        this.billed = billed;
    }

    public boolean isDebit() {
        return debit;
    }

    public void setDebit(boolean debit) {
        this.debit = debit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getRatedate() {
        return ratedate;
     
    }

    public void setRatedate(Date ratedate) {
        this.ratedate = ratedate;
        if(ratedate!=null){
        this.rateDateString.set(df.format(ratedate));
        }
    }

    public long getcRateid() {
        return cRateid;
    }

    public void setcRateid(long cRateid) {
        this.cRateid = cRateid;
        this.id=cRateid;
        
        
        
        
    }

    public String getcTotal() {
        if(this.cAmount!=0 && this.cPrice!=0){
           this.cTotal.set(String.valueOf(this.cAmount*this.cPrice));
        }          
        return cTotal.get();
    }

   

    public String getdTotal() {
        
        if(this.dAmount!=0 && this.dPrice!=0){
        this.dTotal.set(String.valueOf(this.dAmount*this.dPrice));
        }
        return dTotal.get();
    }

   

    public long getdRateid() {
        return dRateid;
    }

    public void setdRateid(long dRateid) {
        this.dRateid = dRateid;
        this.id=dRateid;
    }

    public String getcPositionname() {
        return cPositionname.get();
    }

    public void setcPositionname(String cPositionname) {
        this.cPositionname.set(cPositionname);
    }

    public String getdPositionname() {
        return dPositionname.get();
    }

    public void setdPositionname(String dPositionname) {
        this.dPositionname.set(dPositionname);
    }

    public int getcAmount() {
        return cAmount;
        
    }

    public void setcAmount(int cAmount) {
        this.cAmount = cAmount;
        if(cAmount!=0){
            this.cAmountString.set(String.valueOf(cAmount));
        }
    }

    public int getdAmount() {
        return dAmount;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public long getCanceledposition() {
        return canceledposition;
    }

    public void setCanceledposition(long canceledposition) {
        this.canceledposition = canceledposition;
    }

    public double getcPrice() {
        return cPrice;
    }

    public void setcPrice(double cPrice) {
        this.cPrice = cPrice;
        if(cPrice!=0){
            this.cPriceString.set(String.valueOf(cPrice));
        }
    }

    public double getdPrice() {
        return dPrice;
    }

    public void setdPrice(double dPrice) {
        this.dPrice = dPrice;
        if(dPrice!=0){
            this.dPriceString.set(String.valueOf(dPrice));
        }
    }

    public String getcPriceString() {
        return cPriceString.get();
    }

    public void setcPriceString(String cPriceString) {
        this.cPriceString.set(cPriceString);
    }

    public String getdPriceString() {
        return dPriceString.get();
    }

    public void setdPriceString(String dPriceString) {
        this.dPriceString.set(dPriceString);
    }
   
    public void setdAmount(int dAmount) {
        this.dAmount = dAmount;
        if(dAmount!=0){
            this.dAmountString.set(String.valueOf(dAmount));
        }
        
    }
    
     public String getcAmountString() {
        return cAmountString.get();
    }

    public void setcAmountString(String cAmountString) {
        this.cAmountString.set(cAmountString);
    }

    public String getdAmountString() {
        return dAmountString.get();
    }

    public void setdAmountString(String dAmountString) {
        this.dAmountString.set(dAmountString);
    }
    
    public AccountPosition getAccountPosition(){
        AccountPosition pos=new AccountPosition();
        pos.setBilled(this.billed);
        pos.setDebit(this.debit);
       ratesbean jj=new ratesbean();
        
        if(this.debit==false){
             pos.setAmount(this.cAmount);
             pos.setPositionname(this.cPositionname.get());
             pos.setPrice(this.cPrice);
             pos.setRate(jj.getDataRecord(this.cRateid));
        }if(this.debit==true){pos.setAmount(this.dAmount);
             pos.setPositionname(this.dPositionname.get());
             pos.setPrice(this.dPrice);
             pos.setRate(jj.getDataRecord(this.dRateid));
        }
        return pos;
    }
    
}
