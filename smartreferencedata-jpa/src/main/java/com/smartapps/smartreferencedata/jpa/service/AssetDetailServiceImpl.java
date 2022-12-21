package com.smartapps.smartreferencedata.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartapps.smartlib.dto.AssetDetailDto;
import com.smartapps.smartlib.dto.SearchAssetDetailRequestDto;
import com.smartapps.smartlib.exception.ResourceNotFoundException;
import com.smartapps.smartlib.service.MessageService;
import com.smartapps.smartlib.util.SharedMessages;
import com.smartapps.smartlib.util.SmartLibraryUtil;
import com.smartapps.smartreferencedata.jpa.entities.AssetDetail;
import com.smartapps.smartreferencedata.jpa.repository.AssetDetailRepository;
import com.smartapps.smartreferencedata.jpa.util.AssetDetailSearchUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AssetDetailServiceImpl implements AssetDetailService {
	
	@Autowired
	private AssetDetailRepository repository;
	
	@Autowired
	private MessageService messageService;
	
	private static final String ENTITY_NAME = "AssetDetail";

	@Override
	public Optional<AssetDetail> create(AssetDetail obj) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		return Optional.of(repository.save(obj));
	}

	@Override
	public AssetDetail readById(Integer id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		Optional<AssetDetail> entityObj = repository.findById(id);
		if(!entityObj.isPresent()) {
			throw new ResourceNotFoundException(messageService.getMessage(SharedMessages.ERR001_RESOURCE_NOTFOUND, 
					new Object[]{ENTITY_NAME,id}));
		}
		return entityObj.get();
	}

	@Override
	public Optional<List<AssetDetailDto>> search(SearchAssetDetailRequestDto searchCriteria) {
		List<AssetDetailDto> searchResponses = new ArrayList<>();
		Optional<List<AssetDetail>> entityObjList = Optional.ofNullable(repository.findAll(AssetDetailSearchUtil.SearchAssetDetailSpecification.findByCriteria(searchCriteria)));
		if(entityObjList.isPresent()) {
			for(AssetDetail assetDetail : entityObjList.get()) {
				searchResponses.add(SmartLibraryUtil.map(assetDetail, AssetDetailDto.class));
			}
		}
		return Optional.of(searchResponses);
	}

	@Override
	public Optional<AssetDetail> update(AssetDetail obj) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		
		Optional<AssetDetail> resp = repository.findById(obj.getId());
		if(resp.isPresent()) {
			if(StringUtils.isNotEmpty(obj.getHost())) {
				resp.get().setHost(obj.getHost());;
			}
			if(StringUtils.isNotEmpty(obj.getBucketName())) {
				resp.get().setBucketName(obj.getBucketName());
			}
			if(StringUtils.isNotEmpty(obj.getType())) {
				resp.get().setType(obj.getType());
			}
			if(StringUtils.isNotEmpty(obj.getName())) {
				resp.get().setName(obj.getName());
			}
			if(StringUtils.isNotEmpty(obj.getCategory())) {
				resp.get().setCategory(obj.getCategory());
			}
			if(obj.getJourneyDate() != null) {
				resp.get().setJourneyDate(obj.getJourneyDate());
			}
			if(StringUtils.isNotEmpty(obj.getFilePath())) {
				resp.get().setFilePath(obj.getFilePath());
			}
			if(StringUtils.isNotEmpty(obj.getUrl())) {
				resp.get().setUrl(obj.getUrl());
			}
			return Optional.of(repository.save(resp.get()));
		}
		
		return Optional.ofNullable(obj);
	}

	@Override
	public void delete(Integer id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		Optional<AssetDetail> resp = repository.findById(id);
		if(resp.isPresent()) {
			repository.deleteById(resp.get().getId());
		}
	}

}
