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
public class ExpenseServiceFacadeImpl extends CommonServiceFacade implements ExpenseServiceFacade {
	
	private static final String EXPENSE_TYPE = "CTY-EXPENSE";
	
	@Override
	public List<CodeValueDto> retrieveCategories() throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
	
		List<CodeValueDto> objList = new ArrayList<>();
		List<ReferenceData> refDataList = referenceDataService.readByRefDataType(EXPENSE_TYPE);
		for(ReferenceData refData: refDataList) {
			objList.add(CodeValueDto.builder()
					.code(refData.getRefDataCode())
					.value(refData.getRefDataDescription())
					.subCodeValues(retrieveSubCategories(refData.getRefDataCode())).build());
		}
		
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						SmartLibraryUtil.mapToString(objList, true)}));
		return objList;
	}

	private List<CodeValueDto> retrieveSubCategories(String type) {
		List<CodeValueDto> objList = new ArrayList<>();
		List<ReferenceData> refDataList = referenceDataService.readByRefDataType(type);
		for(ReferenceData refData: refDataList) {
			objList.add(CodeValueDto.builder()
					.code(refData.getRefDataCode())
					.value(refData.getRefDataDescription())
					.subCodeValues(new ArrayList<>()).build());
		}
		return objList;
	}

}
