package com.smartapps.smartreferencedata.jpa.entities;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "SMART_ASSET_DETAIL")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class AssetDetail extends CommonEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "HOST")
	@Convert(converter = TrimConverter.class)
	private String host;
	
	@Column(name = "BUCKET_NAME")
	@Convert(converter = TrimConverter.class)
	private String bucketName;
	
	@Column(name = "TYPE")
	@Convert(converter = TrimConverter.class)
	private String type;
	
	@Column(name = "NAME")
	@Convert(converter = TrimConverter.class)
	private String name;
	
	@Column(name = "CATEGORY")
	@Convert(converter = TrimConverter.class)
	private String category;
	
	@Column(name = "JOURNEY_DATE")
	private Date journeyDate;
	
	@Column(name = "FILE_PATH")
	@Convert(converter = TrimConverter.class)
	private String filePath;
	
	@Column(name = "URL")
	@Convert(converter = TrimConverter.class)
	private String url;

}
