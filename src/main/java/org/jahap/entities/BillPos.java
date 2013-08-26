/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "BILL_POS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BillPos.findAll", query = "SELECT b FROM BillPos b"),
    @NamedQuery(name = "BillPos.findById", query = "SELECT b FROM BillPos b WHERE b.id = :id")})
public class BillPos implements Serializable {
    @Column(name = "ARTICLES")
    private BigInteger articles;
    @Size(max = 50)
    @Column(name = "AMOUNT")
    private String amount;
    @Column(name = "ATRICLE")
    private BigInteger atricle;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "BILL", referencedColumnName = "ID")
    @ManyToOne
    private Bill bill;
    @JoinColumn(name = "ACCOUNT_POSITION", referencedColumnName = "ID")
    @ManyToOne
    private AccountPosition accountPosition;

    public BillPos() {
    }

    public BillPos(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public AccountPosition getAccountPosition() {
        return accountPosition;
    }

    public void setAccountPosition(AccountPosition accountPosition) {
        this.accountPosition = accountPosition;
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
        if (!(object instanceof BillPos)) {
            return false;
        }
        BillPos other = (BillPos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.BillPos[ id=" + id + " ]";
    }

    public BigInteger getArticles() {
        return articles;
    }

    public void setArticles(BigInteger articles) {
        this.articles = articles;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public BigInteger getAtricle() {
        return atricle;
    }

    public void setAtricle(BigInteger atricle) {
        this.atricle = atricle;
    }
    
}
