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
@Table(name = "RATES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rates.findAll", query = "SELECT r FROM Rates r"),
    @NamedQuery(name = "Rates.findById", query = "SELECT r FROM Rates r WHERE r.id = :id"),
    @NamedQuery(name = "Rates.findByName", query = "SELECT r FROM Rates r WHERE r.name = :name"),
    @NamedQuery(name = "Rates.findByPrice", query = "SELECT r FROM Rates r WHERE r.price = :price"),
    @NamedQuery(name = "Rates.findByCode", query = "SELECT r FROM Rates r WHERE r.code = :code")})
public class Rates implements Serializable, rates_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    // currently no constraint - the real revaccount will be set by the ratesplitter
    @Column(name="REVACCOUNT")
    private Long revaccount;
  
    @Column(name = "PRICE")
    private double price;
    @Size(max = 50)
    @Column(name = "CODE")
    private String code;
    @OneToMany( mappedBy = "rate")
    private Collection<AccountPosition> accountPositionCollection;

    public Rates() {
    }

    public Rates(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    @Override
    public Collection<AccountPosition> getAccountPositionCollection() {
        return accountPositionCollection;
    }

    @Override
    public void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection) {
        this.accountPositionCollection = accountPositionCollection;
    }
    
   
    
    @Override
    public void setRevaccount(Long revaccount) {
        this.revaccount = revaccount;
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
        if (!(object instanceof Rates)) {
            return false;
        }
        Rates other = (Rates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Rates[ id=" + id + " ]";
    }

    public long getRevaccount() {
      return this.revaccount;
    }
    
}
