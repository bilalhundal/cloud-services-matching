package com.bilal.hundal1.models;


import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "canonical_service_taxonomy")
public class CanonicalServiceTaxonomy {
	@Id
	@Column(length = 256,name = "canonical_service_path")
	String canonicalServicePath;
	String canonicalServiceName;
	String canonicalClass;
	String canonicalFamily;
	String canonicalType;
	String canonicalSubType;
	//@OneToMany relations for making CanonicalServicePath as foreign key to all these table
	 @OneToMany(fetch = FetchType.EAGER,mappedBy="canonicalServiceTaxonomy",cascade = CascadeType.ALL)
   private Set<CanonicalProperties> canonicalProperties;
	 @OneToMany(fetch = FetchType.EAGER,mappedBy="canonicalServiceTaxonomy",cascade = CascadeType.ALL)
    private Set<AwsProperties> awsProperties;
	 @OneToMany(fetch = FetchType.EAGER,mappedBy="canonicalServiceTaxonomy",cascade = CascadeType.ALL)
    private Set<GcpProperties> gcpProperties;
	 @OneToMany(fetch = FetchType.EAGER,mappedBy="canonicalServiceTaxonomy",cascade = CascadeType.ALL)
    private Set<AzureProperties> azureProperties;
	public CanonicalServiceTaxonomy() {
		
	}
	public CanonicalServiceTaxonomy(String canonicalServicePath, String canonicalServiceName, String canonicalClass,
			String canonicalFamily, String canonicalType, String canonicalSubType,
			Set<CanonicalProperties> canonicalProperties, Set<AwsProperties> awsProperties,
			Set<GcpProperties> gcpProperties, Set<AzureProperties> azureProperties) {
		super();
		this.canonicalServicePath = canonicalServicePath;
		this.canonicalServiceName = canonicalServiceName;
		this.canonicalClass = canonicalClass;
		this.canonicalFamily = canonicalFamily;
		this.canonicalType = canonicalType;
		this.canonicalSubType = canonicalSubType;
		this.canonicalProperties = canonicalProperties;
		this.awsProperties = awsProperties;
		this.gcpProperties = gcpProperties;
		this.azureProperties = azureProperties;
	}
	public String getCanonicalServicePath() {
		return canonicalServicePath;
	}
	public void setCanonicalServicePath(String canonicalServicePath) {
		this.canonicalServicePath = canonicalServicePath;
	}
	public String getCanonicalServiceName() {
		return canonicalServiceName;
	}
	public void setCanonicalServiceName(String canonicalServiceName) {
		this.canonicalServiceName = canonicalServiceName;
	}
	public String getCanonicalClass() {
		return canonicalClass;
	}
	public void setCanonicalClass(String canonicalClass) {
		this.canonicalClass = canonicalClass;
	}
	public String getCanonicalFamily() {
		return canonicalFamily;
	}
	public void setCanonicalFamily(String canonicalFamily) {
		this.canonicalFamily = canonicalFamily;
	}
	public String getCanonicalType() {
		return canonicalType;
	}
	public void setCanonicalType(String canonicalType) {
		this.canonicalType = canonicalType;
	}
	public String getCanonicalSubType() {
		return canonicalSubType;
	}
	public void setCanonicalSubType(String canonicalSubType) {
		this.canonicalSubType = canonicalSubType;
	}
	public Set<CanonicalProperties> getCanonicalProperties() {
		return canonicalProperties;
	}
	public void setCanonicalProperties(Set<CanonicalProperties> canonicalProperties) {
		this.canonicalProperties = canonicalProperties;
	}
	public Set<AwsProperties> getAwsProperties() {
		return awsProperties;
	}
	public void setAwsProperties(Set<AwsProperties> awsProperties) {
		this.awsProperties = awsProperties;
	}
	public Set<GcpProperties> getGcpProperties() {
		return gcpProperties;
	}
	public void setGcpProperties(Set<GcpProperties> gcpProperties) {
		this.gcpProperties = gcpProperties;
	}
	public Set<AzureProperties> getAzureProperties() {
		return azureProperties;
	}
	public void setAzureProperties(Set<AzureProperties> azureProperties) {
		this.azureProperties = azureProperties;
	}
		

}
