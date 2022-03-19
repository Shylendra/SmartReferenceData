package com.smartapps.smartreferencedata.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smartapps.smartlib.converter.TrimConverter;
import com.smartapps.smartlib.entities.CommonEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SMART_REFERENCE_DATA")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class ReferenceData extends CommonEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "REF_DATA_CODE")
	@Convert(converter = TrimConverter.class)
	private String refDataCode;
	
	@Column(name = "REF_DATA_TYPE")
	@Convert(converter = TrimConverter.class)
	private String refDataType;
	
	@Column(name = "REF_DATA_DESC")
	@Convert(converter = TrimConverter.class)
	private String refDataDescription;
	
	@Column(name = "REF_DATA_DESCDETAIL")
	@Convert(converter = TrimConverter.class)
	private String refDataDescriptionDetail;

}
