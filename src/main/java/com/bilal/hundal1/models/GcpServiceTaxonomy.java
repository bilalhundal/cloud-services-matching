package com.bilal.hundal1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class GcpServiceTaxonomy {
	@Id
	@Column(length = 256)
	String gcpServicePath;
	@Column(length = 256)
	String canonicalServicePath;
	String gcpServiceName;
	String gcpClass;
	String gcpFamily;
	String gcpType;
	String gcpSubType;
	public GcpServiceTaxonomy() {
		
	}
	public GcpServiceTaxonomy(String gcpServicePath, String canonicalServicePath, String gcpServiceName,
			String gcpClass, String gcpFamily, String gcpType, String gcpSubType) {
		super();
		this.gcpServicePath = gcpServicePath;
		this.canonicalServicePath = canonicalServicePath;
		this.gcpServiceName = gcpServiceName;
		this.gcpClass = gcpClass;
		this.gcpFamily = gcpFamily;
		this.gcpType = gcpType;
		this.gcpSubType = gcpSubType;
	}
	public String getgcpServicePath() {
		return gcpServicePath;
	}
	public void setgcpServicePath(String gcpServicePath) {
		this.gcpServicePath = gcpServicePath;
	}
	public String getCanonicalServicePath() {
		return canonicalServicePath;
	}
	public void setCanonicalServicePath(String canonicalServicePath) {
		this.canonicalServicePath = canonicalServicePath;
	}
	public String getgcpServiceName() {
		return gcpServiceName;
	}
	public void setgcpServiceName(String gcpServiceName) {
		this.gcpServiceName = gcpServiceName;
	}
	public String getgcpClass() {
		return gcpClass;
	}
	public void setgcpClass(String gcpClass) {
		this.gcpClass = gcpClass;
	}
	public String getgcpFamily() {
		return gcpFamily;
	}
	public void setgcpFamily(String gcpFamily) {
		this.gcpFamily = gcpFamily;
	}
	public String getgcpType() {
		return gcpType;
	}
	public void setgcpType(String gcpType) {
		this.gcpType = gcpType;
	}
	public String getgcpSubType() {
		return gcpSubType;
	}
	public void setgcpSubType(String gcpSubType) {
		this.gcpSubType = gcpSubType;
	}
	

}
