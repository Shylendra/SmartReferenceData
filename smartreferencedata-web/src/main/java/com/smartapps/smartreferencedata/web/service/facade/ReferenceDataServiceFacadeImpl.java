package com.smartapps.smartreferencedata.web.service.facade;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.dto.ImportRefDataResponseDto;
import com.smartapps.smartlib.dto.ReferenceDataDto;
import com.smartapps.smartlib.dto.RegisterRefDataResponseDto;
import com.smartapps.smartlib.dto.SearchReferenceDataRequestDto;
import com.smartapps.smartlib.dto.SearchReferenceDataResponseDto;
import com.smartapps.smartlib.util.SharedMessages;
import com.smartapps.smartlib.util.SmartLibraryUtil;
import com.smartapps.smartreferencedata.jpa.entities.ReferenceData;
import com.smartapps.smartreferencedata.web.util.SmartReferenceDataWebUtil;

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

		ReferenceData reqEntityObj = smartRefDataAssembler.mapToEntity(obj);
		ReferenceData resEntityObj = referenceDataService.create(reqEntityObj).get();
		if(resEntityObj != null) {
			ReferenceDataDto resObj = smartRefDataAssembler.mapToDto(resEntityObj);
			log.info(messageService.getMessage(
					SharedMessages.LOG003_RESPONSE, 
					new Object[]{
							this.getClass().getSimpleName(), 
							new Object(){}.getClass().getEnclosingMethod().getName(),
							resObj}));
			return resObj;
		}
		return null;
	}

	@Override
	public RegisterRefDataResponseDto registerByType(String type, List<ReferenceDataDto> objList)
			throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG002_REQUEST, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						type}));
		List<String> refDataCodes = new ArrayList<>();
		int createCount = 0;
		int updateCount = 0;
		for(ReferenceDataDto refData : objList) {
			refData.setRefDataType(type);
			ReferenceData entityObj = smartRefDataAssembler.mapToEntity(refData);
			ReferenceData entityObjSaved = referenceDataService.readByRefDataCodeAndRefDataType(entityObj.getRefDataCode(), entityObj.getRefDataType());
			ReferenceDataDto response = null;
			if(entityObjSaved == null) {
				Optional<ReferenceData> entityObjCreated = referenceDataService.create(entityObj);
				if(entityObjCreated.isPresent()) {
					response = smartRefDataAssembler.mapToDto(entityObjCreated.get());
					createCount++;
				}
			} else {
				String desc = StringUtils.isNotEmpty(entityObj.getRefDataDescription()) ? entityObj.getRefDataDescription().trim() : "";
				String descDetails = StringUtils.isNotEmpty(entityObj.getRefDataDescriptionDetail()) ? entityObj.getRefDataDescriptionDetail().trim() : "";
				String savedDesc = StringUtils.isNotEmpty(entityObjSaved.getRefDataDescription()) ? entityObjSaved.getRefDataDescription().trim() : "";
				String savedDescDetails = StringUtils.isNotEmpty(entityObjSaved.getRefDataDescriptionDetail()) ? entityObjSaved.getRefDataDescriptionDetail().trim() : "";
				if(!desc.equals(savedDesc) || !descDetails.equals(savedDescDetails)) {
					Optional<ReferenceData> entityObjUpdated = referenceDataService.update(entityObj);
					if(entityObjUpdated.isPresent()) {
						response = smartRefDataAssembler.mapToDto(entityObjUpdated.get());
						updateCount++;
					}
				}
			}
			if(response != null) {
				refDataCodes.add(response.getRefDataCode());
			}
		}
		
		RegisterRefDataResponseDto response = new RegisterRefDataResponseDto();
		response.setRefDataType(type);
		response.setRefDataCodes(refDataCodes);
		response.setCreateCount(createCount);
		response.setUpdateCount(updateCount);
		
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
	public Page<ReferenceDataDto> retrieveAll(Pageable pageable) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		Page<ReferenceData> entityPageObjs = referenceDataService.readAll(pageable);
		Page<ReferenceDataDto> dtoPageObj = entityPageObjs.map(new Function<ReferenceData, ReferenceDataDto>() {
		    @Override
		    public ReferenceDataDto apply(ReferenceData entityObj) {
		        return SmartLibraryUtil.map(entityObj, ReferenceDataDto.class);
		    }
		});
		return dtoPageObj;
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

		ReferenceData entityObj = referenceDataService.readByRefDataCodeAndRefDataType(obj.getRefDataCode(), obj.getRefDataType());
		if(entityObj != null) {
			smartRefDataAssembler.mapToEntityForUpdate(entityObj, obj);
			ReferenceData resEntityObj = referenceDataService.update(entityObj).get();
			ReferenceDataDto resObj = smartRefDataAssembler.mapToDto(resEntityObj);
			log.info(messageService.getMessage(
					SharedMessages.LOG003_RESPONSE, 
					new Object[]{
							this.getClass().getSimpleName(), 
							new Object(){}.getClass().getEnclosingMethod().getName(),
							resObj}));
			return resObj;

		}
		return null;
	}

	@Override
	public void delete(String code, String type) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		referenceDataService.delete(code, type);
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
		
		Optional<List<SearchReferenceDataResponseDto>> objList = referenceDataService.search(obj);
		
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						SmartLibraryUtil.mapToString(objList.get(), true)}));
		return objList.get();
	}

	@Override
	public String exportData(String type) throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		List<ReferenceDataDto> dtoObjList = new ArrayList<>();
		List<ReferenceData> entityObjList = null;
		String responseStr = "";
		if(StringUtils.isNotEmpty(type)) {
			entityObjList = referenceDataService.readByRefDataType(type);
		} else {
			entityObjList = referenceDataService.readAll();
		}
		if(entityObjList != null) {
			for(ReferenceData entityObj: entityObjList) {
				dtoObjList.add(SmartLibraryUtil.map(entityObj, ReferenceDataDto.class));
			}
			responseStr = SmartLibraryUtil.mapToString(dtoObjList, true);
		}

		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						responseStr}));
		
		return responseStr;
	}

	@Override
	public ImportRefDataResponseDto importData(MultipartFile file) throws IOException {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		int createCount = 0;
		int updateCount = 0;
		String statusMsg = "";
		String fileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
		List<ReferenceDataDto> dtoDataList = new ArrayList<>();
		if(file.getBytes().length != 0 && StringUtils.isNotEmpty(fileContent)) {
			String fileDetails = file.getOriginalFilename() + "[" + file.getSize() + "] ";
			dtoDataList = SmartLibraryUtil.mapToObjectList(fileContent, ReferenceDataDto.class);
			Map<String, List<ReferenceDataDto>> codesMap = SmartReferenceDataWebUtil.extractCodes(dtoDataList);
			for (Map.Entry<String, List<ReferenceDataDto>> entry : codesMap.entrySet()) {
				RegisterRefDataResponseDto registerRefDataResponseDto = registerByType(entry.getKey(), entry.getValue());
				createCount += registerRefDataResponseDto.getCreateCount();
				updateCount += registerRefDataResponseDto.getUpdateCount();
			}
			statusMsg = (createCount == 0 && updateCount == 0) ? "Nothing to import" : "Successfully imported [" + fileDetails + "] ";
		}
		
		ImportRefDataResponseDto response = ImportRefDataResponseDto.builder()
				.recordsToImport(dtoDataList != null ? dtoDataList.size() : 0)
				.created(createCount)
				.updated(updateCount)
				.status(statusMsg).build();
		
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						response.toString()}));
		return response;
	}

	@Override
	public void delete(List<Integer> ids) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		referenceDataService.delete(ids);
	}

}
