package com.smartapps.smartreferencedata.web.service.facade;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartreferencedata.jpa.dto.ReferenceDataDto;
import com.smartapps.smartreferencedata.jpa.dto.SearchReferenceDataRequestDto;
import com.smartapps.smartreferencedata.jpa.dto.SearchReferenceDataResponseDto;

public interface ReferenceDataServiceFacade {

	public ReferenceDataDto register(final ReferenceDataDto obj) throws JsonProcessingException;
	public List<ReferenceDataDto> retrieveAll() throws JsonProcessingException;
	public ReferenceDataDto retrieveById(final Integer id);
	public List<ReferenceDataDto> retrieveByRefDataType(final String refDataType) throws JsonProcessingException, SecurityException;
	public ReferenceDataDto update(final ReferenceDataDto obj) throws JsonProcessingException;
	public void deleteById(final Integer id);
	public List<SearchReferenceDataResponseDto> search(final SearchReferenceDataRequestDto obj) throws JsonProcessingException;

}
