package com.smartapps.smartreferencedata.web.service.facade;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.util.SharedMessages;
import com.smartapps.smartlib.util.SmartLibraryUtil;
import com.smartapps.smartreferencedata.jpa.dto.ReferenceDataDto;
import com.smartapps.smartreferencedata.jpa.dto.SearchReferenceDataRequestDto;
import com.smartapps.smartreferencedata.jpa.dto.SearchReferenceDataResponseDto;
import com.smartapps.smartreferencedata.jpa.entities.ReferenceData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ReferenceDataServiceFacadeImpl extends CommonServiceFacade implements ReferenceDataServiceFacade {

	@Override
	public ReferenceDataDto register(ReferenceDataDto obj) throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG002_REQUEST, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						obj}));

		ReferenceData entityObj = SmartLibraryUtil.map(obj, ReferenceData.class);
		if(StringUtils.isNotEmpty(obj.getProcTs())) {
			entityObj.setProcTs(obj.getSqlProcTs());
		}
		ReferenceDataDto response = SmartLibraryUtil.map(referenceDataService.create(entityObj).get(), ReferenceDataDto.class);
		
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						response}));

		return response;
	}

	@Override
	public List<ReferenceDataDto> retrieveAll() throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		List<ReferenceDataDto> objList = new ArrayList<>();
		List<ReferenceData> entityObjList = referenceDataService.readAll();
		for(ReferenceData entityObj: entityObjList) {
			objList.add(SmartLibraryUtil.map(entityObj, ReferenceDataDto.class));
		}

		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						SmartLibraryUtil.mapToString(objList, true)}));
		
		return objList;
	}

	@Override
	public ReferenceDataDto retrieveById(Integer id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));

		ReferenceDataDto response = SmartLibraryUtil.map(referenceDataService.readById(id), ReferenceDataDto.class);

		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						response}));
		return response;
	}

	@Override
	public List<ReferenceDataDto> retrieveByRefDataType(String refDataType) throws JsonProcessingException, SecurityException {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		List<ReferenceDataDto> objList = new ArrayList<>();
		List<ReferenceData> entityObjList = referenceDataService.readByRefDataType(refDataType);
		for(ReferenceData entityObj: entityObjList) {
			objList.add(SmartLibraryUtil.map(entityObj, ReferenceDataDto.class));
		}
		
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						SmartLibraryUtil.mapToString(objList, true)}));
		return objList;
	}

	@Override
	public ReferenceDataDto update(ReferenceDataDto obj) throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG002_REQUEST, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						obj}));

		ReferenceData entityObjToUpdate = SmartLibraryUtil.map(obj, ReferenceData.class);
		if(StringUtils.isNotEmpty(obj.getRefDataCode())) {
			entityObjToUpdate.setRefDataCode(obj.getRefDataCode());
		}
		if(StringUtils.isNotEmpty(obj.getRefDataType())) {
			entityObjToUpdate.setRefDataType(obj.getRefDataType());
		}
		if(StringUtils.isNotEmpty(obj.getRefDataDescription())) {
			entityObjToUpdate.setRefDataDescription(obj.getRefDataDescription());
		}
		if(StringUtils.isNotEmpty(obj.getRefDataDescriptionDetail())) {
			entityObjToUpdate.setRefDataDescriptionDetail(obj.getRefDataDescriptionDetail());
		}
		ReferenceDataDto response = SmartLibraryUtil.map(referenceDataService.update(entityObjToUpdate).get(), ReferenceDataDto.class);
		
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						response}));
		return response;
	}

	@Override
	public void deleteById(Integer id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		referenceDataService.deleteById(id);
	}

	@Override
	public List<SearchReferenceDataResponseDto> search(SearchReferenceDataRequestDto obj)
			throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG002_REQUEST, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						obj}));
		
		List<SearchReferenceDataResponseDto> objList = new ArrayList<>();
		
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						SmartLibraryUtil.mapToString(objList, true)}));
		return objList;
	}

}
