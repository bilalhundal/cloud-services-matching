package com.bilal.hundal1.models;

import java.util.HashMap;
import java.util.Map;

public class MatchedProperty {
	private String canonicalProperty;
	private Map<String, Map<String, String>> property;
	public MatchedProperty() {
		property=new HashMap<String, Map<String, String>>();
	}
	public MatchedProperty(String canonicalProperty, Map<String, Map<String, String>> property) {
		super();
		this.canonicalProperty = canonicalProperty;
		this.property = property;
	}
	public String getCanonicalProperty() {
		return canonicalProperty;
	}
	public void setCanonicalProperty(String canonicalProperty) {
		this.canonicalProperty = canonicalProperty;
	}
	public Map<String, Map<String, String>> getProperty() {
		return property;
	}
	public void setProperty(Map<String, Map<String, String>> property) {
		this.property = property;
	}
	

}
