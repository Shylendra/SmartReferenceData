package com.smartapps.smartreferencedata.web.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
public class AddressTypesController extends CommonController {
	
	@Operation(summary = SmartReferenceDataWebUtil.ADDRESS_TYPES_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.ADDRESS_TYPES)
	public ResponseEntity<List<CodeValueDto>> retrieveAddressTypes() 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(addressTypeServiceFacade.retrieveAddressTypes());
	}

}
