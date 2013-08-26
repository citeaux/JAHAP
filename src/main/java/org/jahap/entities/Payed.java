/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "PAYED")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payed.findAll", query = "SELECT p FROM Payed p"),
    @NamedQuery(name = "Payed.findById", query = "SELECT p FROM Payed p WHERE p.id = :id"),
    @NamedQuery(name = "Payed.findByDebit", query = "SELECT p FROM Payed p WHERE p.debit = :debit")})
public class Payed implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "DEBIT")
    private Serializable debit;
    @OneToMany(mappedBy = "payed")
    private Collection<Billposition> billpositionCollection;
    @JoinColumn(name = "PAYMENTTYPE", referencedColumnName = "ID")
    @ManyToOne
    private Paymenttypes paymenttype;

    public Payed() {
    }

    public Payed(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Serializable getDebit() {
        return debit;
    }

    public void setDebit(Serializable debit) {
        this.debit = debit;
    }

    @XmlTransient
    public Collection<Billposition> getBillpositionCollection() {
        return billpositionCollection;
    }

    public void setBillpositionCollection(Collection<Billposition> billpositionCollection) {
        this.billpositionCollection = billpositionCollection;
    }

    public Paymenttypes getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(Paymenttypes paymenttype) {
        this.paymenttype = paymenttype;
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
        if (!(object instanceof Payed)) {
            return false;
        }
        Payed other = (Payed) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Payed[ id=" + id + " ]";
    }
    
}
