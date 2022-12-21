package com.smartapps.smartreferencedata.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.MDC;
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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.annotations.GlobalApiReponsesDelete;
import com.smartapps.smartlib.annotations.GlobalApiReponsesGet;
import com.smartapps.smartlib.annotations.GlobalApiReponsesPost;
import com.smartapps.smartlib.annotations.GlobalApiReponsesPut;
import com.smartapps.smartlib.dto.AssetDetailDto;
import com.smartapps.smartlib.util.SmartHttpUtil;
import com.smartapps.smartreferencedata.web.util.SmartReferenceDataWebUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping(SmartReferenceDataWebUtil.CONTEXT_ROOT)
public class AssetDetailController extends CommonController {

	@Operation(summary = SmartReferenceDataWebUtil.REGISTER_ASSETDETAIL_OPERATION)
	@GlobalApiReponsesPost
	@PostMapping(SmartReferenceDataWebUtil.REGISTER_ASSETDETAIL)
	public ResponseEntity<List<AssetDetailDto>> register(
			@RequestHeader(value = SmartHttpUtil.SA_APP_ID, required = true) String appId,
			@RequestHeader(value = SmartHttpUtil.SA_USER_ID, required = true) String userId,
			@Parameter(name = "registerAssetdetail", description = "JSON with AssetDetailDto object in and out", required = true) @Valid @RequestBody AssetDetailDto obj) 
			throws JsonProcessingException {

		/** Logging **/
		MDC.put(SmartHttpUtil.SA_APP_ID, appId);
		MDC.put(SmartHttpUtil.SA_USER_ID, userId);
		
		return ResponseEntity.ok().body(assetDetailServiceFacade.register(obj));
	}

	@Operation(summary = SmartReferenceDataWebUtil.RETRIEVE_ASSETDETAIL_ID_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.RETRIEVE_ASSETDETAIL_ID)
	public ResponseEntity<AssetDetailDto> retrieveByAssetDetailId(
			@RequestHeader(value = SmartHttpUtil.SA_APP_ID, required = true) String appId,
			@RequestHeader(value = SmartHttpUtil.SA_USER_ID, required = true) String userId,
			@PathVariable("id") @Valid Integer id) throws JsonProcessingException {

		/** Logging **/
		MDC.put(SmartHttpUtil.SA_APP_ID, appId);
		MDC.put(SmartHttpUtil.SA_USER_ID, userId);

		return ResponseEntity.ok().body(assetDetailServiceFacade.retrieveById(id));
	}

	@Operation(summary = SmartReferenceDataWebUtil.UPDATE_ASSETDETAIL_OPERATION)
	@GlobalApiReponsesPut
	@PutMapping(SmartReferenceDataWebUtil.UPDATE_ASSETDETAIL)
	public ResponseEntity<AssetDetailDto> update(
			@RequestHeader(value = SmartHttpUtil.SA_APP_ID, required = true) String appId,
			@RequestHeader(value = SmartHttpUtil.SA_USER_ID, required = true) String userId,
			@PathVariable("id") @Valid Integer id,
			@Parameter(name = "updateAssetdetail", description = "JSON with AssetDetailDto object in and out", required = true) @Valid @RequestBody AssetDetailDto obj) 
			throws JsonProcessingException {
			obj.setId(id);

			/** Logging **/
			MDC.put(SmartHttpUtil.SA_APP_ID, appId);
			MDC.put(SmartHttpUtil.SA_USER_ID, userId);

			return ResponseEntity.ok().body(assetDetailServiceFacade.update(obj));
	}

	@Operation(summary = SmartReferenceDataWebUtil.DELETE_ASSETDETAIL_OPERATION)
	@GlobalApiReponsesDelete
	@DeleteMapping(SmartReferenceDataWebUtil.DELETE_ASSETDETAIL)
	public ResponseEntity<String> deleteById(
			@RequestHeader(value = SmartHttpUtil.SA_APP_ID, required = true) String appId,
			@RequestHeader(value = SmartHttpUtil.SA_USER_ID, required = true) String userId,
			@PathVariable("id") @Valid Integer id) {

		/** Logging **/
		MDC.put(SmartHttpUtil.SA_APP_ID, appId);
		MDC.put(SmartHttpUtil.SA_USER_ID, userId);

		assetDetailServiceFacade.delete(id);
		return ResponseEntity.ok().body("DELETED");
	}

}
