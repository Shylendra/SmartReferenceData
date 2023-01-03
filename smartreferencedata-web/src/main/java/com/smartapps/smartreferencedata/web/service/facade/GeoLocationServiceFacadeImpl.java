package com.smartapps.smartreferencedata.web.service.facade;

import java.util.List;

import javax.transaction.Transactional;

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
	public List<CodeValueDto> retrieveCountries() throws JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeValueDto retrieveCountry(String countryCode) throws JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CodeValueDto> retrieveStates(String countryCode) throws JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
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
