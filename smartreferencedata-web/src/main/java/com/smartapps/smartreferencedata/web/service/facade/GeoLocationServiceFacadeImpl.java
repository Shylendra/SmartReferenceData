package com.smartapps.smartreferencedata.web.service.facade;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartreferencedata.jpa.dto.CodeValueDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class GeoLocationServiceFacadeImpl extends CommonServiceFacade implements GeoLocationServiceFacade {
	
	private static final String REF_DATA_TYPE = "GEO-LOCATION";

	@Override
	public List<CodeValueDto> retrieveCountries() throws Exception {
		  WebService.setGeoNamesServer("api.geonames.org");
		  WebService.setUserName("shylendra.tm"); // add your username here
		  
		  ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		  //searchCriteria.setQ("Australia");
		  searchCriteria.setCountryCode("AU");
		  ToponymSearchResult searchResult = WebService.search(searchCriteria);
		  for (Toponym toponym : searchResult.getToponyms()) {
		     System.out.println(toponym.getName()+"-"+ toponym.getCountryName()+"-"+ toponym.getCountryCode());
		  }
		return null;
	}

	@Override
	public CodeValueDto retrieveCountry(String countryCode) throws JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CodeValueDto> retrieveStates(String countryCode) throws JsonProcessingException {
		
		List<CodeValueDto> states = new ArrayList<>();
		states.add(new CodeValueDto("NSW","New South Wales",null));
		states.add(new CodeValueDto("VIC","Victoria",null));
		states.add(new CodeValueDto("QLD","Queensland",null));
		states.add(new CodeValueDto("WA","Western Australia",null));
		states.add(new CodeValueDto("SA","South Australia",null));
		states.add(new CodeValueDto("TAS","Tasmania",null));
		states.add(new CodeValueDto("ACT","Australian Capital Territory",null));
		states.add(new CodeValueDto("NT","Northern Territory",null));
		
		return states;
	}

	@Override
	public CodeValueDto retrieveState(String countryCode, String stateCode) throws JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CodeValueDto> retrieveCities(String countryCode) throws JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CodeValueDto> retrieveCities(String countryCode, String stateCode) throws JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

}
