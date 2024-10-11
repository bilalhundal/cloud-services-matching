package com.bilal.hundal1.models;
public class Taxonomy {
	String canonicalServicePath;
	String canonicalServiceName;
	String canonicalClass;
	String canonicalFamily;
	String canonicalType;
	String canonicalSubType;
	
	public Taxonomy(String canonicalServicePath, String canonicalServiceName, String canonicalClass,
			String canonicalFamily, String canonicalType, String canonicalSubType) {
		super();
		this.canonicalServicePath = canonicalServicePath;
		this.canonicalServiceName = canonicalServiceName;
		this.canonicalClass = canonicalClass;
		this.canonicalFamily = canonicalFamily;
		this.canonicalType = canonicalType;
		this.canonicalSubType = canonicalSubType;
	}
	public Taxonomy() {
		
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
	

}
