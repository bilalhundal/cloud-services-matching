package com.bilal.hundal1.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Embeddable
public class UnGeography {
	@Id
	String unGeographyId;
	String unRegion;
	String unSubRegion;
	String unSubSubRegion;
	String country;
	@Column(length = 3)
	String isoCountryCode;
	String countrySubRegion;
	String city;
	// here are OneToMany relations to all providers geography tables
	@OneToMany(fetch = FetchType.EAGER,mappedBy="unGeography",cascade = CascadeType.ALL)
     private Set<AwsGeography> awsGeography;
	 @OneToMany(fetch = FetchType.EAGER,mappedBy="unGeography",cascade = CascadeType.ALL)
	 private Set<GcpGeography> gcpGeography;
	 @OneToMany(fetch = FetchType.EAGER,mappedBy="unGeography",cascade = CascadeType.ALL)
	 private Set<AzureGeography> azureGeography;
	public UnGeography() {
		
	}
	public UnGeography(String unGeographyId, String unRegion, String unSubRegion, String unSubSubRegion, String country,
			String isoCountryCode, String countrySubRegion, String city, Set<AwsGeography> awsGeography,
			Set<GcpGeography> gcpGeography, Set<AzureGeography> azureGeography) {
		super();
		this.unGeographyId = unGeographyId;
		this.unRegion = unRegion;
		this.unSubRegion = unSubRegion;
		this.unSubSubRegion = unSubSubRegion;
		this.country = country;
		this.isoCountryCode = isoCountryCode;
		this.countrySubRegion = countrySubRegion;
		this.city = city;
		this.awsGeography = awsGeography;
		this.gcpGeography = gcpGeography;
		this.azureGeography = azureGeography;
	}
	public String getUnGeographyId() {
		return unGeographyId;
	}
	public void setUnGeographyId(String unGeographyId) {
		this.unGeographyId = unGeographyId;
	}
	public String getUnRegion() {
		return unRegion;
	}
	public void setUnRegion(String unRegion) {
		this.unRegion = unRegion;
	}
	public String getUnSubRegion() {
		return unSubRegion;
	}
	public void setUnSubRegion(String unSubRegion) {
		this.unSubRegion = unSubRegion;
	}
	public String getUnSubSubRegion() {
		return unSubSubRegion;
	}
	public void setUnSubSubRegion(String unSubSubRegion) {
		this.unSubSubRegion = unSubSubRegion;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIsoCountryCode() {
		return isoCountryCode;
	}
	public void setIsoCountryCode(String isoCountryCode) {
		this.isoCountryCode = isoCountryCode;
	}
	public String getCountrySubRegion() {
		return countrySubRegion;
	}
	public void setCountrySubRegion(String countrySubRegion) {
		this.countrySubRegion = countrySubRegion;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Set<AwsGeography> getAwsGeography() {
		return awsGeography;
	}
	public void setAwsGeography(Set<AwsGeography> awsGeography) {
		this.awsGeography = awsGeography;
	}
	public Set<GcpGeography> getGcpGeography() {
		return gcpGeography;
	}
	public void setGcpGeography(Set<GcpGeography> gcpGeography) {
		this.gcpGeography = gcpGeography;
	}
	public Set<AzureGeography> getAzureGeography() {
		return azureGeography;
	}
	public void setAzureGeography(Set<AzureGeography> azureGeography) {
		this.azureGeography = azureGeography;
	}
	
	

}
