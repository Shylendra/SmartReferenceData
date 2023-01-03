package com.smartapps.smartreferencedata.web.service.facade;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.util.SharedMessages;
import com.smartapps.smartlib.util.SmartLibraryUtil;
import com.smartapps.smartreferencedata.jpa.dto.CodeValueDto;
import com.smartapps.smartreferencedata.jpa.entities.ReferenceData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AddressTypeServiceFacadeImpl extends CommonServiceFacade implements AddressTypeServiceFacade {
	
	private static final String REF_DATA_TYPE = "ADDRESS-TYPE";

	@Override
	public List<CodeValueDto> retrieveAddressTypes() throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
	
		List<CodeValueDto> objList = new ArrayList<>();
		List<ReferenceData> refDataList = referenceDataService.readByRefDataType(REF_DATA_TYPE);
		for(ReferenceData refData: refDataList) {
			objList.add(CodeValueDto.builder()
					.code(refData.getRefDataCode())
					.value(refData.getRefDataDescription()).build());
		}
		
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						SmartLibraryUtil.mapToString(objList, true)}));
		return objList;
	}

}
