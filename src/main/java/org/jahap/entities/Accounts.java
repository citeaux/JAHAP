/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "ACCOUNTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a"),
    @NamedQuery(name = "Accounts.findById", query = "SELECT a FROM Accounts a WHERE a.id = :id"),
    @NamedQuery(name = "Accounts.findByBalance", query = "SELECT a FROM Accounts a WHERE a.balance = :balance"),
    @NamedQuery(name = "Accounts.findByCheckout", query = "SELECT a FROM Accounts a WHERE a.checkout = :checkout"),
    @NamedQuery(name = "Accounts.findByCheckindate", query = "SELECT a FROM Accounts a WHERE a.checkindate = :checkindate"),
    @NamedQuery(name = "Accounts.findByCheckoutdate", query = "SELECT a FROM Accounts a WHERE a.checkoutdate = :checkoutdate")})
public class Accounts implements Serializable, accounts_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "BALANCE")
    private Integer balance;
    @Column(name = "CHECKOUT")
    private boolean checkout;
    @Size(max = 50)
    @Column(name = "CHECKINDATE")
    private String checkindate;
    @Size(max = 50)
    @Column(name = "CHECKOUTDATE")
    private String checkoutdate;
    @OneToOne(mappedBy = "resaccount",targetEntity=Res.class)
    private Res reservation;
    @OneToMany(mappedBy = "account", targetEntity=AccountPosition.class)
    private Collection<AccountPosition> accountPositionCollection;

    @Override
    public Collection<AccountPosition> getAccountPositionCollection() {
        return accountPositionCollection;
    }
    // @todo get it done
    @Override
    public void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection) {
        this.accountPositionCollection = accountPositionCollection;
    }

    public Accounts() {
    }

    public Accounts(Long id) {
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
    public Integer getBalance() {
        return balance;
    }

    @Override
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public boolean getCheckout() {
        return checkout;
    }

    @Override
    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }

    @Override
    public String getCheckindate() {
        return checkindate;
    }

    @Override
    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    @Override
    public String getCheckoutdate() {
        return checkoutdate;
    }

    @Override
    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    @XmlTransient
    @Override
    public Res getReservation() {
        return reservation;
    }

    @Override
    public void setReservation(Res reservation) {
        this.reservation = reservation;
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
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Accounts[ id=" + id + " ]";
    }
    
}
