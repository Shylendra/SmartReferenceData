package com.smartapps.smartreferencedata.jpa.service;

import java.util.List;
import java.util.Optional;

import com.smartapps.smartlib.dto.AssetDetailDto;
import com.smartapps.smartlib.dto.SearchAssetDetailRequestDto;
import com.smartapps.smartreferencedata.jpa.entities.AssetDetail;

public interface AssetDetailService {
	
	/* Create */
	public Optional<AssetDetail> create(final AssetDetail obj);
	
	/* Read */
	public AssetDetail readById(final Integer id);
	public Optional<List<AssetDetailDto>> search(final SearchAssetDetailRequestDto searchCriteria);
	
	/* Update */
	public Optional<AssetDetail> update(final AssetDetail obj);
	
	/* Delete */
	public void delete(final Integer id);

}
