package com.smartapps.smartreferencedata.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

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
@RequestMapping(path = SmartReferenceDataWebUtil.CONTEXT_ROOT, produces = MediaType.APPLICATION_JSON)
public class AssetDetailController extends CommonController {

	@Operation(summary = SmartReferenceDataWebUtil.REGISTER_ASSETDETAIL_OPERATION)
	@GlobalApiReponsesPost
	@PostMapping(SmartReferenceDataWebUtil.REGISTER_ASSETDETAIL)
	public ResponseEntity<List<AssetDetailDto>> register(
			@RequestHeader(value = SmartHttpUtil.APP_ID_HEADER, required = true) String appId,
			@RequestHeader(value = SmartHttpUtil.USER_ID_HEADER, required = true) String userId,
			@RequestHeader(value = SmartHttpUtil.USER_GROUPS_HEADER, required = false) String userGroups,
			@Parameter(name = "registerAssetdetail", description = "JSON with object in and out", required = true) @Valid @RequestBody AssetDetailDto obj,
			HttpServletRequest request) 
			throws JsonProcessingException {

		/** Logging **/
		MDC.put(SmartHttpUtil.APP_ID_HEADER, appId);
		MDC.put(SmartHttpUtil.USER_ID_HEADER, userId);
		MDC.put(SmartHttpUtil.USER_GROUPS_HEADER, userGroups);
		
		obj.setProcApprId(appId);
		obj.setProcUserId(userId);
		obj.setProcUserIpAddress(SmartHttpUtil.getIpAddress(request));

		return ResponseEntity.ok().body(assetDetailServiceFacade.register(obj));
	}

	@Operation(summary = SmartReferenceDataWebUtil.RETRIEVE_ASSETDETAIL_ID_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.RETRIEVE_ASSETDETAIL_ID)
	public ResponseEntity<AssetDetailDto> retrieveByAssetDetailId(
			@PathVariable("id") @Valid Integer id) throws JsonProcessingException {
		return ResponseEntity.ok().body(assetDetailServiceFacade.retrieveById(id));
	}

	@Operation(summary = SmartReferenceDataWebUtil.UPDATE_ASSETDETAIL_OPERATION)
	@GlobalApiReponsesPut
	@PutMapping(SmartReferenceDataWebUtil.UPDATE_ASSETDETAIL)
	public ResponseEntity<AssetDetailDto> update(
			@RequestHeader(value = SmartHttpUtil.APP_ID_HEADER, required = true) String appId,
			@RequestHeader(value = SmartHttpUtil.USER_ID_HEADER, required = true) String userId,
			@RequestHeader(value = SmartHttpUtil.USER_GROUPS_HEADER, required = false) String userGroups,
			@PathVariable("id") @Valid Integer id,
			@Parameter(name = "updateAssetdetail", description = "JSON with object in and out", required = true) @Valid @RequestBody AssetDetailDto obj,
			HttpServletRequest request) 
			throws JsonProcessingException {
			obj.setId(id);

			/** Logging **/
			MDC.put(SmartHttpUtil.APP_ID_HEADER, appId);
			MDC.put(SmartHttpUtil.USER_ID_HEADER, userId);
			MDC.put(SmartHttpUtil.USER_GROUPS_HEADER, userGroups);

			obj.setId(id);
			obj.setProcApprId(appId);
			obj.setProcUserId(userId);
			obj.setProcUserIpAddress(SmartHttpUtil.getIpAddress(request));
			return ResponseEntity.ok().body(assetDetailServiceFacade.update(obj));
	}

	@Operation(summary = SmartReferenceDataWebUtil.DELETE_ASSETDETAIL_OPERATION)
	@GlobalApiReponsesDelete
	@DeleteMapping(SmartReferenceDataWebUtil.DELETE_ASSETDETAIL)
	public ResponseEntity<String> deleteById(
			@RequestHeader(value = SmartHttpUtil.APP_ID_HEADER, required = true) String appId,
			@RequestHeader(value = SmartHttpUtil.USER_ID_HEADER, required = true) String userId,
			@RequestHeader(value = SmartHttpUtil.USER_GROUPS_HEADER, required = false) String userGroups,
			@PathVariable("id") @Valid Integer id) {

		/** Logging **/
		MDC.put(SmartHttpUtil.APP_ID_HEADER, appId);
		MDC.put(SmartHttpUtil.USER_ID_HEADER, userId);
		MDC.put(SmartHttpUtil.USER_GROUPS_HEADER, userGroups);

		assetDetailServiceFacade.delete(id);
		return ResponseEntity.ok().body("DELETED");
	}

}
