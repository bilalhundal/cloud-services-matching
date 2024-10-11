package com.bilal.hundal1.validators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.bilal.hundal1.DTO.ValidInputDTO;

class APIValidatorClassTest {
    //Given 
	ValidInputDTO  result;
	@Test
	void testValidate() {
		//When
		result=APIValidatorClass.validate("location", null, null, null);
		//then
		assertFalse(result.isStatus());
		//When provider have less then 2 entries
		List<String> providers=new ArrayList<>();
		providers.add("gcp");
		result=APIValidatorClass.validate("location", providers, "storage", null);
		//then
		assertFalse(result.isStatus());
		//When provider have less then 2 entries
				List<String> providers2=new ArrayList<>();
				providers2.add("gcp");
				providers2.add("aws");
				result=APIValidatorClass.validate("location", providers2, "storage", null);
				//then
				assertTrue(result.isStatus());
	}

}
