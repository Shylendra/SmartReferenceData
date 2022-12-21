package com.smartapps.smartreferencedata.web.service.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.dto.AssetDetailDto;
import com.smartapps.smartlib.dto.SearchAssetDetailRequestDto;
import com.smartapps.smartlib.util.SharedMessages;
import com.smartapps.smartlib.util.SmartLibraryUtil;
import com.smartapps.smartreferencedata.jpa.entities.AssetDetail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AssetDetailServiceFacadeImpl extends CommonServiceFacade implements AssetDetailServiceFacade {

	@Override
	public List<AssetDetailDto> register(AssetDetailDto obj) throws JsonProcessingException {
		log.info(messageService.getMessage(
				SharedMessages.LOG002_REQUEST, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						obj}));

		List<AssetDetailDto> response = new ArrayList<>();
		AssetDetail entityObj = SmartLibraryUtil.map(obj, AssetDetail.class);
		Optional<AssetDetail> assetDetailSObj = assetDetailService.create(entityObj);
		if(assetDetailSObj.isPresent()) {
			Optional<List<AssetDetailDto>> searchResult = assetDetailService.search(SearchAssetDetailRequestDto.builder()
					.host(obj.getHost())
					.bucketName(obj.getBucketName())
					.type(obj.getType())
					.name(obj.getName())
					.category(obj.getCategory())
					.journeyDate(obj.getJourneyDate())
					.appId(obj.getProcApprId())
					.userId(obj.getProcUserId()).build());
			if(searchResult.isPresent()) {
				response = searchResult.get();
			}
		}
		
		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						response}));

		return response;
	}

	@Override
	public AssetDetailDto retrieveById(Integer id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));

		AssetDetailDto response = SmartLibraryUtil.map(assetDetailService.readById(id), AssetDetailDto.class);

		log.info(messageService.getMessage(
				SharedMessages.LOG003_RESPONSE, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName(),
						response}));
		return response;
	}

	@Override
	public AssetDetailDto update(AssetDetailDto obj) throws JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		log.info(messageService.getMessage(
				SharedMessages.LOG001_PREFIX, 
				new Object[]{
						this.getClass().getSimpleName(), 
						new Object(){}.getClass().getEnclosingMethod().getName()}));
		assetDetailService.delete(id);
	}

}
