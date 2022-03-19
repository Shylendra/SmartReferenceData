package com.smartapps.smartreferencedata.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartapps.smartlib.exception.ResourceNotFoundException;
import com.smartapps.smartlib.service.MessageService;
import com.smartapps.smartlib.util.SharedMessages;
import com.smartapps.smartreferencedata.jpa.dto.SearchReferenceDataRequestDto;
import com.smartapps.smartreferencedata.jpa.dto.SearchReferenceDataResponseDto;
import com.smartapps.smartreferencedata.jpa.entities.ReferenceData;
import com.smartapps.smartreferencedata.jpa.repository.ReferenceDataRepository;

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
		return Optional.of(repository.save(obj));
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
		return repository.findByRefDataType(refDataType);
	}

	@Override
	public List<SearchReferenceDataResponseDto> search(SearchReferenceDataRequestDto searchRequest) {
		return new ArrayList<>();
	}

	@Override
	public Optional<ReferenceData> update(ReferenceData obj) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		ReferenceData entityObj = readById(obj.getId());
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
		
		return Optional.of(repository.save(entityObj));
	}

	@Override
	public void deleteById(Integer id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));

		repository.deleteById(readById(id).getId());
	}

}
