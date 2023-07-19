package com.smartapps.smartreferencedata.web.assemblers;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Component;

import com.smartapps.smartlib.dto.ReferenceDataDto;
import com.smartapps.smartreferencedata.jpa.entities.ReferenceData;

@Component
public class SmartRefDataAssembler {

	public ReferenceData mapToEntity(ReferenceDataDto obj) {
		ReferenceData entityObj = new ReferenceData();
		
		if(StringUtils.isNotEmpty(obj.getRefDataCode())) {
			entityObj.setRefDataCode(obj.getRefDataCode());
		}
		if(StringUtils.isNotEmpty(obj.getRefDataType())) {
			entityObj.setRefDataType(obj.getRefDataType());
		}
		if(StringUtils.isNotEmpty(obj.getRefDataDescription())) {
			entityObj.setRefDataDescription(obj.getRefDataDescription());
		}
		if(StringUtils.isNotEmpty(obj.getRefDataDescriptionDetail())) {
			entityObj.setRefDataDescriptionDetail(obj.getRefDataDescriptionDetail());
		}

		/* Base Entity */
		if(StringUtils.isNotEmpty(obj.getProcTs())) {
			entityObj.setProcTs(obj.getSqlProcTs());
		}
		if(StringUtils.isNotEmpty(obj.getProcAppId())) {
			entityObj.setProcAppId(obj.getProcAppId());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserId())) {
			entityObj.setProcUserId(obj.getProcUserId());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserIpAddress())) {
			entityObj.setProcUserIpAddress(obj.getProcUserIpAddress());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserLatitude())) {
			entityObj.setProcUserLatitude(obj.getProcUserLatitude());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserLongitude())) {
			entityObj.setProcUserLongitude(obj.getProcUserLongitude());
		}

		return entityObj;
	}
	
	public ReferenceDataDto mapToDto(ReferenceData entityObj) {
		ReferenceDataDto obj = new ReferenceDataDto();
		
		obj.setId(entityObj.getId());
		if(StringUtils.isNotEmpty(entityObj.getRefDataCode())) {
			obj.setRefDataCode(entityObj.getRefDataCode());
		}
		if(StringUtils.isNotEmpty(entityObj.getRefDataType())) {
			obj.setRefDataType(entityObj.getRefDataType());
		}
		if(StringUtils.isNotEmpty(entityObj.getRefDataDescription())) {
			obj.setRefDataDescription(entityObj.getRefDataDescription());
		}
		if(StringUtils.isNotEmpty(entityObj.getRefDataDescriptionDetail())) {
			obj.setRefDataDescriptionDetail(entityObj.getRefDataDescriptionDetail());
		}

		/* Base Entity */
		if(entityObj.getProcTs() != null) {
			obj.setProcTs(entityObj.getProcTs().toString());
		}
		if(StringUtils.isNotEmpty(entityObj.getProcAppId())) {
			obj.setProcAppId(entityObj.getProcAppId());
		}
		if(StringUtils.isNotEmpty(entityObj.getProcUserId())) {
			obj.setProcUserId(entityObj.getProcUserId());
		}
		if(StringUtils.isNotEmpty(entityObj.getProcUserIpAddress())) {
			obj.setProcUserIpAddress(entityObj.getProcUserIpAddress());
		}
		if(StringUtils.isNotEmpty(entityObj.getProcUserLatitude())) {
			obj.setProcUserLatitude(entityObj.getProcUserLatitude());
		}
		if(StringUtils.isNotEmpty(entityObj.getProcUserLongitude())) {
			obj.setProcUserLongitude(entityObj.getProcUserLongitude());
		}
		
		return obj;
	}
	
	public void mapToEntityForUpdate(ReferenceData entityObj, ReferenceDataDto obj) {
		
		//entityObj.setId(obj.getId());
		if(StringUtils.isNotEmpty(obj.getRefDataDescription())) {
			entityObj.setRefDataDescription(obj.getRefDataDescription());
		}
		if(StringUtils.isNotEmpty(obj.getRefDataDescriptionDetail())) {
			entityObj.setRefDataDescriptionDetail(obj.getRefDataDescriptionDetail());
		}

		/* Base Entity */
		if(StringUtils.isNotEmpty(obj.getProcTs())) {
			entityObj.setProcTs(obj.getSqlProcTs());
		}
		if(StringUtils.isNotEmpty(obj.getProcAppId())) {
			entityObj.setProcAppId(obj.getProcAppId());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserId())) {
			entityObj.setProcUserId(obj.getProcUserId());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserIpAddress())) {
			entityObj.setProcUserIpAddress(obj.getProcUserIpAddress());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserLatitude())) {
			entityObj.setProcUserLatitude(obj.getProcUserLatitude());
		}
		if(StringUtils.isNotEmpty(obj.getProcUserLongitude())) {
			entityObj.setProcUserLongitude(obj.getProcUserLongitude());
		}
	}
}
