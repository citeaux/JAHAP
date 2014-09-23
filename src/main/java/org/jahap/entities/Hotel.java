/*
 * The MIT License
 *
 * Copyright 2014 Open Jahap Community.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jahap.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "HOTEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hotel.findAll", query = "SELECT h FROM Hotel h"),
    @NamedQuery(name = "Hotel.findById", query = "SELECT h FROM Hotel h WHERE h.id = :id"),
    @NamedQuery(name = "Hotel.findByHotelCode", query = "SELECT h FROM Hotel h WHERE h.hotelCode = :hotelCode"),
    @NamedQuery(name = "Hotel.findByHotelName", query = "SELECT h FROM Hotel h WHERE h.hotelName = :hotelName"),
    @NamedQuery(name = "Hotel.findByHotelAdress", query = "SELECT h FROM Hotel h WHERE h.hotelAdress = :hotelAdress"),
    @NamedQuery(name = "Hotel.findByHotelBankaccountdata1", query = "SELECT h FROM Hotel h WHERE h.hotelBankaccountdata1 = :hotelBankaccountdata1"),
    @NamedQuery(name = "Hotel.findByHotelBankaccountdata2", query = "SELECT h FROM Hotel h WHERE h.hotelBankaccountdata2 = :hotelBankaccountdata2"),
    @NamedQuery(name = "Hotel.findByHotelLanguage", query = "SELECT h FROM Hotel h WHERE h.hotelLanguage = :hotelLanguage"),
    @NamedQuery(name = "Hotel.findByHotelCountry", query = "SELECT h FROM Hotel h WHERE h.hotelCountry = :hotelCountry"),
    @NamedQuery(name = "Hotel.findByHotelCurrency", query = "SELECT h FROM Hotel h WHERE h.hotelCurrency = :hotelCurrency"),
    @NamedQuery(name = "Hotel.findByHotelFootertext", query = "SELECT h FROM Hotel h WHERE h.hotelFootertext = :hotelFootertext")})
public class Hotel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 10)
    @Column(name = "HOTEL_CODE")
    private String hotelCode;
    @Size(max = 100)
    @Column(name = "HOTEL_NAME")
    private String hotelName;
    @Column(name = "HOTEL_ADRESS")
    private BigInteger hotelAdress;
    @Size(max = 200)
    @Column(name = "HOTEL_BANKACCOUNTDATA1")
    private String hotelBankaccountdata1;
    @Size(max = 200)
    @Column(name = "HOTEL_BANKACCOUNTDATA2")
    private String hotelBankaccountdata2;
    @Column(name = "HOTEL_LANGUAGE")
    private Integer hotelLanguage;
    @Column(name = "HOTEL_COUNTRY")
    private Integer hotelCountry;
    @Column(name = "HOTEL_CURRENCY")
    private Integer hotelCurrency;
    @Size(max = 200)
    @Column(name = "HOTEL_FOOTERTEXT")
    private String hotelFootertext;

    public Hotel() {
    }

    public Hotel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public BigInteger getHotelAdress() {
        return hotelAdress;
    }

    public void setHotelAdress(BigInteger hotelAdress) {
        this.hotelAdress = hotelAdress;
    }

    public String getHotelBankaccountdata1() {
        return hotelBankaccountdata1;
    }

    public void setHotelBankaccountdata1(String hotelBankaccountdata1) {
        this.hotelBankaccountdata1 = hotelBankaccountdata1;
    }

    public String getHotelBankaccountdata2() {
        return hotelBankaccountdata2;
    }

    public void setHotelBankaccountdata2(String hotelBankaccountdata2) {
        this.hotelBankaccountdata2 = hotelBankaccountdata2;
    }

    public Integer getHotelLanguage() {
        return hotelLanguage;
    }

    public void setHotelLanguage(Integer hotelLanguage) {
        this.hotelLanguage = hotelLanguage;
    }

    public Integer getHotelCountry() {
        return hotelCountry;
    }

    public void setHotelCountry(Integer hotelCountry) {
        this.hotelCountry = hotelCountry;
    }

    public Integer getHotelCurrency() {
        return hotelCurrency;
    }

    public void setHotelCurrency(Integer hotelCurrency) {
        this.hotelCurrency = hotelCurrency;
    }

    public String getHotelFootertext() {
        return hotelFootertext;
    }

    public void setHotelFootertext(String hotelFootertext) {
        this.hotelFootertext = hotelFootertext;
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
        if (!(object instanceof Hotel)) {
            return false;
        }
        Hotel other = (Hotel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jahap.entities.Hotel[ id=" + id + " ]";
    }
    
}
