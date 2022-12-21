package com.smartapps.smartreferencedata.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartapps.smartlib.dto.SearchReferenceDataRequestDto;
import com.smartapps.smartlib.dto.SearchReferenceDataResponseDto;
import com.smartapps.smartlib.exception.ResourceNotFoundException;
import com.smartapps.smartlib.service.MessageService;
import com.smartapps.smartlib.util.SharedMessages;
import com.smartapps.smartreferencedata.jpa.entities.ReferenceData;
import com.smartapps.smartreferencedata.jpa.repository.ReferenceDataRepository;
import com.smartapps.smartreferencedata.jpa.util.RefDataSearchUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReferenceDataServiceImpl implements ReferenceDataService {

	@Autowired
	private ReferenceDataRepository repository;
	
	@Autowired
	private MessageService messageService;
	
	private static final String ENTITY_NAME = "ReferenceData";

	@Override
	public Optional<ReferenceData> create(ReferenceData obj) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		Optional<ReferenceData> resp = repository.findByRefDataCodeAndRefDataType(obj.getRefDataCode(), obj.getRefDataType());
		if(!resp.isPresent()) {
			return Optional.of(repository.save(obj));
		}
//		else {
//			String desc = StringUtils.isNotEmpty(obj.getRefDataDescription()) ? obj.getRefDataDescription().trim() : "";
//			String descDetails = StringUtils.isNotEmpty(obj.getRefDataDescriptionDetail()) ? obj.getRefDataDescriptionDetail().trim() : "";
//			String savedDesc = StringUtils.isNotEmpty(resp.get().getRefDataDescription()) ? resp.get().getRefDataDescription().trim() : "";
//			String savedDescDetails = StringUtils.isNotEmpty(resp.get().getRefDataDescriptionDetail()) ? resp.get().getRefDataDescriptionDetail().trim() : "";
//			if(!desc.equals(savedDesc) || !descDetails.equals(savedDescDetails)) {
//				return update(obj);
//			}
//		}
		return Optional.ofNullable(obj);
	}

	@Override
	public List<ReferenceData> readAll() {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		return repository.findAll();
	}

	@Override
	public ReferenceData readById(Integer id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		Optional<ReferenceData> entityObj = repository.findById(id);
		if(!entityObj.isPresent()) {
			throw new ResourceNotFoundException(messageService.getMessage(SharedMessages.ERR001_RESOURCE_NOTFOUND, 
					new Object[]{ENTITY_NAME,id}));
		}
		
		return entityObj.get();
	}

	@Override
	public List<ReferenceData> readByRefDataType(String refDataType) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		Optional<List<ReferenceData>> resp = repository.findByRefDataType(refDataType);
		if(resp.isPresent()) {
			return resp.get();
		}
		return new ArrayList<>();
	}

	@Override
	public ReferenceData readByRefDataCodeAndRefDataType(String refDataCode, String refDataType) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		Optional<ReferenceData> resp = repository.findByRefDataCodeAndRefDataType(refDataCode, refDataType);
		if(resp.isPresent()) {
			return resp.get();
		}
		return null;
	}

	@Override
	public Optional<List<SearchReferenceDataResponseDto>> search(SearchReferenceDataRequestDto searchCriteria) {
		List<SearchReferenceDataResponseDto> searchResponses = new ArrayList<>();
		Optional<List<ReferenceData>> entityObjList = Optional.ofNullable(repository.findAll(RefDataSearchUtil.SearchRefDataSpecification.findByCriteria(searchCriteria)));
		if(entityObjList.isPresent()) {
			for(ReferenceData referenceData : entityObjList.get()) {
				searchResponses.add(SearchReferenceDataResponseDto.builder()
						.code(referenceData.getRefDataCode())
						.type(referenceData.getRefDataType())
						.description(referenceData.getRefDataDescription())
						.descriptionDetail(referenceData.getRefDataDescriptionDetail()).build());
			}
		}
		
		return Optional.of(searchResponses);
	}

	@Override
	public Optional<ReferenceData> update(ReferenceData obj) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		Optional<ReferenceData> resp = repository.findByRefDataCodeAndRefDataType(obj.getRefDataCode(), obj.getRefDataType());
		if(resp.isPresent()) {
			if(StringUtils.isNotEmpty(obj.getRefDataDescription())) {
				resp.get().setRefDataDescription(obj.getRefDataDescription());
			}
			if(StringUtils.isNotEmpty(obj.getRefDataDescriptionDetail())) {
				resp.get().setRefDataDescriptionDetail(obj.getRefDataDescriptionDetail());
			}
			return Optional.of(repository.save(resp.get()));
		}
		
		return Optional.ofNullable(obj);
	}

	@Override
	public void delete(String code, String type) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		Optional<ReferenceData> resp = repository.findByRefDataCodeAndRefDataType(code, type);
		if(resp.isPresent()) {
			repository.deleteById(resp.get().getId());
		}
	}
	
}
