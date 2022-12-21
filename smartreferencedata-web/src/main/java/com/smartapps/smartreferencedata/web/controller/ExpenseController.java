package com.smartapps.smartreferencedata.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.annotations.GlobalApiReponsesGet;
import com.smartapps.smartreferencedata.jpa.dto.CodeValueDto;
import com.smartapps.smartreferencedata.web.util.SmartReferenceDataWebUtil;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping(SmartReferenceDataWebUtil.CONTEXT_ROOT)
public class ExpenseController extends CommonController {
	
	@Operation(summary = SmartReferenceDataWebUtil.EXPENSE_CATEGORIES_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartReferenceDataWebUtil.EXPENSE_CATEGORIES)
	public ResponseEntity<List<CodeValueDto>> retrieveCategories(HttpServletRequest request) 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(expenseServiceFacade.retrieveCategories());
	}

}
