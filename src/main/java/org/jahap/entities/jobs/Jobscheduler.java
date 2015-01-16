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
package org.jahap.entities.jobs;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "jobscheduler")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Jobscheduler.findAll", query = "SELECT j FROM Jobscheduler j"),
	@NamedQuery(name = "Jobscheduler.findById", query = "SELECT j FROM Jobscheduler j WHERE j.id = :id"),
	@NamedQuery(name = "Jobscheduler.findByTyp", query = "SELECT j FROM Jobscheduler j WHERE j.typ = :typ"),
	@NamedQuery(name = "Jobscheduler.findByName", query = "SELECT j FROM Jobscheduler j WHERE j.name = :name")})
public class Jobscheduler implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
        @Basic(optional = false)
        @NotNull
        @Column(name = "id")
	private Long id;
	@Size(max = 50)
        @Column(name = "typ")
	private String typ;
	@Size(max = 100)
        @Column(name = "name")
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idJobscheduler")
	private Collection<JobJobscheduler> jobJobschedulerCollection;

	public Jobscheduler() {
	}

	public Jobscheduler(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public Collection<JobJobscheduler> getJobJobschedulerCollection() {
		return jobJobschedulerCollection;
	}

	public void setJobJobschedulerCollection(Collection<JobJobscheduler> jobJobschedulerCollection) {
		this.jobJobschedulerCollection = jobJobschedulerCollection;
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
		if (!(object instanceof Jobscheduler)) {
			return false;
		}
		Jobscheduler other = (Jobscheduler) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.jahap.entities.jobs.Jobscheduler[ id=" + id + " ]";
	}
	
}
