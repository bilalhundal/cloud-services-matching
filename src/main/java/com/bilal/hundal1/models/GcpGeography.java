package com.bilal.hundal1.models;

import java.util.Date;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
public class GcpGeography {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int gcpGeographyId;
	@Column(nullable = false)
	String gcpGeography;
	@Column(nullable = false)
	String gcpRegion;
	@Column(nullable = false)
	String gcpLocation;
	@Column(nullable = false)
	short gcpAz;
	@Column(nullable = false)
	String status;
	@Column(nullable = false)
	@Basic
	@Temporal(TemporalType.DATE)
	Date openingDate;
	String address;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	  @JoinColumn(name = "un_geography_id", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
	  @JsonIgnore
	  private UnGeography unGeography;
	public GcpGeography() {
		
	}
	public GcpGeography(int gcpGeographyId, String gcpGeography, String gcpRegion, String gcpLocation, short gcpAz,
			String status, Date openingDate, String address, UnGeography unGeography) {
		super();
		this.gcpGeographyId = gcpGeographyId;
		this.gcpGeography = gcpGeography;
		this.gcpRegion = gcpRegion;
		this.gcpLocation = gcpLocation;
		this.gcpAz = gcpAz;
		this.status = status;
		this.openingDate = openingDate;
		this.address = address;
		this.unGeography = unGeography;
	}
	public int getGcpGeographyId() {
		return gcpGeographyId;
	}
	public void setGcpGeographyId(int gcpGeographyId) {
		this.gcpGeographyId = gcpGeographyId;
	}
	public String getGcpGeography() {
		return gcpGeography;
	}
	public void setGcpGeography(String gcpGeography) {
		this.gcpGeography = gcpGeography;
	}
	public String getGcpRegion() {
		return gcpRegion;
	}
	public void setGcpRegion(String gcpRegion) {
		this.gcpRegion = gcpRegion;
	}
	public String getGcpLocation() {
		return gcpLocation;
	}
	public void setGcpLocation(String gcpLocation) {
		this.gcpLocation = gcpLocation;
	}
	public short getGcpAz() {
		return gcpAz;
	}
	public void setGcpAz(short gcpAz) {
		this.gcpAz = gcpAz;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public UnGeography getUnGeography() {
		return unGeography;
	}
	public void setUnGeography(UnGeography unGeography) {
		this.unGeography = unGeography;
	}
	
	
}
