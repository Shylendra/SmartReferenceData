package com.smartapps.smartreferencedata.jpa.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.smartapps.smartlib.dto.SearchAssetDetailRequestDto;
import com.smartapps.smartreferencedata.jpa.entities.AssetDetail;

public class AssetDetailSearchUtil {
	
	private static final String DBFIELD_HOST = "host";
	private static final String DBFIELD_BUCKET_NAME = "bucketName";
	private static final String DBFIELD_TYPE = "type";
	private static final String DBFIELD_NAME = "name";
	private static final String DBFIELD_CATEGORY = "category";
	private static final String DBFIELD_JOURNEY_DATE = "journeyDate";
	private static final String DBFIELD_FILE_PATH = "filePath";
	private static final String DBFIELD_URL = "url";
	private static final String DBFIELD_PROC_APP_ID = "procApprId";
	private static final String DBFIELD_PROC_USER_ID = "procUserId";
	
	public static class SearchAssetDetailSpecification {
		public static Specification<AssetDetail> findByCriteria(final SearchAssetDetailRequestDto searchCriteria) {
			return new Specification<AssetDetail>() {

				private static final long serialVersionUID = 1L;

				@Override
				public Predicate toPredicate(Root<AssetDetail> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
					List<Predicate> predicates = new ArrayList<>();
					
					if(StringUtils.isNotEmpty(searchCriteria.getHost())) {
						predicates.add(criteriaBuilder.equal(root.get(DBFIELD_HOST), searchCriteria.getHost()));
					}
					if(StringUtils.isNotEmpty(searchCriteria.getBucketName())) {
						predicates.add(criteriaBuilder.equal(root.get(DBFIELD_BUCKET_NAME), searchCriteria.getBucketName()));
					}
					if(StringUtils.isNotEmpty(searchCriteria.getType())) {
						predicates.add(criteriaBuilder.equal(root.get(DBFIELD_TYPE), searchCriteria.getType()));
					}

					if(StringUtils.isNotEmpty(searchCriteria.getName())) {
						predicates.add(criteriaBuilder.equal(root.get(DBFIELD_NAME), searchCriteria.getName()));
					}
					if(StringUtils.isNotEmpty(searchCriteria.getCategory())) {
						predicates.add(criteriaBuilder.equal(root.get(DBFIELD_CATEGORY), searchCriteria.getCategory()));
					}
					/*
					if(StringUtils.isNotEmpty(searchCriteria.getJourneyDate())) {
						predicates.add(criteriaBuilder.equal(root.get(DBFIELD_JOURNEY_DATE), searchCriteria.getJourneyDate()));
					}*/
					if(StringUtils.isNotEmpty(searchCriteria.getFilePath())) {
						predicates.add(criteriaBuilder.equal(root.get(DBFIELD_FILE_PATH), searchCriteria.getFilePath()));
					}
					if(StringUtils.isNotEmpty(searchCriteria.getUrl())) {
						predicates.add(criteriaBuilder.equal(root.get(DBFIELD_URL), searchCriteria.getUrl()));
					}
					if(StringUtils.isNotEmpty(searchCriteria.getAppId())) {
						predicates.add(criteriaBuilder.equal(root.get(DBFIELD_PROC_APP_ID), searchCriteria.getAppId()));
					}
					if(StringUtils.isNotEmpty(searchCriteria.getUserId())) {
						predicates.add(criteriaBuilder.equal(root.get(DBFIELD_PROC_USER_ID), searchCriteria.getUserId()));
					}

					Optional<Predicate> finalPredicate = predicates.stream().reduce(criteriaBuilder::and);
					return finalPredicate.isPresent() ? finalPredicate.get() : null;
				}
				
			};
		}
	}

}
