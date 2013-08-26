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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "PAYMENTTYPES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paymenttypes.findAll", query = "SELECT p FROM Paymenttypes p"),
    @NamedQuery(name = "Paymenttypes.findById", query = "SELECT p FROM Paymenttypes p WHERE p.id = :id"),
    @NamedQuery(name = "Paymenttypes.findByName", query = "SELECT p FROM Paymenttypes p WHERE p.name = :name"),
    @NamedQuery(name = "Paymenttypes.findByReceivable", query = "SELECT p FROM Paymenttypes p WHERE p.receivable = :receivable")})
public class Paymenttypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Column(name = "RECEIVABLE")
    private Serializable receivable;
    @OneToMany(mappedBy = "paymenttype")
    private Collection<Receivables> receivablesCollection;
    @OneToMany(mappedBy = "paymenttype")
    private Collection<Payed> payedCollection;

    public Paymenttypes() {
    }

    public Paymenttypes(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Serializable getReceivable() {
        return receivable;
    }

    public void setReceivable(Serializable receivable) {
        this.receivable = receivable;
    }

    @XmlTransient
    public Collection<Receivables> getReceivablesCollection() {
        return receivablesCollection;
    }

    public void setReceivablesCollection(Collection<Receivables> receivablesCollection) {
        this.receivablesCollection = receivablesCollection;
    }

    @XmlTransient
    public Collection<Payed> getPayedCollection() {
        return payedCollection;
    }

    public void setPayedCollection(Collection<Payed> payedCollection) {
        this.payedCollection = payedCollection;
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
        if (!(object instanceof Paymenttypes)) {
            return false;
        }
        Paymenttypes other = (Paymenttypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Paymenttypes[ id=" + id + " ]";
    }
    
}
