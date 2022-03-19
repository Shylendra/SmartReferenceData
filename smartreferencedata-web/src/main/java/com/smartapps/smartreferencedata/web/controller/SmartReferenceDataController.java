package com.smartapps.smartreferencedata.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.annotations.GlobalApiReponsesDelete;
import com.smartapps.smartlib.annotations.GlobalApiReponsesGet;
import com.smartapps.smartlib.annotations.GlobalApiReponsesPost;
import com.smartapps.smartlib.annotations.GlobalApiReponsesPut;
import com.smartapps.smartreferencedata.jpa.dto.ReferenceDataDto;
import com.smartapps.smartreferencedata.jpa.dto.SearchReferenceDataRequestDto;
import com.smartapps.smartreferencedata.jpa.dto.SearchReferenceDataResponseDto;
import com.smartapps.smartreferencedata.web.util.SmartReferenceDataWebUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping(SmartReferenceDataWebUtil.CONTEXT_ROOT)
public class SmartReferenceDataController extends CommonController {

	@Operation(summary = SmartReferenceDataWebUtil.REGISTER_REFDATA_OPERATION)
	@GlobalApiReponsesPost
	@PostMapping(SmartReferenceDataWebUtil.REGISTER_REFDATA)
	public ResponseEntity<ReferenceDataDto> register(
			@Parameter(name = "registerRefdata", description = "JSON with ReferenceDataDto object in and out", required = true) @Valid @RequestBody ReferenceDataDto obj) 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(referenceDataServiceFacade.register(obj));
	}

	@Operation(summary = SmartReferenceDataWebUtil.RETRIEVE_REFDATAS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.RETRIEVE_REFDATAS)
	public ResponseEntity<List<ReferenceDataDto>> retrieveAll(HttpServletRequest request) 
			throws IOException {
		return ResponseEntity.ok().body(referenceDataServiceFacade.retrieveAll());
	}

	@Operation(summary = SmartReferenceDataWebUtil.RETRIEVE_REFDATA_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.RETRIEVE_REFDATA)
	public ResponseEntity<ReferenceDataDto> retrieveById(
			@PathVariable("id") @Valid Integer id) {
		return ResponseEntity.ok().body(referenceDataServiceFacade.retrieveById(id));
	}

	@Operation(summary = SmartReferenceDataWebUtil.RETRIEVE_REFDATA_TYPE_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.RETRIEVE_REFDATA_TYPE)
	public ResponseEntity<List<ReferenceDataDto>> retrieveByRefDataType(
			@PathVariable("type") @Valid String type) throws JsonProcessingException {
		return ResponseEntity.ok().body(referenceDataServiceFacade.retrieveByRefDataType(type));
	}

	@Operation(summary = SmartReferenceDataWebUtil.UPDATE_REFDATA_OPERATION)
	@GlobalApiReponsesPut
	@PutMapping(SmartReferenceDataWebUtil.UPDATE_REFDATA)
	public ResponseEntity<ReferenceDataDto> update(
			@PathVariable("id") @Valid Integer id,
			@Parameter(name = "updateReferenceData", description = "JSON with ReferenceDataDto object in and out", required = true) @Valid @RequestBody ReferenceDataDto obj) 
			throws JsonProcessingException {
			obj.setId(id);
		return ResponseEntity.ok().body(referenceDataServiceFacade.update(obj));
	}

	@Operation(summary = SmartReferenceDataWebUtil.DELETE_REFDATA_OPERATION)
	@GlobalApiReponsesDelete
	@DeleteMapping(SmartReferenceDataWebUtil.DELETE_REFDATA)
	public ResponseEntity<String> deleteById(
			@PathVariable("id") @Valid Integer id) {
		referenceDataServiceFacade.deleteById(id);
		return ResponseEntity.ok().body("DELETED");
	}

	@Operation(summary = SmartReferenceDataWebUtil.SEARCH_REFDATA_OPERATION)
	@GlobalApiReponsesPost
	@PostMapping(SmartReferenceDataWebUtil.SEARCH_REFDATA)
	public ResponseEntity<List<SearchReferenceDataResponseDto>> search(
			@Parameter(name = "searchRefdata", description = "JSON with SearchReferenceDataRequestDto object in and out", required = true) @Valid @RequestBody SearchReferenceDataRequestDto obj) 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(referenceDataServiceFacade.search(obj));
	}
	
}
