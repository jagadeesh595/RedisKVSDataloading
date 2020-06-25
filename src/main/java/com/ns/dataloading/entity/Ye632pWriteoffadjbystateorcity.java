package com.ns.dataloading.entity;
// Generated 16 Aug, 2018 5:26:43 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Ye632pWriteoffadjbystateorcity generated by hbm2java
 */
@Entity
@Table(name = "YE632P_WriteOffAdjByStateorCity")
public class Ye632pWriteoffadjbystateorcity implements java.io.Serializable {

	private Long id;
	private Integer ywyearProcessingYear;
	private String ywcoidCompany;
	private String ywstatState;
	private String ywcityCity;
	private Long ywctycCountryCode;
	private Long ywdidDistributorId;
	private String ywfnamFirstName;
	private String ywlnamLastName;
	private BigDecimal ywamtWriteOffAmt;

	public Ye632pWriteoffadjbystateorcity() {
	}

	public Ye632pWriteoffadjbystateorcity(Integer ywyearProcessingYear, String ywcoidCompany, String ywstatState,
			String ywcityCity, Long ywctycCountryCode, Long ywdidDistributorId, String ywfnamFirstName,
			String ywlnamLastName, BigDecimal ywamtWriteOffAmt) {
		this.ywyearProcessingYear = ywyearProcessingYear;
		this.ywcoidCompany = ywcoidCompany;
		this.ywstatState = ywstatState;
		this.ywcityCity = ywcityCity;
		this.ywctycCountryCode = ywctycCountryCode;
		this.ywdidDistributorId = ywdidDistributorId;
		this.ywfnamFirstName = ywfnamFirstName;
		this.ywlnamLastName = ywlnamLastName;
		this.ywamtWriteOffAmt = ywamtWriteOffAmt;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "YWYEAR_ProcessingYear")
	public Integer getYwyearProcessingYear() {
		return this.ywyearProcessingYear;
	}

	public void setYwyearProcessingYear(Integer ywyearProcessingYear) {
		this.ywyearProcessingYear = ywyearProcessingYear;
	}

	@Column(name = "YWCOID_Company", length = 15)
	public String getYwcoidCompany() {
		return this.ywcoidCompany;
	}

	public void setYwcoidCompany(String ywcoidCompany) {
		this.ywcoidCompany = ywcoidCompany;
	}

	@Column(name = "YWSTAT_State", length = 15)
	public String getYwstatState() {
		return this.ywstatState;
	}

	public void setYwstatState(String ywstatState) {
		this.ywstatState = ywstatState;
	}

	@Column(name = "YWCITY_City", length = 45)
	public String getYwcityCity() {
		return this.ywcityCity;
	}

	public void setYwcityCity(String ywcityCity) {
		this.ywcityCity = ywcityCity;
	}

	@Column(name = "YWCTYC_CountryCode")
	public Long getYwctycCountryCode() {
		return this.ywctycCountryCode;
	}

	public void setYwctycCountryCode(Long ywctycCountryCode) {
		this.ywctycCountryCode = ywctycCountryCode;
	}

	@Column(name = "YWDID_DistributorId")
	public Long getYwdidDistributorId() {
		return this.ywdidDistributorId;
	}

	public void setYwdidDistributorId(Long ywdidDistributorId) {
		this.ywdidDistributorId = ywdidDistributorId;
	}

	@Column(name = "YWFNAM_FirstName")
	public String getYwfnamFirstName() {
		return this.ywfnamFirstName;
	}

	public void setYwfnamFirstName(String ywfnamFirstName) {
		this.ywfnamFirstName = ywfnamFirstName;
	}

	@Column(name = "YWLNAM_LastName")
	public String getYwlnamLastName() {
		return this.ywlnamLastName;
	}

	public void setYwlnamLastName(String ywlnamLastName) {
		this.ywlnamLastName = ywlnamLastName;
	}

	@Column(name = "YWAMT_WriteOffAmt", precision = 10)
	public BigDecimal getYwamtWriteOffAmt() {
		return this.ywamtWriteOffAmt;
	}

	public void setYwamtWriteOffAmt(BigDecimal ywamtWriteOffAmt) {
		this.ywamtWriteOffAmt = ywamtWriteOffAmt;
	}

	public Ye632pWriteoffadjbystateorcity copy() {
		Ye632pWriteoffadjbystateorcity ye = new Ye632pWriteoffadjbystateorcity();
		ye.ywyearProcessingYear = ywyearProcessingYear;
		ye.ywcoidCompany = ywcoidCompany;
		ye.ywstatState = ywstatState;
		ye.ywcityCity = ywcityCity;
		ye.ywctycCountryCode = ywctycCountryCode;
		ye.ywdidDistributorId = ywdidDistributorId;
		ye.ywfnamFirstName = ywfnamFirstName;
		ye.ywlnamLastName = ywlnamLastName;
		ye.ywamtWriteOffAmt = ywamtWriteOffAmt;
		return ye;
	}
}
