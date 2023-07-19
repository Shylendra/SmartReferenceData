package com.smartapps.smartreferencedata.web.service.facade;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.dto.ImportRefDataResponseDto;
import com.smartapps.smartlib.dto.ReferenceDataDto;
import com.smartapps.smartlib.dto.RegisterRefDataResponseDto;
import com.smartapps.smartlib.dto.SearchReferenceDataRequestDto;
import com.smartapps.smartlib.dto.SearchReferenceDataResponseDto;
import com.smartapps.smartreferencedata.jpa.entities.ReferenceData;

public interface ReferenceDataServiceFacade {

	public ReferenceDataDto register(final ReferenceDataDto obj) throws JsonProcessingException;
	public RegisterRefDataResponseDto registerByType(final String type, final List<ReferenceDataDto> objList) throws JsonProcessingException;
	public List<ReferenceDataDto> retrieveAll() throws JsonProcessingException;
	public Page<ReferenceDataDto> retrieveAll(Pageable pageable);
	public String exportData(final String type) throws JsonProcessingException;
	public ImportRefDataResponseDto importData(final MultipartFile file) throws IOException;
	public ReferenceDataDto retrieveById(final Integer id);
	public List<ReferenceDataDto> retrieveByRefDataType(final String refDataType) throws JsonProcessingException, SecurityException;
	public ReferenceDataDto update(final ReferenceDataDto obj) throws JsonProcessingException;
	public void delete(final String code, final String type);
	public void delete(List<Integer> ids);
	public List<SearchReferenceDataResponseDto> search(final SearchReferenceDataRequestDto obj) throws JsonProcessingException;

}
