/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "BILL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b"),
    @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id"),
    @NamedQuery(name = "Bill.findByBalance", query = "SELECT b FROM Bill b WHERE b.balance = :balance"),
    @NamedQuery(name = "Bill.findByBillno", query = "SELECT b FROM Bill b WHERE b.billno = :billno"),
    @NamedQuery(name = "Bill.findByCheckout", query = "SELECT b FROM Bill b WHERE b.checkout = :checkout"),
    @NamedQuery(name = "Bill.findByCheckindate", query = "SELECT b FROM Bill b WHERE b.checkindate = :checkindate"),
    @NamedQuery(name = "Bill.findByCheckoutdate", query = "SELECT b FROM Bill b WHERE b.checkoutdate = :checkoutdate"),
    @NamedQuery(name = "Bill.findByResno", query = "SELECT b FROM Bill b WHERE b.resno = :resno"),
    @NamedQuery(name = "Bill.findByClosed", query = "SELECT b FROM Bill b WHERE b.closed = :closed"),
    @NamedQuery(name = "Bill.findByBilldate", query = "SELECT b FROM Bill b WHERE b.billdate = :billdate")})
public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "BALANCE")
    private Integer balance;
    @Column(name = "BILLNO")
    private BigInteger billno;
    @Column(name = "CHECKOUT")
    private Serializable checkout;
    @Size(max = 50)
    @Column(name = "CHECKINDATE")
    private String checkindate;
    @Size(max = 50)
    @Column(name = "CHECKOUTDATE")
    private String checkoutdate;
    @Column(name = "RESNO")
    private BigInteger resno;
    @Column(name = "CLOSED")
    private Serializable closed;
    @Column(name = "BILLDATE")
    @Temporal(TemporalType.DATE)
    private Date billdate;
    @JoinColumn(name = "ADDRESS", referencedColumnName = "ID")
    @ManyToOne
    private Address address;

    public Bill() {
    }

    public Bill(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public BigInteger getBillno() {
        return billno;
    }

    public void setBillno(BigInteger billno) {
        this.billno = billno;
    }

    public Serializable getCheckout() {
        return checkout;
    }

    public void setCheckout(Serializable checkout) {
        this.checkout = checkout;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public String getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    public BigInteger getResno() {
        return resno;
    }

    public void setResno(BigInteger resno) {
        this.resno = resno;
    }

    public Serializable getClosed() {
        return closed;
    }

    public void setClosed(Serializable closed) {
        this.closed = closed;
    }

    public Date getBilldate() {
        return billdate;
    }

    public void setBilldate(Date billdate) {
        this.billdate = billdate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Bill[ id=" + id + " ]";
    }
    
}
