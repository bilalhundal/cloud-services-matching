package com.bilal.hundal1.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bilal.hundal1.DTO.ResponseEntityDTO;
import com.bilal.hundal1.Repos.CanonicalServiceTaxonomyRepo;
import com.bilal.hundal1.Repos.UnGeographyRepo;
import com.bilal.hundal1.models.AwsProperties;
import com.bilal.hundal1.models.AzureProperties;
import com.bilal.hundal1.models.CanonicalProperties;
import com.bilal.hundal1.models.CanonicalServiceTaxonomy;
import com.bilal.hundal1.models.GcpProperties;
import com.bilal.hundal1.models.MatchedProperty;
import com.bilal.hundal1.models.Taxonomy;
import com.bilal.hundal1.models.UnGeography;
/**
 * Main class with algorithm implementations
 * All logic of algorithm and database interaction and queries are implemented in this class
 * @Service
 * @author Bilal Saddique
 *
 */
@Service
public class CloudMatchService {
	/**
	 * JPA Repository for interactions with CanonicalServiceTaxonomy table
	 */
	@Autowired
	private CanonicalServiceTaxonomyRepo canonicalServiceTaxonomyRepo;
	/**
	 * JPA Repository for interactions with UnGeography table
	 */
	@Autowired
	private UnGeographyRepo unGeographyRepo;
	/**
	 * List of Cloud Service Providers like AWS, GCP and Azure
	 * getting list from src/main/resources/application.properties file 
	 */
	
	/**
	 * JPA Repository for Database operations on taxonomy tables in database
	 */
	@Value("#{'${list.of.providers}'.split(',')}") 
	private List<String> providerList;
	/**
	 * ResponseEntityDTO Object declaration 
	 */
	private ResponseEntityDTO responseEntityDTO;
	/**
	 * ResponseEntityDTO Object declaration 
	 */
	private List<String> properties;
	/**
	 * Object Declaration of Set for using to store common properties of service providers
	 */
	private Set<String> commonProperties;
	/**
	 * Object for checking if provider is not available in given location
	 */
	private boolean locationUnAvailable=false;
	/**
	 * Taxonomy object list for returning on /taxonomy call
	 */
	private List<Taxonomy> taxonomyList;
	/**
	 * Main method of code for comparison algorithm
	 * @param location
	 * @param providers
	 * @param service
	 * @param properties
	 * @return return response entity with response body based on comparison
	 */
    public ResponseEntity<ResponseEntityDTO> compare (String location,List<String> providers,String service
  			,List<String> property){
    LoggerServiceClass.info("CloudMatchService method compare():Beginning");
    this.properties=property;
    commonProperties=new HashSet<>();
    responseEntityDTO = new ResponseEntityDTO();
    locationUnAvailable=false;
    //Set Collection for storing providers available in given location
    Set<String> availableProviders=new HashSet<>();
 	//Set<> Variable for storing providers name in lower case string to avoid error
 	Set<String>lowercaseProviders=new HashSet<>();
 	//Storing all providers into Set as lower case 
 	providers.stream().map(s->s.toLowerCase()).forEach(s->lowercaseProviders.add(s));
 	 // Getting canonicalServiceTaxonomy Object with given CanonicalServiceName in APi request 
	 CanonicalServiceTaxonomy canonicalServiceTaxonomy= canonicalServiceTaxonomyRepo.findByCanonicalServiceName(service).orElse(null); 
	//Getting unGeography Object and all providers Geography Object(i.e AwsGeography, GcpGeography, AzureGeoGraphy on base of foreign key) with specific given locations  
	 UnGeography unGeography= unGeographyRepo.findById(location).orElse(null);
	 //Checking through loop if providers parameter are not other then list (aws, gcp, azure)
 	if(providerList==null) {
 		providerList=new ArrayList<>();
 	}
	 for(String s:lowercaseProviders) {
 		if(!providerList.contains(""+s)) {
 			 return new ResponseEntity<>(responseEntityDTO,HttpStatus.BAD_REQUEST);
 		}
 	} 
 	
 	// Checking if Location exists in list in database
 	if(unGeography==null) {
 		LoggerServiceClass.info("CloudMatchService method compare():Geography Not Found");
 		 return new ResponseEntity<>(responseEntityDTO,HttpStatus.BAD_REQUEST);
 	}
 	
 
// Checking if such CanonicalService with CanonicalServiceName present in table in database 	 
 	 if(canonicalServiceTaxonomy == null) {
 		LoggerServiceClass.info("CloudMatchService method compare():canonicalServiceTaxonomy Not Found");
 			 return new ResponseEntity<>(responseEntityDTO,HttpStatus.BAD_REQUEST);
 		}
 	 // Declaring Object for MatchedProperty which hold logic of matched property body
 	 // in Response Body
 	MatchedProperty matchedProperty;
 	
 	// Here getting service path name from object what already got from database on base of service name
 	String canonicalServicePath=canonicalServiceTaxonomy.getCanonicalServicePath();
 	
 	//Adding Service Path in Response Body
 	responseEntityDTO.setCanonicalService(canonicalServicePath);
 	
 	//Adding Region in Response Body
 	responseEntityDTO.setRegion(location);
 	
 	// Here Getting Canonical Properties list with Foreign key from canonicalServiceTaxonomy Object Not from database 
 	// All Properties are on base of Service Path got from canonicalServiceTaxonomy what fetched on base of name
 	//filter is applied for removing properties with value=NA
 	Set<CanonicalProperties> canonicalProperties=canonicalServiceTaxonomy.getCanonicalProperties();
    Set<String> missingValuePropeties=new HashSet<>();
 	// Here Getting AWS Properties list with Foreign key from canonicalServiceTaxonomy Object Not from database 
  	// All Properties are on base of Service Path got from canonicalServiceTaxonomy what fetched on base of name
 	//filter is applied for removing properties with value=NA
 	Set<AwsProperties> awsProperties=canonicalServiceTaxonomy.getAwsProperties().stream()
 			.filter(Prop->(!Prop.getCanonicalPropertyValue().trim().equalsIgnoreCase("na"))).collect(Collectors.toSet());
    // Here Getting GCP Properties list with Foreign key from canonicalServiceTaxonomy Object Not from database 
  	// All Properties are on base of Service Path got from canonicalServiceTaxonomy what fetched on base of name
 	//filter is applied for removing properties with value=NA		
 	Set<GcpProperties> gcpProperties=canonicalServiceTaxonomy.getGcpProperties().stream()
 			.filter(Prop->(!Prop.getCanonicalPropertyValue().trim().equalsIgnoreCase("na"))).collect(Collectors.toSet());
 	
    // Here Getting Azure Properties list with Foreign key from canonicalServiceTaxonomy Object Not from database 
  	// All Properties are on base of Service Path got from canonicalServiceTaxonomy what fetched on base of name
 	//filter is applied for removing properties with value=NA
 	Set<AzureProperties> azureProperties=canonicalServiceTaxonomy.getAzureProperties().stream()
 			.filter(Prop->(!Prop.getCanonicalPropertyValue().trim().equalsIgnoreCase("na"))).collect(Collectors.toSet());
 	
 	//Getting Names of all Canonical Properties into a Set Collection
 	Set<String> canonicalPropertyNames=canonicalProperties.stream().map(can->can.getCanonicalPropertyName()).collect(Collectors.toSet());
 	
 	
 	// If Specific List of  Properties are not provided in APi Request 
 	// Then add all Properties else add provided properties
 	if(properties!=null) {
 	 if(properties.size()==0) {
 	commonProperties.addAll(canonicalPropertyNames);
 	 }else {
 		 commonProperties.addAll(properties);
 	 }}else {
 		properties=new ArrayList<>();
 		commonProperties.addAll(canonicalPropertyNames);
 	 }
 	 
 	// Set Collection for storing providers names in request providers parameters 
 	// And Checking if given Service Provider is available in given location
 	// And Filtering Common Properties by removing those what are not AWS Properties 
 	Set<String> providersNames=new HashSet<>();
 	if(lowercaseProviders.contains("aws")) {
 		providersNames.add("aws");
 		if(unGeography.getAwsGeography()!=null) {
 			Set<String> awsPropertiesNames=awsProperties.stream().map(can->can.getCanonicalPropertyName()).collect(Collectors.toSet());
 			if(unGeography.getAwsGeography().size()>0) {
 				availableProviders.add("aws");
 				commonProperties=awsPropertiesNames.stream().filter(name->commonProperties.contains(name))
 						.collect(Collectors.toSet());
 			}else {
 			commonProperties=commonProperties.stream().filter(name->awsPropertiesNames.contains(name))
						.collect(Collectors.toSet());
 			locationUnAvailable=true;
 		}
 		}
 	}
    // Set Collection for storing providers names in request providers parameters 
  	// And Checking if given Service Provider is available in given location
  	// And Filtering Common Properties by removing those what are not Azure Properties
 	if(lowercaseProviders.contains("azure")) {
 		providersNames.add("azure");
 		if(unGeography.getAzureGeography()!=null) {
 			Set<String> azurePropertiesNames=azureProperties.stream().map(can->can.getCanonicalPropertyName()).collect(Collectors.toSet());
 			if(unGeography.getAzureGeography().size()>0) {
 				availableProviders.add("azure");
 				commonProperties=azurePropertiesNames.stream().filter(name->commonProperties.contains(name))
 						.collect(Collectors.toSet());
 			}else {
 			commonProperties=commonProperties.stream().filter(name->azurePropertiesNames.contains(name))
						.collect(Collectors.toSet());
 			locationUnAvailable=true;
 		}
 		}
 	}
    // Set Collection for storing providers names in request providers parameters 
  	// And Checking if given Service Provider is available in given location
    // And Filtering Common Properties by removing those what are not GCP Properties
 	if(lowercaseProviders.contains("gcp")) {
 		providersNames.add("gcp");
 		if(unGeography.getGcpGeography()!=null) {
 			Set<String> gcpPropertiesNames=gcpProperties.stream().map(can->can.getCanonicalPropertyName()).collect(Collectors.toSet());
 			if(unGeography.getGcpGeography().size()>0) {
 				availableProviders.add("gcp");
 				commonProperties=gcpPropertiesNames.stream().filter(name->commonProperties.contains(name))
 						.collect(Collectors.toSet());
 			}else {
 			commonProperties=commonProperties.stream().filter(name->gcpPropertiesNames.contains(name))
						.collect(Collectors.toSet());
 			locationUnAvailable=true;
 		}
 		}
 		
 	}
 	
 	//Looping through Common Properties and getting property name with property value and measurement unit 
 	// for each provider and storing in matched property
 	//In case if a provider missing in a given location
 	if(locationUnAvailable) {
 		commonProperties.clear();
 	}else {
     for(String comPropName:commonProperties) {
     	matchedProperty=new MatchedProperty();
     	matchedProperty.setCanonicalProperty(comPropName);
 	for(String provider:providers) {
 		switch(provider) {
 		case "aws":
 			Map<String,String> awsMatchProp=new HashMap<>();
 			awsProperties.stream().filter(awsProp->(awsProp.getCanonicalPropertyName().equalsIgnoreCase(comPropName)))
 			.filter(Prop->(!Prop.getCanonicalPropertyValue().trim().equalsIgnoreCase("na")))
 			.forEach((awsProp)->{
 				if(awsProp.getUnitOfMeasurement().trim().equalsIgnoreCase("na")) {
 					awsMatchProp.put(awsProp.getCanonicalPropertyName(), awsProp.getCanonicalPropertyValue());
 				}else {
 					awsMatchProp.put(awsProp.getCanonicalPropertyName(), awsProp.getCanonicalPropertyValue()+" "+awsProp.getUnitOfMeasurement());
 			}});
 			matchedProperty.getProperty().put("aws", awsMatchProp);
 			break;
 		case "gcp":
 			Map<String,String> gcpMatchProp=new HashMap<>();
 			gcpProperties.stream().filter(gProp->gProp.getCanonicalPropertyName().equalsIgnoreCase(comPropName))
 			.filter(Prop->(!Prop.getCanonicalPropertyValue().trim().equalsIgnoreCase("na")))
 			.forEach((gProp)->
 			{
 				if(gProp.getUnitOfMeasurement().trim().equalsIgnoreCase("na")) {
 					gcpMatchProp.put(gProp.getCanonicalPropertyName(), gProp.getCanonicalPropertyValue());
 				}else {
 					gcpMatchProp.put(gProp.getCanonicalPropertyName(), gProp.getCanonicalPropertyValue()+" "+gProp.getUnitOfMeasurement());
 			}}
 			);
 			matchedProperty.getProperty().put("gcp", gcpMatchProp);
 			break;
 		case "azure":
 			Map<String,String> azureMatchProp=new HashMap<>();
 			azureProperties.stream().filter(azProp->azProp.getCanonicalPropertyName().equalsIgnoreCase(comPropName))
 			.filter(Prop->(!Prop.getCanonicalPropertyValue().trim().equalsIgnoreCase("na")))
 			.forEach((azProp)->{
 				if(azProp.getUnitOfMeasurement().trim().equalsIgnoreCase("na")) {
 					azureMatchProp.put(azProp.getCanonicalPropertyName(), azProp.getCanonicalPropertyValue());
 				}else {
 				azureMatchProp.put(azProp.getCanonicalPropertyName(), azProp.getCanonicalPropertyValue()+" "+azProp.getUnitOfMeasurement());
 			}});
 			matchedProperty.getProperty().put("azure", azureMatchProp);
 			break;
 			
 		default:
 			LoggerServiceClass.info("Invalid Service Provider");
 			break;
 		
 		}
 	}
 	responseEntityDTO.getMatched().add(matchedProperty);
 	}
 	}
     responseEntityDTO.setUnMatched(new HashMap<String, Map<String, String>>());
    
    //Looping through all Providers and then common properties to find out 
    // what properties of a each providers are not in common properties so 
    // storing that properties for individual providers into UnMatched object
    // This is the case if properties are give 
     if(properties.size()>0) {
 	for(String provider:providersNames) {
 		switch(provider) {
 		case "aws":
 			if(availableProviders.contains("aws")) {
 			Map<String,String> awsUnMatchProp=new HashMap<>();
 			// Looping through common properties and filtering what properties of AWS are not in common properties list
 		    // and nor in request properties given list
 			awsProperties.stream().filter(awsProp->(!commonProperties.contains(awsProp.getCanonicalPropertyName())&&properties.contains(awsProp.getCanonicalPropertyName())))
 			.filter(Prop->(!Prop.getCanonicalPropertyValue().trim().equalsIgnoreCase("na")))
 			.forEach(
 					(Prop)->{
 		 				if(Prop.getUnitOfMeasurement().trim().equalsIgnoreCase("na")) {
 		 					awsUnMatchProp.put(Prop.getCanonicalPropertyName(), Prop.getCanonicalPropertyValue());
 		 				}else {
 		 					awsUnMatchProp.put(Prop.getCanonicalPropertyName(), Prop.getCanonicalPropertyValue()+" "+Prop.getUnitOfMeasurement());
 		 			}}
 					);
 			responseEntityDTO.getUnMatched().put("unmatched_aws", awsUnMatchProp);
 		}
 			break;
 		case "gcp":
 			if(availableProviders.contains("gcp")) {
 			Map<String,String> gcpUnMatchProp=new HashMap<>();
 		   // Looping through common properties and filtering what properties of GCP are not in common properties list 
 		   // and nor in request properties given list	
 			gcpProperties.stream().filter(gProp->(!commonProperties.contains(gProp.getCanonicalPropertyName())&& properties.contains(gProp.getCanonicalPropertyName())))
 			.filter(Prop->(!Prop.getCanonicalPropertyValue().trim().equalsIgnoreCase("na")))
 			.forEach(
 					(Prop)->{
 		 				if(Prop.getUnitOfMeasurement().trim().equalsIgnoreCase("na")) {
 		 					gcpUnMatchProp.put(Prop.getCanonicalPropertyName(), Prop.getCanonicalPropertyValue());
 		 				}else {
 		 					gcpUnMatchProp.put(Prop.getCanonicalPropertyName(), Prop.getCanonicalPropertyValue()+" "+Prop.getUnitOfMeasurement());
 		 			}}
 					);
 			responseEntityDTO.getUnMatched().put("unmatched_gcp", gcpUnMatchProp);
 		}
 			break;
 		case "azure":
 			if(availableProviders.contains("azure")) {
 			Map<String,String> azureUnMatchProp=new HashMap<>();
 		   // Looping through common properties and filtering what properties of Azure are not in common properties list
 		    // and nor in request properties given list
 			azureProperties.stream().filter(azProp->(!commonProperties.contains(azProp.getCanonicalPropertyName())&& properties.contains(azProp.getCanonicalPropertyName())))
 			.filter(Prop->(!Prop.getCanonicalPropertyValue().trim().equalsIgnoreCase("na")))
 			.forEach(
 					(Prop)->{
 		 				if(Prop.getUnitOfMeasurement().trim().equalsIgnoreCase("na")) {
 		 					azureUnMatchProp.put(Prop.getCanonicalPropertyName(), Prop.getCanonicalPropertyValue());
 		 				}else {
 		 					azureUnMatchProp.put(Prop.getCanonicalPropertyName(), Prop.getCanonicalPropertyValue()+" "+Prop.getUnitOfMeasurement());
 		 			}}
 					);
 			responseEntityDTO.getUnMatched().put("unmatched_azure", azureUnMatchProp);
 			}
 			break;
 		default:
 			LoggerServiceClass.warn("CloudMatchService method compare(): No Such Provider Exist");
 			break;
 		
 		}
 	}
     }else {
 // This is the case if properties are not given 
  	for(String provider:providersNames) {
  		switch(provider) {
  		case "aws":
  			if(availableProviders.contains("aws")) {
  			Map<String,String> awsUnMatchProp=new HashMap<>();
  			// Looping through common properties and filtering what properties of AWS are not in common properties list
  		    // and nor in request properties given list
  			awsProperties.stream().filter(awsProp->(!commonProperties.contains(awsProp.getCanonicalPropertyName())))
  			.filter(Prop->(!Prop.getCanonicalPropertyValue().trim().equalsIgnoreCase("na")))
  			.forEach(
  					(Prop)->{
 		 				if(Prop.getUnitOfMeasurement().trim().equalsIgnoreCase("na")) {
 		 					awsUnMatchProp.put(Prop.getCanonicalPropertyName(), Prop.getCanonicalPropertyValue());
 		 				}else {
 		 					awsUnMatchProp.put(Prop.getCanonicalPropertyName(), Prop.getCanonicalPropertyValue()+" "+Prop.getUnitOfMeasurement());
 		 			}}
  					);
  			responseEntityDTO.getUnMatched().put("unmatched_aws", awsUnMatchProp);
  		}
  			break;
  		case "gcp":
  			if(availableProviders.contains("gcp")) {
  			Map<String,String> gcpUnMatchProp=new HashMap<>();
  		   // Looping through common properties and filtering what properties of GCP are not in common properties list 
  		   // and nor in request properties given list	
  			gcpProperties.stream().filter(gProp->(!commonProperties.contains(gProp.getCanonicalPropertyName())))
  			.filter(Prop->(!Prop.getCanonicalPropertyValue().trim().equalsIgnoreCase("na")))
  			.forEach(
  					(Prop)->{
 		 				if(Prop.getUnitOfMeasurement().trim().equalsIgnoreCase("na")) {
 		 					gcpUnMatchProp.put(Prop.getCanonicalPropertyName(), Prop.getCanonicalPropertyValue());
 		 				}else {
 		 					gcpUnMatchProp.put(Prop.getCanonicalPropertyName(), Prop.getCanonicalPropertyValue()+" "+Prop.getUnitOfMeasurement());
 		 			}}
  					);
  			responseEntityDTO.getUnMatched().put("unmatched_gcp", gcpUnMatchProp);
  			}
  			break;
  		case "azure":
  			if(availableProviders.contains("azure")) {
  			Map<String,String> azureUnMatchProp=new HashMap<>();
  		   // Looping through common properties and filtering what properties of Azure are not in common properties list
  		    // and nor in request properties given list
  			azureProperties.stream().filter(azProp->(!commonProperties.contains(azProp.getCanonicalPropertyName())))
  			.filter(Prop->(!Prop.getCanonicalPropertyValue().trim().equalsIgnoreCase("na")))
  			.forEach(
  					(Prop)->{
 		 				if(Prop.getUnitOfMeasurement().trim().equalsIgnoreCase("na")) {
 		 					azureUnMatchProp.put(Prop.getCanonicalPropertyName(), Prop.getCanonicalPropertyValue());
 		 				}else {
 		 					azureUnMatchProp.put(Prop.getCanonicalPropertyName(), Prop.getCanonicalPropertyValue()+" "+Prop.getUnitOfMeasurement());
 		 			}}
  					);
  			responseEntityDTO.getUnMatched().put("unmatched_azure", azureUnMatchProp);
  			}
  			break;
  		default:
  			LoggerServiceClass.warn("CloudMatchService method compare(): No Such Provider Exist");
  			break;
  		
  		}
  	}
     }
 	
 	return new ResponseEntity<ResponseEntityDTO>(responseEntityDTO,HttpStatus.OK);
      }
    
    public ResponseEntity<List<Taxonomy>> getTaxonomy(){
    	taxonomyList=new ArrayList<>();
    	canonicalServiceTaxonomyRepo.findAll().stream()
    	.forEach(service->taxonomyList.add(new Taxonomy(service.getCanonicalServicePath(), service.getCanonicalServiceName(), service.getCanonicalClass(),
			service.getCanonicalFamily(), service.getCanonicalType(),service.getCanonicalSubType())));
    	return new ResponseEntity<>(taxonomyList,HttpStatus.OK);
	}
    
    
    
    
}
