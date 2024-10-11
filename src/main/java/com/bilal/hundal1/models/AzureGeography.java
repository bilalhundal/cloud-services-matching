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
public class AzureGeography {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int azureGeographyId;
	@Column(nullable = false)
	String azureGeography;
	@Column(nullable = false)
	String azureRegion;
	@Column(nullable = false)
	String azureLocation;
	@Column(nullable = false)
	short azureAz;
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
	public AzureGeography(){
		
	}
	public AzureGeography(int azureGeographyId, String azureGeography, String azureRegion, String azureLocation,
			short azureAz, String status, Date openingDate, String address, UnGeography unGeography) {
		super();
		this.azureGeographyId = azureGeographyId;
		this.azureGeography = azureGeography;
		this.azureRegion = azureRegion;
		this.azureLocation = azureLocation;
		this.azureAz = azureAz;
		this.status = status;
		this.openingDate = openingDate;
		this.address = address;
		this.unGeography = unGeography;
	}
	public int getAzureGeographyId() {
		return azureGeographyId;
	}
	public void setAzureGeographyId(int azureGeographyId) {
		this.azureGeographyId = azureGeographyId;
	}
	public String getAzureGeography() {
		return azureGeography;
	}
	public void setAzureGeography(String azureGeography) {
		this.azureGeography = azureGeography;
	}
	public String getAzureRegion() {
		return azureRegion;
	}
	public void setAzureRegion(String azureRegion) {
		this.azureRegion = azureRegion;
	}
	public String getAzureLocation() {
		return azureLocation;
	}
	public void setAzureLocation(String azureLocation) {
		this.azureLocation = azureLocation;
	}
	public short getAzureAz() {
		return azureAz;
	}
	public void setAzureAz(short azureAz) {
		this.azureAz = azureAz;
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
