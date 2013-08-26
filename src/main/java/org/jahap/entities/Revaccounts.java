/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "REVACCOUNTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Revaccounts.findAll", query = "SELECT r FROM Revaccounts r"),
    @NamedQuery(name = "Revaccounts.findById", query = "SELECT r FROM Revaccounts r WHERE r.id = :id"),
    @NamedQuery(name = "Revaccounts.findByRevaccnumber", query = "SELECT r FROM Revaccounts r WHERE r.revaccnumber = :revaccnumber"),
    @NamedQuery(name = "Revaccounts.findByName", query = "SELECT r FROM Revaccounts r WHERE r.name = :name")})
public class Revaccounts implements Serializable, revaccounts_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "REVACCNUMBER")
    private BigInteger revaccnumber;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "revaccount")
    private Collection<Revenue> revenueCollection;

    @Override
    public Collection<Revenue> getRevenueCollection() {
        return revenueCollection;
    }

    @Override
    public void setRevenueCollection(Collection<Revenue> revenueCollection) {
        this.revenueCollection = revenueCollection;
    }
    
    
    public Revaccounts() {
    }

    public Revaccounts(Long id) {
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
    public BigInteger getRevaccnumber() {
        return revaccnumber;
    }

    @Override
    public void setRevaccnumber(BigInteger revaccnumber) {
        this.revaccnumber = revaccnumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Revaccounts)) {
            return false;
        }
        Revaccounts other = (Revaccounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Revaccounts[ id=" + id + " ]";
    }
    
}
