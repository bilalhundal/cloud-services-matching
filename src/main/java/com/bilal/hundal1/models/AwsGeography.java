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
public class AwsGeography {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int awsGeographyId;
	@Column(nullable = false)
	String awsGeography;
	@Column(nullable = false)
	String awsRegion;
	@Column(nullable = false)
	String awsLocation;
	@Column(nullable = false)
	short awsAz;
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
	public AwsGeography() {
		
	}
	public AwsGeography(int awsGeographyId, String awsGeography, String awsRegion, String awsLocation, short awsAz,
			String status, Date openingDate, String address, UnGeography unGeography) {
		super();
		this.awsGeographyId = awsGeographyId;
		this.awsGeography = awsGeography;
		this.awsRegion = awsRegion;
		this.awsLocation = awsLocation;
		this.awsAz = awsAz;
		this.status = status;
		this.openingDate = openingDate;
		this.address = address;
		this.unGeography = unGeography;
	}
	public int getAwsGeographyId() {
		return awsGeographyId;
	}
	public void setAwsGeographyId(int awsGeographyId) {
		this.awsGeographyId = awsGeographyId;
	}
	public String getAwsGeography() {
		return awsGeography;
	}
	public void setAwsGeography(String awsGeography) {
		this.awsGeography = awsGeography;
	}
	public String getAwsRegion() {
		return awsRegion;
	}
	public void setAwsRegion(String awsRegion) {
		this.awsRegion = awsRegion;
	}
	public String getAwsLocation() {
		return awsLocation;
	}
	public void setAwsLocation(String awsLocation) {
		this.awsLocation = awsLocation;
	}
	public short getAwsAz() {
		return awsAz;
	}
	public void setAwsAz(short awsAz) {
		this.awsAz = awsAz;
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
	public String getaddress() {
		return address;
	}
	public void setaddress(String address) {
		this.address = address;
	}
	public UnGeography getUnGeography() {
		return unGeography;
	}
	public void setUnGeography(UnGeography unGeography) {
		this.unGeography = unGeography;
	}
	
	

}
