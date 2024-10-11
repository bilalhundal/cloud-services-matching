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
import jakarta.persistence.Table;
@Entity
@Table(name = "canonical_properties")
public class CanonicalProperties {
	@Id
	@Column(length = 256,name="canonical_property_name")
	String canonicalPropertyName;
	  @ManyToOne(fetch = FetchType.LAZY, optional = false)
	  @JoinColumn(name = "canonical_service_path", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
	  @JsonIgnore
	  private CanonicalServiceTaxonomy canonicalServiceTaxonomy;
    public CanonicalProperties() {
    	
    }
	public CanonicalProperties(String canonicalPropertyName, CanonicalServiceTaxonomy canonicalServiceTaxonomy) {
		super();
		this.canonicalPropertyName = canonicalPropertyName;
		this.canonicalServiceTaxonomy = canonicalServiceTaxonomy;
	}
	public String getCanonicalPropertyName() {
		return canonicalPropertyName;
	}
	public void setCanonicalPropertyName(String canonicalPropertyName) {
		this.canonicalPropertyName = canonicalPropertyName;
	}
	public CanonicalServiceTaxonomy getCanonicalServiceTaxonomy() {
		return canonicalServiceTaxonomy;
	}
	public void setCanonicalServiceTaxonomy(CanonicalServiceTaxonomy canonicalServiceTaxonomy) {
		this.canonicalServiceTaxonomy = canonicalServiceTaxonomy;
	}
    
	
}
