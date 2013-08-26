/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "RES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Res.findAll", query = "SELECT r FROM Res r"),
    @NamedQuery(name = "Res.findById", query = "SELECT r FROM Res r WHERE r.id = :id"),
    @NamedQuery(name = "Res.findByArrivaltime", query = "SELECT r FROM Res r WHERE r.arrivaltime = :arrivaltime"),
    @NamedQuery(name = "Res.findByArrivaldate", query = "SELECT r FROM Res r WHERE r.arrivaldate = :arrivaldate"),
    @NamedQuery(name = "Res.findByDeparturetime", query = "SELECT r FROM Res r WHERE r.departuretime = :departuretime"),
    @NamedQuery(name = "Res.findByResno", query = "SELECT r FROM Res r WHERE r.resno = :resno"),
    @NamedQuery(name = "Res.findByDeparturedate", query = "SELECT r FROM Res r WHERE r.departuredate = :departuredate")})
public class Res implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 50)
    @Column(name = "ARRIVALTIME")
    private String arrivaltime;
    @Size(max = 50)
    @Column(name = "ARRIVALDATE")
    private String arrivaldate;
    @Size(max = 50)
    @Column(name = "DEPARTURETIME")
    private String departuretime;
    @Size(max = 50)
    @Column(name = "RESNO")
    private String resno;
    @Size(max = 50)
    @Column(name = "DEPARTUREDATE")
    private String departuredate;
    @JoinColumn(name = "ADDRESSID", referencedColumnName = "ID")
    @ManyToOne
    private Address addressid;
    @OneToOne
    @JoinColumn(name = "ACCOUNT", referencedColumnName = "ID")   
    private Accounts resaccount;

    public Res() {
    }

    public Res(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public String getArrivaldate() {
        return arrivaldate;
    }

    public void setArrivaldate(String arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getResno() {
        return resno;
    }

    public void setResno(String resno) {
        this.resno = resno;
    }

    public String getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(String departuredate) {
        this.departuredate = departuredate;
    }

    public Address getAddressid() {
        return addressid;
    }

    public void setAddressid(Address addressid) {
        this.addressid = addressid;
    }

    public Accounts getAccount() {
        return resaccount;
    }

    public void setAccount(Accounts account) {
        this.resaccount = account;
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
        if (!(object instanceof Res)) {
            return false;
        }
        Res other = (Res) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Res[ id=" + id + " ]";
    }
    
}
