/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import java.util.Date;

/**
 *
 * @author russ
 */
public class viewAccountPositons {
    
     private boolean billed=false;
     private boolean debit=false;
     private long id;
     private Date ratedate;
     private long cRateid;
     private long dRateid;
     private String cPositionname;
     private String dPositionname;
     private double cAmount;
     private double dAmount;
    
    
    public boolean isBilled() {
        return billed;
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
    }

    public long getcRateid() {
        return cRateid;
    }

    public void setcRateid(long cRateid) {
        this.cRateid = cRateid;
    }

    public long getdRateid() {
        return dRateid;
    }

    public void setdRateid(long dRateid) {
        this.dRateid = dRateid;
    }

    public String getcPositionname() {
        return cPositionname;
    }

    public void setcPositionname(String cPositionname) {
        this.cPositionname = cPositionname;
    }

    public String getdPositionname() {
        return dPositionname;
    }

    public void setdPositionname(String dPositionname) {
        this.dPositionname = dPositionname;
    }

    public double getcAmount() {
        return cAmount;
    }

    public void setcAmount(double cAmount) {
        this.cAmount = cAmount;
    }

    public double getdAmount() {
        return dAmount;
    }

    public void setdAmount(double dAmount) {
        this.dAmount = dAmount;
    }
    
    
}
