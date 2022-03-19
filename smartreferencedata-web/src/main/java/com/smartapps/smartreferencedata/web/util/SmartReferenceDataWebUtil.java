package com.smartapps.smartreferencedata.web.util;

public class SmartReferenceDataWebUtil {

	/* Controller Settings */
	public static final String CONTEXT_ROOT = "/smartreferencedata-api/";

	public static final String REGISTER_REFDATA_OPERATION = "Register refdata";
	public static final String REGISTER_REFDATA = "refdatas";
	
	public static final String RETRIEVE_REFDATAS_OPERATION = "Retrieve refdatas";
	public static final String RETRIEVE_REFDATAS = "refdatas";
	
	public static final String RETRIEVE_REFDATA_OPERATION = "Retrieve refdata by id";
	public static final String RETRIEVE_REFDATA = "refdatas/{id}";
	
	public static final String RETRIEVE_REFDATA_TYPE_OPERATION = "Retrieve refdatas by type";
	public static final String RETRIEVE_REFDATA_TYPE = "refdatas/type/{type}";
	
	public static final String UPDATE_REFDATA_OPERATION = "Update refdatas";
	public static final String UPDATE_REFDATA = "refdatas/{id}";
	
	public static final String DELETE_REFDATA_OPERATION = "Delete refdatas by id";
	public static final String DELETE_REFDATA = "refdatas/{id}";

	public static final String SEARCH_REFDATA_OPERATION = "Search refdata";
	public static final String SEARCH_REFDATA = "search";

	/* OpenAPI Settings */
	public static final String TITLE = CONTEXT_ROOT.replaceAll("/", "");
	public static final String DESCRIPTION = "SmartReferenceData REST API Information";
	public static final String VERSION = "1.0.0";

	private SmartReferenceDataWebUtil() {
	}

}
