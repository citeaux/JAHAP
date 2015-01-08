/*
 * The MIT License
 *
 * Copyright 2015 Open Jahap Community.
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
package org.jahap.entities.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "reservations")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Reservations.findAll", query = "SELECT r FROM Reservations r"),
	@NamedQuery(name = "Reservations.findByOrdererChristianname", query = "SELECT r FROM Reservations r WHERE r.ordererChristianname = :ordererChristianname"),
	@NamedQuery(name = "Reservations.findByOrdererCity", query = "SELECT r FROM Reservations r WHERE r.ordererCity = :ordererCity"),
	@NamedQuery(name = "Reservations.findByOrdererEmail", query = "SELECT r FROM Reservations r WHERE r.ordererEmail = :ordererEmail"),
	@NamedQuery(name = "Reservations.findByOrdererName", query = "SELECT r FROM Reservations r WHERE r.ordererName = :ordererName"),
	@NamedQuery(name = "Reservations.findByOrdererPhone", query = "SELECT r FROM Reservations r WHERE r.ordererPhone = :ordererPhone"),
	@NamedQuery(name = "Reservations.findByOrdererStreet", query = "SELECT r FROM Reservations r WHERE r.ordererStreet = :ordererStreet"),
	@NamedQuery(name = "Reservations.findByOrdererZipcode", query = "SELECT r FROM Reservations r WHERE r.ordererZipcode = :ordererZipcode"),
	@NamedQuery(name = "Reservations.findByOrdererGreeting", query = "SELECT r FROM Reservations r WHERE r.ordererGreeting = :ordererGreeting"),
	@NamedQuery(name = "Reservations.findByOrdererSalutation", query = "SELECT r FROM Reservations r WHERE r.ordererSalutation = :ordererSalutation"),
	@NamedQuery(name = "Reservations.findByOrdererTitle", query = "SELECT r FROM Reservations r WHERE r.ordererTitle = :ordererTitle"),
	@NamedQuery(name = "Reservations.findByOrdererRemarks", query = "SELECT r FROM Reservations r WHERE r.ordererRemarks = :ordererRemarks"),
	@NamedQuery(name = "Reservations.findByOrdererLanguageCode", query = "SELECT r FROM Reservations r WHERE r.ordererLanguageCode = :ordererLanguageCode"),
	@NamedQuery(name = "Reservations.findByOrdererLanguageName", query = "SELECT r FROM Reservations r WHERE r.ordererLanguageName = :ordererLanguageName"),
	@NamedQuery(name = "Reservations.findByGuestChristianname", query = "SELECT r FROM Reservations r WHERE r.guestChristianname = :guestChristianname"),
	@NamedQuery(name = "Reservations.findByGuestCity", query = "SELECT r FROM Reservations r WHERE r.guestCity = :guestCity"),
	@NamedQuery(name = "Reservations.findByGuestEmail", query = "SELECT r FROM Reservations r WHERE r.guestEmail = :guestEmail"),
	@NamedQuery(name = "Reservations.findByGuestName", query = "SELECT r FROM Reservations r WHERE r.guestName = :guestName"),
	@NamedQuery(name = "Reservations.findByGuestPhone", query = "SELECT r FROM Reservations r WHERE r.guestPhone = :guestPhone"),
	@NamedQuery(name = "Reservations.findByGuestStreet", query = "SELECT r FROM Reservations r WHERE r.guestStreet = :guestStreet"),
	@NamedQuery(name = "Reservations.findByGuestZipcode", query = "SELECT r FROM Reservations r WHERE r.guestZipcode = :guestZipcode"),
	@NamedQuery(name = "Reservations.findByGuestGreeting", query = "SELECT r FROM Reservations r WHERE r.guestGreeting = :guestGreeting"),
	@NamedQuery(name = "Reservations.findByGuestSalutation", query = "SELECT r FROM Reservations r WHERE r.guestSalutation = :guestSalutation"),
	@NamedQuery(name = "Reservations.findByGuestTitle", query = "SELECT r FROM Reservations r WHERE r.guestTitle = :guestTitle"),
	@NamedQuery(name = "Reservations.findByGuestRemarks", query = "SELECT r FROM Reservations r WHERE r.guestRemarks = :guestRemarks"),
	@NamedQuery(name = "Reservations.findByGuestLanguageCode", query = "SELECT r FROM Reservations r WHERE r.guestLanguageCode = :guestLanguageCode"),
	@NamedQuery(name = "Reservations.findByGuestLanguageName", query = "SELECT r FROM Reservations r WHERE r.guestLanguageName = :guestLanguageName"),
	@NamedQuery(name = "Reservations.findByServicesFromdate", query = "SELECT r FROM Reservations r WHERE r.servicesFromdate = :servicesFromdate"),
	@NamedQuery(name = "Reservations.findByServicesTodate", query = "SELECT r FROM Reservations r WHERE r.servicesTodate = :servicesTodate"),
	@NamedQuery(name = "Reservations.findByServicesPrice", query = "SELECT r FROM Reservations r WHERE r.servicesPrice = :servicesPrice"),
	@NamedQuery(name = "Reservations.findByServicesAmount", query = "SELECT r FROM Reservations r WHERE r.servicesAmount = :servicesAmount"),
	@NamedQuery(name = "Reservations.findByName", query = "SELECT r FROM Reservations r WHERE r.name = :name"),
	@NamedQuery(name = "Reservations.findByCode", query = "SELECT r FROM Reservations r WHERE r.code = :code"),
	@NamedQuery(name = "Reservations.findByResno", query = "SELECT r FROM Reservations r WHERE r.resno = :resno"),
	@NamedQuery(name = "Reservations.findByDeparturedate", query = "SELECT r FROM Reservations r WHERE r.departuredate = :departuredate"),
	@NamedQuery(name = "Reservations.findByCatName", query = "SELECT r FROM Reservations r WHERE r.catName = :catName"),
	@NamedQuery(name = "Reservations.findByCatDescription", query = "SELECT r FROM Reservations r WHERE r.catDescription = :catDescription"),
	@NamedQuery(name = "Reservations.findByArrivaldate", query = "SELECT r FROM Reservations r WHERE r.arrivaldate = :arrivaldate"),
	@NamedQuery(name = "Reservations.findByHotelName", query = "SELECT r FROM Reservations r WHERE r.hotelName = :hotelName"),
	@NamedQuery(name = "Reservations.findByHotelCode", query = "SELECT r FROM Reservations r WHERE r.hotelCode = :hotelCode"),
	@NamedQuery(name = "Reservations.findByHotelBankaccountdata1", query = "SELECT r FROM Reservations r WHERE r.hotelBankaccountdata1 = :hotelBankaccountdata1"),
	@NamedQuery(name = "Reservations.findByHotelBankaccountdata2", query = "SELECT r FROM Reservations r WHERE r.hotelBankaccountdata2 = :hotelBankaccountdata2"),
	@NamedQuery(name = "Reservations.findByHotelFootertext", query = "SELECT r FROM Reservations r WHERE r.hotelFootertext = :hotelFootertext")})
public class Reservations implements Serializable {
	private static final long serialVersionUID = 1L;
	@Size(max = 255)
        @Column(name = "orderer_christianname")
	private String ordererChristianname;
	@Size(max = 255)
        @Column(name = "orderer_city")
	private String ordererCity;
	@Size(max = 255)
        @Column(name = "orderer_email")
	private String ordererEmail;
	@Size(max = 255)
        @Column(name = "orderer_name")
	private String ordererName;
	@Size(max = 255)
        @Column(name = "orderer_phone")
	private String ordererPhone;
	@Size(max = 255)
        @Column(name = "orderer_street")
	private String ordererStreet;
	@Size(max = 255)
        @Column(name = "orderer_zipcode")
	private String ordererZipcode;
	@Size(max = 50)
        @Column(name = "orderer_greeting")
	private String ordererGreeting;
	@Size(max = 50)
        @Column(name = "orderer_salutation")
	private String ordererSalutation;
	@Size(max = 50)
        @Column(name = "orderer_title")
	private String ordererTitle;
	@Size(max = 200)
        @Column(name = "orderer_remarks")
	private String ordererRemarks;
	@Size(max = 5)
        @Column(name = "orderer_language_code")
	private String ordererLanguageCode;
	@Size(max = 50)
        @Column(name = "orderer_language_name")
	private String ordererLanguageName;
	@Size(max = 255)
        @Column(name = "guest_christianname")
	private String guestChristianname;
	@Size(max = 255)
        @Column(name = "guest_city")
	private String guestCity;
	@Size(max = 255)
        @Column(name = "guest_email")
	private String guestEmail;
	@Size(max = 255)
        @Column(name = "guest_name")
	private String guestName;
	@Size(max = 255)
        @Column(name = "guest_phone")
	private String guestPhone;
	@Size(max = 255)
        @Column(name = "guest_street")
	private String guestStreet;
	@Size(max = 255)
        @Column(name = "guest_zipcode")
	private String guestZipcode;
	@Size(max = 50)
        @Column(name = "guest_greeting")
	private String guestGreeting;
	@Size(max = 50)
        @Column(name = "guest_salutation")
	private String guestSalutation;
	@Size(max = 50)
        @Column(name = "guest_title")
	private String guestTitle;
	@Size(max = 200)
        @Column(name = "guest_remarks")
	private String guestRemarks;
	@Size(max = 5)
        @Column(name = "guest_language_code")
	private String guestLanguageCode;
	@Size(max = 50)
        @Column(name = "guest_language_name")
	private String guestLanguageName;
	@Column(name = "services_fromdate")
        @Temporal(TemporalType.DATE)
	private Date servicesFromdate;
	@Column(name = "services_todate")
        @Temporal(TemporalType.DATE)
	private Date servicesTodate;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "services_price")
	private BigDecimal servicesPrice;
	@Column(name = "services_amount")
	private Short servicesAmount;
	@Size(max = 255)
        @Column(name = "name")
	private String name;
	@Size(max = 255)
        @Column(name = "code")
	private String code;
	@Size(max = 50)
        @Column(name = "resno")
	private String resno;
	@Size(max = 50)
        @Column(name = "departuredate")
	private String departuredate;
	@Size(max = 100)
        @Column(name = "cat_name")
	private String catName;
	@Size(max = 255)
        @Column(name = "cat_description")
	private String catDescription;
	@Size(max = 50)
        @Column(name = "arrivaldate")
	private String arrivaldate;
	@Size(max = 100)
        @Column(name = "hotel_name")
	private String hotelName;
	@Size(max = 10)
        @Column(name = "hotel_code")
	private String hotelCode;
	@Size(max = 200)
        @Column(name = "hotel_bankaccountdata1")
	private String hotelBankaccountdata1;
	@Size(max = 200)
        @Column(name = "hotel_bankaccountdata2")
	private String hotelBankaccountdata2;
	@Size(max = 200)
        @Column(name = "hotel_footertext")
	private String hotelFootertext;

	public Reservations() {
	}

	public String getOrdererChristianname() {
		return ordererChristianname;
	}

	public void setOrdererChristianname(String ordererChristianname) {
		this.ordererChristianname = ordererChristianname;
	}

	public String getOrdererCity() {
		return ordererCity;
	}

	public void setOrdererCity(String ordererCity) {
		this.ordererCity = ordererCity;
	}

	public String getOrdererEmail() {
		return ordererEmail;
	}

	public void setOrdererEmail(String ordererEmail) {
		this.ordererEmail = ordererEmail;
	}

	public String getOrdererName() {
		return ordererName;
	}

	public void setOrdererName(String ordererName) {
		this.ordererName = ordererName;
	}

	public String getOrdererPhone() {
		return ordererPhone;
	}

	public void setOrdererPhone(String ordererPhone) {
		this.ordererPhone = ordererPhone;
	}

	public String getOrdererStreet() {
		return ordererStreet;
	}

	public void setOrdererStreet(String ordererStreet) {
		this.ordererStreet = ordererStreet;
	}

	public String getOrdererZipcode() {
		return ordererZipcode;
	}

	public void setOrdererZipcode(String ordererZipcode) {
		this.ordererZipcode = ordererZipcode;
	}

	public String getOrdererGreeting() {
		return ordererGreeting;
	}

	public void setOrdererGreeting(String ordererGreeting) {
		this.ordererGreeting = ordererGreeting;
	}

	public String getOrdererSalutation() {
		return ordererSalutation;
	}

	public void setOrdererSalutation(String ordererSalutation) {
		this.ordererSalutation = ordererSalutation;
	}

	public String getOrdererTitle() {
		return ordererTitle;
	}

	public void setOrdererTitle(String ordererTitle) {
		this.ordererTitle = ordererTitle;
	}

	public String getOrdererRemarks() {
		return ordererRemarks;
	}

	public void setOrdererRemarks(String ordererRemarks) {
		this.ordererRemarks = ordererRemarks;
	}

	public String getOrdererLanguageCode() {
		return ordererLanguageCode;
	}

	public void setOrdererLanguageCode(String ordererLanguageCode) {
		this.ordererLanguageCode = ordererLanguageCode;
	}

	public String getOrdererLanguageName() {
		return ordererLanguageName;
	}

	public void setOrdererLanguageName(String ordererLanguageName) {
		this.ordererLanguageName = ordererLanguageName;
	}

	public String getGuestChristianname() {
		return guestChristianname;
	}

	public void setGuestChristianname(String guestChristianname) {
		this.guestChristianname = guestChristianname;
	}

	public String getGuestCity() {
		return guestCity;
	}

	public void setGuestCity(String guestCity) {
		this.guestCity = guestCity;
	}

	public String getGuestEmail() {
		return guestEmail;
	}

	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestPhone() {
		return guestPhone;
	}

	public void setGuestPhone(String guestPhone) {
		this.guestPhone = guestPhone;
	}

	public String getGuestStreet() {
		return guestStreet;
	}

	public void setGuestStreet(String guestStreet) {
		this.guestStreet = guestStreet;
	}

	public String getGuestZipcode() {
		return guestZipcode;
	}

	public void setGuestZipcode(String guestZipcode) {
		this.guestZipcode = guestZipcode;
	}

	public String getGuestGreeting() {
		return guestGreeting;
	}

	public void setGuestGreeting(String guestGreeting) {
		this.guestGreeting = guestGreeting;
	}

	public String getGuestSalutation() {
		return guestSalutation;
	}

	public void setGuestSalutation(String guestSalutation) {
		this.guestSalutation = guestSalutation;
	}

	public String getGuestTitle() {
		return guestTitle;
	}

	public void setGuestTitle(String guestTitle) {
		this.guestTitle = guestTitle;
	}

	public String getGuestRemarks() {
		return guestRemarks;
	}

	public void setGuestRemarks(String guestRemarks) {
		this.guestRemarks = guestRemarks;
	}

	public String getGuestLanguageCode() {
		return guestLanguageCode;
	}

	public void setGuestLanguageCode(String guestLanguageCode) {
		this.guestLanguageCode = guestLanguageCode;
	}

	public String getGuestLanguageName() {
		return guestLanguageName;
	}

	public void setGuestLanguageName(String guestLanguageName) {
		this.guestLanguageName = guestLanguageName;
	}

	public Date getServicesFromdate() {
		return servicesFromdate;
	}

	public void setServicesFromdate(Date servicesFromdate) {
		this.servicesFromdate = servicesFromdate;
	}

	public Date getServicesTodate() {
		return servicesTodate;
	}

	public void setServicesTodate(Date servicesTodate) {
		this.servicesTodate = servicesTodate;
	}

	public BigDecimal getServicesPrice() {
		return servicesPrice;
	}

	public void setServicesPrice(BigDecimal servicesPrice) {
		this.servicesPrice = servicesPrice;
	}

	public Short getServicesAmount() {
		return servicesAmount;
	}

	public void setServicesAmount(Short servicesAmount) {
		this.servicesAmount = servicesAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatDescription() {
		return catDescription;
	}

	public void setCatDescription(String catDescription) {
		this.catDescription = catDescription;
	}

	public String getArrivaldate() {
		return arrivaldate;
	}

	public void setArrivaldate(String arrivaldate) {
		this.arrivaldate = arrivaldate;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
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

	public String getHotelFootertext() {
		return hotelFootertext;
	}

	public void setHotelFootertext(String hotelFootertext) {
		this.hotelFootertext = hotelFootertext;
	}
	
}
