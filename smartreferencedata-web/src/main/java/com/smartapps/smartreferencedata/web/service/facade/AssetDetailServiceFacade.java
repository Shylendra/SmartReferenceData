package com.smartapps.smartreferencedata.web.service.facade;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.dto.AssetDetailDto;

public interface AssetDetailServiceFacade {

	public List<AssetDetailDto> register(final AssetDetailDto obj) throws JsonProcessingException;
	public AssetDetailDto retrieveById(final Integer id);
	public AssetDetailDto update(final AssetDetailDto obj) throws JsonProcessingException;
	public void delete(final Integer id);
	
}
