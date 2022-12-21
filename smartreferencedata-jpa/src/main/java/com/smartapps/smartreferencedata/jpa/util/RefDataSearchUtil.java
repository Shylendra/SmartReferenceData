package com.smartapps.smartreferencedata.jpa.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.smartapps.smartlib.dto.SearchReferenceDataRequestDto;
import com.smartapps.smartreferencedata.jpa.entities.ReferenceData;

import io.micrometer.core.instrument.util.StringUtils;

public class RefDataSearchUtil {
	
	private static final String DBFIELD_CODE = "refDataCode";
	private static final String DBFIELD_TYPE = "refDataType";
	private static final String DBFIELD_DESC = "refDataDescription";
	
	public static class SearchRefDataSpecification {
		public static Specification<ReferenceData> findByCriteria(final SearchReferenceDataRequestDto searchCriteria) {
			return new Specification<ReferenceData>() {

				private static final long serialVersionUID = 1L;

				@Override
				public Predicate toPredicate(Root<ReferenceData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
					List<Predicate> predicates = new ArrayList<>();
					
					if(StringUtils.isNotEmpty(searchCriteria.getCode())) {
						predicates.add(criteriaBuilder.equal(root.get(DBFIELD_CODE), searchCriteria.getCode()));
					}
					if(StringUtils.isNotEmpty(searchCriteria.getType())) {
						predicates.add(criteriaBuilder.equal(root.get(DBFIELD_TYPE), searchCriteria.getType()));
					}
					if(StringUtils.isNotEmpty(searchCriteria.getDescription())) {
						predicates.add(criteriaBuilder.equal(root.get(DBFIELD_DESC), searchCriteria.getDescription()));
					}
					
					Optional<Predicate> finalPredicate = predicates.stream().reduce(criteriaBuilder::and);
					return finalPredicate.isPresent() ? finalPredicate.get() : null;
				}
				
			};
		}
	}

}
