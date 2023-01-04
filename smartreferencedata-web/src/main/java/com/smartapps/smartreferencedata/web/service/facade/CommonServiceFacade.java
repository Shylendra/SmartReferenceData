package com.smartapps.smartreferencedata.web.service.facade;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartapps.smartlib.service.MessageService;
import com.smartapps.smartreferencedata.jpa.service.AssetDetailService;
import com.smartapps.smartreferencedata.jpa.service.ReferenceDataService;
import com.smartapps.smartreferencedata.web.assemblers.SmartRefDataAssembler;

public class CommonServiceFacade {

	@Autowired
	protected ReferenceDataService referenceDataService;
	
	@Autowired
	protected AssetDetailService assetDetailService;
	
	@Autowired
	protected SmartRefDataAssembler smartRefDataAssembler;

	@Autowired
	protected MessageService messageService;

}
