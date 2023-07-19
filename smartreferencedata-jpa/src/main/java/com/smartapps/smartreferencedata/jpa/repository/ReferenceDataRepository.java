package com.smartapps.smartreferencedata.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.smartapps.smartreferencedata.jpa.entities.ReferenceData;

@Repository
public interface ReferenceDataRepository extends JpaRepository<ReferenceData, Integer>, JpaSpecificationExecutor<ReferenceData> {
	List<ReferenceData> findAllByOrderByProcTsDesc();
	Page<ReferenceData> findAllByOrderByProcTsDesc(Pageable pageable);
	Optional<List<ReferenceData>> findByRefDataTypeOrderByProcTsDesc(final String refDataType);
	Optional<ReferenceData> findByRefDataCodeAndRefDataType(String refDataCode, String refDataType);
	boolean existsByRefDataCodeAndRefDataType(String refDataCode, String refDataType);
	void deleteByIdIn(List<Integer> ids);
}
