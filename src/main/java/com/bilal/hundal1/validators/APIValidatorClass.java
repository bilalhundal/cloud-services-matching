package com.bilal.hundal1.validators;

import java.util.List;

import com.bilal.hundal1.DTO.ValidInputDTO;
import com.bilal.hundal1.service.LoggerServiceClass;
/**
 * Class for validating API Request Input
 * @author Bilal Saddique
 *
 */
public class APIValidatorClass {
	/**
	 * Method with all validating logic implementations
	 * @param location
	 * @param providers
	 * @param service
	 * @param properties
	 * @return
	 */
	public static ValidInputDTO validate(String location,List<String> providers,String service
			,List<String> properties) {
		LoggerServiceClass.info("APIValidatorClass method validate():Beginning");
		ValidInputDTO validInputDto=new ValidInputDTO();
		validInputDto.setMessage("");
		validInputDto.setStatus(true);
		if(providers==null||location==null||service==null) {
			LoggerServiceClass.error("APIValidatorClass method validate(): Required request params missing");
	 		//invalid
			validInputDto.setMessage("Invalid Request");
			validInputDto.setStatus(false);
	 		 return validInputDto;
	 	}
		// Checking if providers in Request are at least 2
		if(providers.size()<2) {
			//invalid
			LoggerServiceClass.error("APIValidatorClass method validate(): Clouds Service Providers are not given");
			validInputDto.setMessage("Invalid Request");
			validInputDto.setStatus(false);
	 		 return validInputDto;
	 	}
		
		
		return validInputDto;
	}
		
		
		
	

}
