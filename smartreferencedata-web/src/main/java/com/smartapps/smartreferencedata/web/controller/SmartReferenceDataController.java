package com.smartapps.smartreferencedata.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.annotations.GlobalApiReponsesDelete;
import com.smartapps.smartlib.annotations.GlobalApiReponsesGet;
import com.smartapps.smartlib.annotations.GlobalApiReponsesPost;
import com.smartapps.smartlib.annotations.GlobalApiReponsesPut;
import com.smartapps.smartlib.dto.ImportRefDataResponseDto;
import com.smartapps.smartlib.dto.ReferenceDataDto;
import com.smartapps.smartlib.dto.RegisterRefDataResponseDto;
import com.smartapps.smartlib.dto.SearchReferenceDataRequestDto;
import com.smartapps.smartlib.dto.SearchReferenceDataResponseDto;
import com.smartapps.smartlib.util.SmartHttpUtil;
import com.smartapps.smartlib.validators.annotations.ValidAppId;
import com.smartapps.smartreferencedata.web.util.SmartReferenceDataWebUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping(path = SmartReferenceDataWebUtil.CONTEXT_ROOT, produces = MediaType.APPLICATION_JSON)
public class SmartReferenceDataController extends CommonController {

	@Operation(summary = SmartReferenceDataWebUtil.REGISTER_REFDATA_OPERATION)
	@GlobalApiReponsesPost
	@PostMapping(SmartReferenceDataWebUtil.REGISTER_REFDATA)
	public ResponseEntity<ReferenceDataDto> register(
			@RequestHeader(value = SmartHttpUtil.APP_ID_HEADER, required = true) @ValidAppId String appId,
			@RequestHeader(value = SmartHttpUtil.USER_ID_HEADER, required = true) String userId,
			@RequestHeader(value = SmartHttpUtil.USER_GROUPS_HEADER, required = false) String userGroups,
			@Parameter(name = "registerRefdata", description = "JSON with request object in and out", required = true) @Valid @RequestBody ReferenceDataDto obj) 
			throws JsonProcessingException {
		
		/** Logging **/
		MDC.put(SmartHttpUtil.APP_ID_HEADER, appId);
		MDC.put(SmartHttpUtil.USER_ID_HEADER, userId);
		MDC.put(SmartHttpUtil.USER_GROUPS_HEADER, userGroups);

		return ResponseEntity.ok().body(referenceDataServiceFacade.register(obj));
	}

	@Operation(summary = SmartReferenceDataWebUtil.REGISTER_REFDATA_TYPE_OPERATION)
	@GlobalApiReponsesPost
	@PostMapping(SmartReferenceDataWebUtil.REGISTER_REFDATA_TYPE)
	public ResponseEntity<RegisterRefDataResponseDto> registerByType(
			@RequestHeader(value = SmartHttpUtil.APP_ID_HEADER, required = true) @ValidAppId String appId,
			@RequestHeader(value = SmartHttpUtil.USER_ID_HEADER, required = true) String userId,
			@RequestHeader(value = SmartHttpUtil.USER_GROUPS_HEADER, required = false) String userGroups,
			@PathVariable("type") @Valid String type,
			@Parameter(name = "registerRefdata", description = "JSON with request object in and out", required = true) @Valid @RequestBody List<ReferenceDataDto> objList) 
			throws JsonProcessingException {
		
		/** Logging **/
		MDC.put(SmartHttpUtil.APP_ID_HEADER, appId);
		MDC.put(SmartHttpUtil.USER_ID_HEADER, userId);
		MDC.put(SmartHttpUtil.USER_GROUPS_HEADER, userGroups);

		return ResponseEntity.ok().body(referenceDataServiceFacade.registerByType(type, objList));
	}

	@Operation(summary = SmartReferenceDataWebUtil.RETRIEVE_REFDATAS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.RETRIEVE_REFDATAS)
	public ResponseEntity<List<ReferenceDataDto>> retrieveAll(HttpServletRequest request) 
			throws Exception {
//		  WebService.setGeoNamesServer("api.geonames.org");
//		  WebService.setUserName("shylendra.tm"); // add your username here
//		  
//		  ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
//		  searchCriteria.setQ("Canberra");
//		  ToponymSearchResult searchResult = WebService.search(searchCriteria);
//		  for (Toponym toponym : searchResult.getToponyms()) {
//		     System.out.println(toponym.getName()+"-"+ 
//		  toponym.getCountryName()+"-"+ 
//		    		 toponym.getCountryCode());
//		  }
		return ResponseEntity.ok().body(referenceDataServiceFacade.retrieveAll());
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
			@RequestHeader(value = SmartHttpUtil.APP_ID_HEADER, required = true) @ValidAppId String appId,
			@RequestHeader(value = SmartHttpUtil.USER_ID_HEADER, required = true) String userId,
			@RequestHeader(value = SmartHttpUtil.USER_GROUPS_HEADER, required = false) String userGroups,
			@PathVariable("code") @Valid String code,
			@PathVariable("type") @Valid String type,
			@Parameter(name = "updateReferenceData", description = "JSON with request object in and out", required = true) @Valid @RequestBody ReferenceDataDto obj,
			HttpServletRequest request) 
			throws JsonProcessingException {
		
		/** Logging **/
		MDC.put(SmartHttpUtil.APP_ID_HEADER, appId);
		MDC.put(SmartHttpUtil.USER_ID_HEADER, userId);
		MDC.put(SmartHttpUtil.USER_GROUPS_HEADER, userGroups);

		obj.setRefDataCode(code);
		obj.setRefDataType(type);
		obj.setProcApprId(appId);
		obj.setProcUserId(userId);
		obj.setProcUserIpAddress(SmartHttpUtil.getIpAddress(request));
		return ResponseEntity.ok().body(referenceDataServiceFacade.update(obj));
	}

	@Operation(summary = SmartReferenceDataWebUtil.DELETE_REFDATA_OPERATION)
	@GlobalApiReponsesDelete
	@DeleteMapping(SmartReferenceDataWebUtil.DELETE_REFDATA)
	public ResponseEntity<String> deleteById(
			@RequestHeader(value = SmartHttpUtil.APP_ID_HEADER, required = true) @ValidAppId String appId,
			@RequestHeader(value = SmartHttpUtil.USER_ID_HEADER, required = true) String userId,
			@RequestHeader(value = SmartHttpUtil.USER_GROUPS_HEADER, required = false) String userGroups,
			@PathVariable("code") @Valid String code,
			@PathVariable("type") @Valid String type) {
		
		/** Logging **/
		MDC.put(SmartHttpUtil.APP_ID_HEADER, appId);
		MDC.put(SmartHttpUtil.USER_ID_HEADER, userId);
		MDC.put(SmartHttpUtil.USER_GROUPS_HEADER, userGroups);

		referenceDataServiceFacade.delete(code, type);
		return ResponseEntity.ok().body("DELETED");
	}

	@Operation(summary = SmartReferenceDataWebUtil.SEARCH_REFDATA_OPERATION)
	@GlobalApiReponsesPost
	@PostMapping(SmartReferenceDataWebUtil.SEARCH_REFDATA)
	public ResponseEntity<List<SearchReferenceDataResponseDto>> search(
			@Parameter(name = "searchRefdata", description = "JSON with request object in and out", required = true) @Valid @RequestBody SearchReferenceDataRequestDto obj) 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(referenceDataServiceFacade.search(obj));
	}

	@Operation(summary = SmartReferenceDataWebUtil.EXPORT_REFDATAS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.EXPORT_REFDATAS)
	public ResponseEntity<byte[]> exportData(
			@RequestParam(name = "type", required = false) String type) throws JsonProcessingException {
		
		String jsonData = referenceDataServiceFacade.exportData(type);
		String fileName = SmartHttpUtil.getServerHostName(); 
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+ fileName +".json")
				.contentLength(jsonData.length())
				.body(jsonData.getBytes());
	}


	@Operation(summary = SmartReferenceDataWebUtil.IMPORT_REFDATAS_OPERATION)
	@GlobalApiReponsesGet
	@PostMapping(SmartReferenceDataWebUtil.IMPORT_REFDATAS)
	public ResponseEntity<ImportRefDataResponseDto> importData(@RequestParam("file") @Valid @NotNull MultipartFile file) throws IOException {
		return ResponseEntity.ok().body(referenceDataServiceFacade.importData(file));
	}
	

}
