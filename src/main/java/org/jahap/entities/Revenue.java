/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "REVENUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Revenue.findAll", query = "SELECT r FROM Revenue r"),
    @NamedQuery(name = "Revenue.findById", query = "SELECT r FROM Revenue r WHERE r.id = :id"),
    @NamedQuery(name = "Revenue.findByDebit", query = "SELECT r FROM Revenue r WHERE r.debit = :debit")})
public class Revenue implements   revenue_ie{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "DEBIT")
    private boolean debit;
    @JoinColumn(name = "ACCOUNTPOSITION", referencedColumnName = "ID")
    @ManyToOne
    private AccountPosition accountposition;
    @JoinColumn(name = "REVACCOUNT", referencedColumnName = "ID")
    @ManyToOne
    private Revaccounts revaccount;

    
    @Column(name="AMOUNT")
    private double amount;
    
    @Override
    public Revaccounts getRevaccount() {
        return revaccount;
    }

    @Override
    public void setRevaccount(Revaccounts revaccount) {
        this.revaccount = revaccount;
    }
    
    public Revenue() {
    }

    public Revenue(Long id) {
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
    public boolean getDebit() {
        return debit;
    }
     
    @Override
    public double getAmount() {
        return amount;
    }
    
    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    @Override
    public void setDebit(boolean debit) {
        this.debit = debit;
    }

    @Override
    public AccountPosition getAccountposition() {
        return accountposition;
    }

    @Override
    public void setAccountposition(AccountPosition accountposition) {
        this.accountposition = accountposition;
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
        if (!(object instanceof Revenue)) {
            return false;
        }
        Revenue other = (Revenue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Revenue[ id=" + id + " ]";
    }
    
}
