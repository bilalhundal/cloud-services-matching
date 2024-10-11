package com.bilal.hundal1.controllers;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.bilal.hundal1.service.CloudMatchService;
@WebMvcTest(MainController.class)
class MainControllerTest {
	 @MockBean
	 CloudMatchService cloudMatchService;
	 // MockMvc Object for testing APi calls
	 @Autowired
     private MockMvc mvc;
     @Captor
     ArgumentCaptor<List<String>> stringArrayCap;  
     @Captor
     ArgumentCaptor<String> stringCap;
     @Test
    void testGetTaxonomy() throws Exception{
    	 //When
     	this.mvc.perform(get("/cloudmatch/taxonomy"));
     	//And Then Verify
     	verify(cloudMatchService).getTaxonomy();
 	}
 	/**
 	 * 
 	 * @throws Exception
 	 */
	@Test
	void testGetCloudComparisonServices() throws Exception {
		// Given
		String Location="asia-easternasia-chn-beijing";
		String Service="Standard Regional Object Storage";
		 //when
    	this.mvc.perform(get("/cloudmatch/compare").param("service", Service)
    			.param("location", Location).param("providers",  new String[]{"aws", "gcp"})
    			.param("properties", new String[]{"storage", "logging"}));
    	//And Then Verify
    	verify(cloudMatchService).compare(stringCap.capture(), stringArrayCap.capture(), stringCap.capture(), stringArrayCap.capture());
	}

}
