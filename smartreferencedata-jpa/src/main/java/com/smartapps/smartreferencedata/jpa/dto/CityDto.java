package com.smartapps.smartreferencedata.jpa.dto;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.util.SmartLibraryUtil;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "City")
public class CityDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private String countryCode;
	private String countryName;
	private String stateCode;
	private String stateName;
	private String type;
	private String population;
	private String latitude;
	private String longitude;

	@Override
	public String toString() {
		try {
			return SmartLibraryUtil.mapToString(this, true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
