package com.bilal.hundal1.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AwsProperties {
	@Id
	@Column(length=256)
	private String CanonicalPropertyName;
	private String CanonicalPropertyValue;
	@Column(length = 256)
	private String unitOfMeasurement;
	  @ManyToOne(fetch = FetchType.LAZY, optional = false)
	  @JoinColumn(name = "canonical_service_path", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
	  @JsonIgnore
	  private CanonicalServiceTaxonomy canonicalServiceTaxonomy;
	public AwsProperties() {
		
	}
	public AwsProperties(String canonicalPropertyName, String canonicalPropertyValue, String unitOfMeasurement,
			CanonicalServiceTaxonomy canonicalServiceTaxonomy) {
		super();
		CanonicalPropertyName = canonicalPropertyName;
		CanonicalPropertyValue = canonicalPropertyValue;
		this.unitOfMeasurement = unitOfMeasurement;
		this.canonicalServiceTaxonomy = canonicalServiceTaxonomy;
	}
	public String getCanonicalPropertyName() {
		return CanonicalPropertyName;
	}
	public void setCanonicalPropertyName(String canonicalPropertyName) {
		CanonicalPropertyName = canonicalPropertyName;
	}
	public String getCanonicalPropertyValue() {
		return CanonicalPropertyValue;
	}
	public void setCanonicalPropertyValue(String canonicalPropertyValue) {
		CanonicalPropertyValue = canonicalPropertyValue;
	}
	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}
	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}
	public CanonicalServiceTaxonomy getCanonicalServiceTaxonomy() {
		return canonicalServiceTaxonomy;
	}
	public void setCanonicalServiceTaxonomy(CanonicalServiceTaxonomy canonicalServiceTaxonomy) {
		this.canonicalServiceTaxonomy = canonicalServiceTaxonomy;
	}
	

}
