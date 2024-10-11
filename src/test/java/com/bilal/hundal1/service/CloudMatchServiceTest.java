package com.bilal.hundal1.service;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.bilal.hundal1.CloudMatchApplication;
import com.bilal.hundal1.Repos.CanonicalServiceTaxonomyRepo;
import com.bilal.hundal1.Repos.UnGeographyRepo;
import jakarta.transaction.Transactional;
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@Transactional
@SpringBootTest(classes = CloudMatchApplication.class)
class CloudMatchServiceTest {
	@Mock
	private CanonicalServiceTaxonomyRepo canonicalServiceTaxonomyRepo;

	@Mock
	private UnGeographyRepo unGeographyRepo;
	@InjectMocks
	CloudMatchService cloudMatchService;
	//Given
	List<String> providers;
	String Location;
	String Service;
	@BeforeAll
	void given() {
		providers=new ArrayList<>();
		this.Location="asia-easternasia-chn-beijing";
		this.Service="Standard Regional Object Storage";
		providers.add("aws");
		providers.add("gcp");
	}
	@Test
	void testCompare() {
		//When CloudMatchService's method compare() is called
		this.cloudMatchService.compare(Location, providers, Service, null);
	   //then verify CanonicalServiceTaxonomyRepo's method findByCanonicalServiceName()
		// is called
		verify(canonicalServiceTaxonomyRepo).findByCanonicalServiceName(Service);
		//then verify UnGeographyRepo's method findById()
		// is called
		verify(unGeographyRepo).findById(Location);
	}

	@Test
	void testGetTaxonomy() {
		//When CloudMatchService's method getTaxonomy() is called
		this.cloudMatchService.getTaxonomy();
		//then verify CanonicalServiceTaxonomyRepo's method findAll()
		// is called
		verify(canonicalServiceTaxonomyRepo).findAll();
	}

}
