package com.smartapps.smartreferencedata.web.service.facade;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartreferencedata.jpa.dto.CodeValueDto;

public interface GeoLocationServiceFacade {
	public List<CodeValueDto> retrieveCountries() throws JsonProcessingException;
	public CodeValueDto retrieveCountry(final String countryCode) throws JsonProcessingException;
	public List<CodeValueDto> retrieveStates(final String countryCode) throws JsonProcessingException;
	public CodeValueDto retrieveState(final String countryCode, final String stateCode) throws JsonProcessingException;
	public List<CodeValueDto> retrieveCities(final String countryCode) throws JsonProcessingException;
	public List<CodeValueDto> retrieveCities(final String countryCode, final String stateCode) throws JsonProcessingException;
}
