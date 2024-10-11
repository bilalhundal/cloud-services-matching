package com.bilal.hundal1.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bilal.hundal1.DTO.ResponseEntityDTO;
import com.bilal.hundal1.DTO.ValidInputDTO;
import com.bilal.hundal1.models.Taxonomy;
import com.bilal.hundal1.service.CloudMatchService;
import com.bilal.hundal1.service.LoggerServiceClass;
import com.bilal.hundal1.validators.APIValidatorClass;
@RestController
@RequestMapping("cloudmatch")
@CrossOrigin("*")
/**
 * Main Request Controller for receiving HTTP requests
 * @author Bilal Saddique
 *
 */
public class MainController{
	/**
	 * CloudMatch Service Object for performing functions on HTTP Requests
	 */
	@Autowired
	private CloudMatchService cloudMatchService;
	@GetMapping("/taxonomy")
	/**
	 * 
	 * @return List of all CanonicalServiceTaxonomy in database
	 */
	public ResponseEntity<List<Taxonomy>> getTaxonomy(){
		LoggerServiceClass.info("MainController method getTaxonomy(): Beginning");
		LoggerServiceClass.info("MainController method getTaxonomy(): END");
		return cloudMatchService.getTaxonomy() ;
	}
	@GetMapping("/compare")
	/**
	 * Method for listening HTTP requests from  cloudmatch/compare EndPoint
	 * @param location
	 * @param providers
	 * @param service
	 * @param properties
	 * @return  All Matched and individual services properties of cloud providers
	 */
	public ResponseEntity<ResponseEntityDTO> getCloudComparisonServices(@RequestParam("location")String location,
			@RequestParam("providers") List<String> providers,@RequestParam("service") String service
			,@RequestParam(name="properties",required = false) List<String> properties) {
		LoggerServiceClass.info("MainController method getCloudComparisonServices(): Beginning");
		//Validating Input using ValidInputDTO class Object 
		// Go to Validators/ValidInputClass.java for viewing functionalities of this Object
		ValidInputDTO validInputDto=APIValidatorClass.validate(location, providers, service, properties);
		if(!validInputDto.isStatus()) {
			LoggerServiceClass.error("MainController method getCloudComparisonServices(): Invalid Input");
			ResponseEntityDTO responseEntityDTO=new ResponseEntityDTO();
			return new ResponseEntity<>(responseEntityDTO,HttpStatus.BAD_REQUEST);
		}
		LoggerServiceClass.info("MainController method getCloudComparisonServices(): END");
		return cloudMatchService.compare(location, providers, service, properties);
	}

}














