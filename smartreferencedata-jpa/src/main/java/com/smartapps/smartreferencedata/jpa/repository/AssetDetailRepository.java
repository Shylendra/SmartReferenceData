package com.smartapps.smartreferencedata.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.smartapps.smartreferencedata.jpa.entities.AssetDetail;

@Repository
public interface AssetDetailRepository extends JpaRepository<AssetDetail, Integer>, JpaSpecificationExecutor<AssetDetail> {
	Optional<List<AssetDetail>> findByType(final String assetType);
}
