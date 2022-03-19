package com.smartapps.smartreferencedata.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartapps.smartlib.service.ClientDetailsService;
import com.smartapps.smartlib.service.MessageService;
import com.smartapps.smartreferencedata.web.service.facade.ReferenceDataServiceFacade;

public class CommonController {

	@Autowired
	protected ClientDetailsService clientDetailsService;
	
	@Autowired
	protected ReferenceDataServiceFacade referenceDataServiceFacade;

	@Autowired
	protected MessageService messageService;

}
