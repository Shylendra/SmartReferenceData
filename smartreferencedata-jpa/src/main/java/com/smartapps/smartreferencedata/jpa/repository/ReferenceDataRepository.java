package com.smartapps.smartreferencedata.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartapps.smartreferencedata.jpa.entities.ReferenceData;

@Repository
public interface ReferenceDataRepository extends JpaRepository<ReferenceData, Integer> {
	List<ReferenceData> findByRefDataType(final String refDataType);
}
