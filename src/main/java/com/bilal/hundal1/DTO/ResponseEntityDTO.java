package com.bilal.hundal1.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bilal.hundal1.models.MatchedProperty;

public class ResponseEntityDTO {
	private String canonicalService;
	private String region;
	private List<MatchedProperty> matched;
	private Map<String, Map<String, String>> unMatched;
	public ResponseEntityDTO() {
		matched=new ArrayList<>();
	}
	public ResponseEntityDTO(String canonicalService, String region, List<MatchedProperty> matched,
	    Map<String, Map<String, String>> unMatched) {
		super();
		this.canonicalService = canonicalService;
		this.region = region;
		this.matched = matched;
		this.unMatched = unMatched;
	}
	public String getCanonicalService() {
		return canonicalService;
	}
	public void setCanonicalService(String canonicalService) {
		this.canonicalService = canonicalService;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public List<MatchedProperty> getMatched() {
		return matched;
	}
	public void setMatched(List<MatchedProperty> matched) {
		this.matched = matched;
	}
	public Map<String, Map<String, String>> getUnMatched() {
		return unMatched;
	}
	public void setUnMatched(Map<String, Map<String, String>> unMatched) {
		this.unMatched = unMatched;
	}
	
	

}
