/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "ACCOUNT_POSITION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountPosition.findAll", query = "SELECT a FROM AccountPosition a"),
    @NamedQuery(name = "AccountPosition.findById", query = "SELECT a FROM AccountPosition a WHERE a.id = :id"),
    @NamedQuery(name = "AccountPosition.findByRate", query = "SELECT a FROM AccountPosition a WHERE a.rate = :rate"),
    @NamedQuery(name = "AccountPosition.findByPositionname", query = "SELECT a FROM AccountPosition a WHERE a.positionname = :positionname"),
    @NamedQuery(name = "AccountPosition.findByAmount", query = "SELECT a FROM AccountPosition a WHERE a.amount = :amount")})
public class AccountPosition implements Serializable, accountsposition_ie {
    @Column(name = "BILLED")
    private boolean billed;
    @Column(name = "DEBIT")
    private boolean debit;
    @Column(name = "RATEDATE")
    @Temporal(TemporalType.DATE)
    private Date ratedate;
    @OneToMany(mappedBy = "accountposition")
    private Collection<Revenue> revenueCollection;
    @JoinColumn(name = "RATE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Rates rate;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "POSITIONNAME")
    private String positionname;
    @Column(name = "AMOUNT")
    private double amount;
    @OneToMany(mappedBy = "accountPosition")
    private Collection<BillPos> billPosCollection;
    @ManyToOne(optional=false)
    @JoinColumn(name = "ACCOUNT", referencedColumnName = "ID")
    private Accounts account;

    public AccountPosition() {
    }

    public AccountPosition(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public BigInteger getArticle() {
        return null;
    }



    @Override
    public String getPositionname() {
        return positionname;
    }

    @Override
    public void setPositionname(String positionname) {
        this.positionname = positionname;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @XmlTransient
    @Override
    public Collection<BillPos> getBillPosCollection() {
        return billPosCollection;
    }

    @Override
    public void setBillPosCollection(Collection<BillPos> billPosCollection) {
        this.billPosCollection = billPosCollection;
    }

   

    @Override
    public Accounts getAccount() {
        return account;
    }

    @Override
    public void setAccount(Accounts account) {
        this.account = account;
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
        if (!(object instanceof AccountPosition)) {
            return false;
        }
        AccountPosition other = (AccountPosition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.AccountPosition[ id=" + id + " ]";
    }

    @Override
    public Rates getRate() {
        return rate;
    }

    @Override
    public void setRate(Rates rate) {
        this.rate = rate;
    }

    @Override
    public boolean getBilled() {
        return billed;
    }

    @Override
    public void setBilled(boolean billed) {
        this.billed = billed;
    }

    @Override
    public boolean getDebit() {
        return debit;
    }

    @Override
    public void setDebit(boolean debit) {
        this.debit = debit;
    }

    @Override
    public Date getRatedate() {
        return ratedate;
    }

    @Override
    public void setRatedate(Date ratedate) {
        this.ratedate = ratedate;
    }

    @XmlTransient
    @Override
    public Collection<Revenue> getRevenueCollection() {
        return revenueCollection;
    }

    public void setRevenueCollection(Collection<Revenue> revenueCollection) {
        this.revenueCollection = revenueCollection;
    }
    
}
