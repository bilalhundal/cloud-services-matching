package com.bilal.hundal1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class AzureServiceTaxonomy {
	@Id
	@Column(length = 256)
	String azureServicePath;
	@Column(length = 256)
	String canonicalServicePath;
	String azureServiceName;
	String azureClass;
	String azureFamily;
	String azureType;
	String azureSubType;
	public AzureServiceTaxonomy() {
		
	}
	public AzureServiceTaxonomy(String azureServicePath, String canonicalServicePath, String azureServiceName,
			String azureClass, String azureFamily, String azureType, String azureSubType) {
		super();
		this.azureServicePath = azureServicePath;
		this.canonicalServicePath = canonicalServicePath;
		this.azureServiceName = azureServiceName;
		this.azureClass = azureClass;
		this.azureFamily = azureFamily;
		this.azureType = azureType;
		this.azureSubType = azureSubType;
	}
	public String getazureServicePath() {
		return azureServicePath;
	}
	public void setazureServicePath(String azureServicePath) {
		this.azureServicePath = azureServicePath;
	}
	public String getCanonicalServicePath() {
		return canonicalServicePath;
	}
	public void setCanonicalServicePath(String canonicalServicePath) {
		this.canonicalServicePath = canonicalServicePath;
	}
	public String getazureServiceName() {
		return azureServiceName;
	}
	public void setazureServiceName(String azureServiceName) {
		this.azureServiceName = azureServiceName;
	}
	public String getazureClass() {
		return azureClass;
	}
	public void setazureClass(String azureClass) {
		this.azureClass = azureClass;
	}
	public String getazureFamily() {
		return azureFamily;
	}
	public void setazureFamily(String azureFamily) {
		this.azureFamily = azureFamily;
	}
	public String getazureType() {
		return azureType;
	}
	public void setazureType(String azureType) {
		this.azureType = azureType;
	}
	public String getazureSubType() {
		return azureSubType;
	}
	public void setazureSubType(String azureSubType) {
		this.azureSubType = azureSubType;
	}
	

}
