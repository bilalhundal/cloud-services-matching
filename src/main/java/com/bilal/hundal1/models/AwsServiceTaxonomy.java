package com.bilal.hundal1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AwsServiceTaxonomy {
	@Id
	@Column(length = 256)
	String awsServicePath;
	@Column(length = 256)
	String canonicalServicePath;
	String awsServiceName;
	String awsClass;
	String awsFamily;
	String awsType;
	String awsSubType;
	public AwsServiceTaxonomy() {
		
	}
	public AwsServiceTaxonomy(String awsServicePath, String canonicalServicePath, String awsServiceName,
			String awsClass, String awsFamily, String awsType, String awsSubType) {
		super();
		this.awsServicePath = awsServicePath;
		this.canonicalServicePath = canonicalServicePath;
		this.awsServiceName = awsServiceName;
		this.awsClass = awsClass;
		this.awsFamily = awsFamily;
		this.awsType = awsType;
		this.awsSubType = awsSubType;
	}
	public String getAwsServicePath() {
		return awsServicePath;
	}
	public void setAwsServicePath(String awsServicePath) {
		this.awsServicePath = awsServicePath;
	}
	public String getCanonicalServicePath() {
		return canonicalServicePath;
	}
	public void setCanonicalServicePath(String canonicalServicePath) {
		this.canonicalServicePath = canonicalServicePath;
	}
	public String getAwsServiceName() {
		return awsServiceName;
	}
	public void setAwsServiceName(String awsServiceName) {
		this.awsServiceName = awsServiceName;
	}
	public String getAwsClass() {
		return awsClass;
	}
	public void setAwsClass(String awsClass) {
		this.awsClass = awsClass;
	}
	public String getAwsFamily() {
		return awsFamily;
	}
	public void setAwsFamily(String awsFamily) {
		this.awsFamily = awsFamily;
	}
	public String getAwsType() {
		return awsType;
	}
	public void setAwsType(String awsType) {
		this.awsType = awsType;
	}
	public String getAwsSubType() {
		return awsSubType;
	}
	public void setAwsSubType(String awsSubType) {
		this.awsSubType = awsSubType;
	}
	

}
