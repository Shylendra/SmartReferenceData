package com.smartapps.smartreferencedata.jpa.dto;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.dto.CommonDto;
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
@Schema(name = "ReferenceData")
public class ReferenceDataDto extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String refDataCode;
	private String refDataType;
	private String refDataDescription;
	private String refDataDescriptionDetail;

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
