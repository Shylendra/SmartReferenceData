package com.smartapps.smartreferencedata.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartapps.smartlib.dto.SearchReferenceDataRequestDto;
import com.smartapps.smartlib.dto.SearchReferenceDataResponseDto;
import com.smartapps.smartreferencedata.jpa.entities.ReferenceData;

public interface ReferenceDataService {
	
	/* Create */
	public Optional<ReferenceData> create(final ReferenceData obj);
	
	/* Read */
	public List<ReferenceData> readAll();
	public Page<ReferenceData> readAll(Pageable pageable);
	public ReferenceData readById(final Integer id);
	public List<ReferenceData> readByRefDataType(final String refDataType);
	public ReferenceData readByRefDataCodeAndRefDataType(String refDataCode, String refDataType);
	public Optional<List<SearchReferenceDataResponseDto>> search(final SearchReferenceDataRequestDto searchCriteria);
	
	/* Update */
	public Optional<ReferenceData> update(final ReferenceData obj);
	
	/* Delete */
	public void delete(final String code, final String type);
	public void delete(List<Integer> ids);

}
