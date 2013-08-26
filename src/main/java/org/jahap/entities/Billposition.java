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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "BILLPOSITION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Billposition.findAll", query = "SELECT b FROM Billposition b"),
    @NamedQuery(name = "Billposition.findById", query = "SELECT b FROM Billposition b WHERE b.id = :id"),
    @NamedQuery(name = "Billposition.findByAmount", query = "SELECT b FROM Billposition b WHERE b.amount = :amount"),
    @NamedQuery(name = "Billposition.findByRate", query = "SELECT b FROM Billposition b WHERE b.rate = :rate"),
    @NamedQuery(name = "Billposition.findByBilled", query = "SELECT b FROM Billposition b WHERE b.billed = :billed"),
    @NamedQuery(name = "Billposition.findByAmount1", query = "SELECT b FROM Billposition b WHERE b.amount1 = :amount1"),
    @NamedQuery(name = "Billposition.findByDebit", query = "SELECT b FROM Billposition b WHERE b.debit = :debit"),
    @NamedQuery(name = "Billposition.findByRatedate", query = "SELECT b FROM Billposition b WHERE b.ratedate = :ratedate")})
public class Billposition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "AMOUNT")
    private Integer amount;
    @Column(name = "RATE")
    private BigInteger rate;
    @Column(name = "BILLED")
    private Serializable billed;
    @Column(name = "AMOUNT_1")
    private Integer amount1;
    @Column(name = "DEBIT")
    private Serializable debit;
    @Column(name = "RATEDATE")
    @Temporal(TemporalType.DATE)
    private Date ratedate;
    @JoinColumn(name = "RECEIVABLE", referencedColumnName = "ID")
    @ManyToOne
    private Receivables receivable;
    @JoinColumn(name = "PAYED", referencedColumnName = "ID")
    @ManyToOne
    private Payed payed;
    @JoinColumn(name = "BILL", referencedColumnName = "ID")
    @ManyToOne
    private Bill bill;

    public Billposition() {
    }

    public Billposition(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigInteger getRate() {
        return rate;
    }

    public void setRate(BigInteger rate) {
        this.rate = rate;
    }

    public Serializable getBilled() {
        return billed;
    }

    public void setBilled(Serializable billed) {
        this.billed = billed;
    }

    public Integer getAmount1() {
        return amount1;
    }

    public void setAmount1(Integer amount1) {
        this.amount1 = amount1;
    }

    public Serializable getDebit() {
        return debit;
    }

    public void setDebit(Serializable debit) {
        this.debit = debit;
    }

    public Date getRatedate() {
        return ratedate;
    }

    public void setRatedate(Date ratedate) {
        this.ratedate = ratedate;
    }

    public Receivables getReceivable() {
        return receivable;
    }

    public void setReceivable(Receivables receivable) {
        this.receivable = receivable;
    }

    public Payed getPayed() {
        return payed;
    }

    public void setPayed(Payed payed) {
        this.payed = payed;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
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
        if (!(object instanceof Billposition)) {
            return false;
        }
        Billposition other = (Billposition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Billposition[ id=" + id + " ]";
    }
    
}
