package com.smartapps.smartreferencedata.web.service.facade;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartreferencedata.jpa.dto.CodeValueDto;

public interface AddressTypeServiceFacade {
	public List<CodeValueDto> retrieveAddressTypes() throws JsonProcessingException;
}
