package com.smartapps.smartreferencedata.web.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.smartapps.smartlib.dto.ReferenceDataDto;
import com.smartapps.smartlib.util.SmartLibraryUtil;

public class SmartReferenceDataWebUtil {

	/* Controller context root */
	public static final String CONTEXT_ROOT = "/smartreferencedata-api/";

	/* SmartReferenceDataController Settings */
	public static final String REGISTER_REFDATA_OPERATION = "Register refdata";
	public static final String REGISTER_REFDATA = "refdatas";
	
	public static final String REGISTER_REFDATA_TYPE_OPERATION = "Register refdata";
	public static final String REGISTER_REFDATA_TYPE = "refdatas/{type}";
	
	public static final String RETRIEVE_REFDATAS_OPERATION = "Retrieve refdatas";
	public static final String RETRIEVE_REFDATAS = "refdatas";
	
	public static final String RETRIEVE_REFDATA_TYPE_OPERATION = "Retrieve refdatas by type";
	public static final String RETRIEVE_REFDATA_TYPE = "refdatas/{type}";
	
	public static final String EXPORT_REFDATAS_OPERATION = "Export refdatas";
	public static final String EXPORT_REFDATAS = "export-data";
	
	public static final String IMPORT_REFDATAS_OPERATION = "Import refdatas";
	public static final String IMPORT_REFDATAS = "import-data";
	
	public static final String UPDATE_REFDATA_OPERATION = "Update refdatas";
	public static final String UPDATE_REFDATA = "refdatas/{code}/{type}";
	
	public static final String DELETE_REFDATA_OPERATION = "Delete refdatas by id";
	public static final String DELETE_REFDATA = "refdatas/{code}/{type}";

	public static final String SEARCH_REFDATA_OPERATION = "Search refdata";
	public static final String SEARCH_REFDATA = "search";

	/* ExpenseController Settings */
	public static final String EXPENSE_CATEGORIES_OPERATION = "Retrieve expense categories";
	public static final String EXPENSE_CATEGORIES = "expenses/categories";
	

	/* Countries/States/Cities */
	public static final String RETRIEVE_COUNTRIES_OPERATION = "Retrieve countries";
	public static final String RETRIEVE_COUNTRIES = "countries";

	public static final String RETRIEVE_COUNTRY_OPERATION = "Retrieve countries by country code";
	public static final String RETRIEVE_COUNTRY = "countries/{countryCode}";

	public static final String RETRIEVE_STATES_OPERATION = "Retrieve states within a country";
	public static final String RETRIEVE_STATES = "countries/{countryCode}/states";

	public static final String RETRIEVE_STATE_OPERATION = "Retrieve state within a country";
	public static final String RETRIEVE_STATE = "countries/{countryCode}/states/{stateCode}";

	public static final String RETRIEVE_COUNTRY_CITIES_OPERATION = "Retrieve cities within a country";
	public static final String RETRIEVE_COUNTRY_CITIES = "countries/{countryCode}/cities";

	public static final String RETRIEVE_CITIES_OPERATION = "Retrieve cities in a state";
	public static final String RETRIEVE_CITIES = "countries/{countryCode}/states/{stateCode}/cities";

	/* AssetDetailController Settings */
	public static final String REGISTER_ASSETDETAIL_OPERATION = "Register assetDetail";
	public static final String REGISTER_ASSETDETAIL = "assetdetails";

	public static final String RETRIEVE_ASSETDETAIL_ID_OPERATION = "Retrieve assetdetails by id";
	public static final String RETRIEVE_ASSETDETAIL_ID = "assetdetails/{id}";
	
	public static final String UPDATE_ASSETDETAIL_OPERATION = "Update assetDetail by id";
	public static final String UPDATE_ASSETDETAIL = "assetdetails/{id}";
	
	public static final String DELETE_ASSETDETAIL_OPERATION = "Delete assetDetail by id";
	public static final String DELETE_ASSETDETAIL = "assetdetails/{id}";

	/* OpenAPI Settings */
	public static final String TITLE = CONTEXT_ROOT.replaceAll("/", "");
	public static final String DESCRIPTION = "SmartReferenceData REST API Information";
	public static final String VERSION = "1.0.0";

	private SmartReferenceDataWebUtil() {
	}
	
	public static List<ReferenceDataDto> extractCodes(String type, List<ReferenceDataDto> codes) {
		return codes.stream()
				.filter(code -> code.getRefDataType().trim().equals(type))
				.collect(Collectors.toList());
	}
	public static Map<String, List<ReferenceDataDto>> extractCodes(String content) throws IOException {
		Map<String, List<ReferenceDataDto>> codesMap = new HashMap<>();
		List<ReferenceDataDto> codes = SmartLibraryUtil.mapToObjectList(content, ReferenceDataDto.class);
		for(ReferenceDataDto code: codes) {
			codesMap.put(code.getRefDataType(), extractCodes(code.getRefDataType(), codes));
		}
		
		return codesMap;
	}
	
	public static Map<String, List<ReferenceDataDto>> extractCodes(List<ReferenceDataDto> codes) throws IOException {
		Map<String, List<ReferenceDataDto>> codesMap = new HashMap<>();
		for(ReferenceDataDto code: codes) {
			codesMap.put(code.getRefDataType(), extractCodes(code.getRefDataType(), codes));
		}
		
		return codesMap;
	}

}
