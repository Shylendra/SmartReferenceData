package com.smartapps.smartreferencedata.web.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.annotations.GlobalApiReponsesGet;
import com.smartapps.smartreferencedata.jpa.dto.CodeValueDto;
import com.smartapps.smartreferencedata.web.util.SmartReferenceDataWebUtil;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping(path = SmartReferenceDataWebUtil.CONTEXT_ROOT, produces = MediaType.APPLICATION_JSON)
public class GeoLocationController extends CommonController {
	
	@Operation(summary = SmartReferenceDataWebUtil.RETRIEVE_COUNTRIES_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.RETRIEVE_COUNTRIES)
	public ResponseEntity<List<CodeValueDto>> retrieveCountries() 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(geoLocationServiceFacade.retrieveCountries());
	}
	
	@Operation(summary = SmartReferenceDataWebUtil.RETRIEVE_COUNTRY_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.RETRIEVE_COUNTRY)
	public ResponseEntity<CodeValueDto> retrieveCountry(
			@PathVariable("countryCode") @Valid String countryCode) 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(geoLocationServiceFacade.retrieveCountry(countryCode));
	}
	
	@Operation(summary = SmartReferenceDataWebUtil.RETRIEVE_STATES_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.RETRIEVE_STATES)
	public ResponseEntity<List<CodeValueDto>> retrieveStates(
			@PathVariable("countryCode") @Valid String countryCode) 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(geoLocationServiceFacade.retrieveStates(countryCode));
	}
	
	@Operation(summary = SmartReferenceDataWebUtil.RETRIEVE_STATE_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.RETRIEVE_STATE)
	public ResponseEntity<CodeValueDto> retrieveState(
			@PathVariable("countryCode") @Valid String countryCode,
			@PathVariable("stateCode") @Valid String stateCode) 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(geoLocationServiceFacade.retrieveState(countryCode, stateCode));
	}
	
	@Operation(summary = SmartReferenceDataWebUtil.RETRIEVE_COUNTRY_CITIES_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.RETRIEVE_COUNTRY_CITIES)
	public ResponseEntity<List<CodeValueDto>> retrieveCities(
			@PathVariable("countryCode") @Valid String countryCode) 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(geoLocationServiceFacade.retrieveCities(countryCode));
	}
	
	@Operation(summary = SmartReferenceDataWebUtil.RETRIEVE_CITIES_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.RETRIEVE_CITIES)
	public ResponseEntity<List<CodeValueDto>> retrieveCities(
			@PathVariable("countryCode") @Valid String countryCode,
			@PathVariable("stateCode") @Valid String stateCode) 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(geoLocationServiceFacade.retrieveCities(countryCode, stateCode));
	}

}
