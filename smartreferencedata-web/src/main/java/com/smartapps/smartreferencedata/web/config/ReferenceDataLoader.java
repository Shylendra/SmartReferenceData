package com.smartapps.smartreferencedata.web.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.smartapps.smartlib.dto.ReferenceDataCsvDto;
import com.smartapps.smartlib.dto.ReferenceDataDto;
import com.smartapps.smartlib.util.SmartLibraryUtil;
import com.smartapps.smartreferencedata.jpa.parser.CSVRefDataParser;
import com.smartapps.smartreferencedata.web.service.facade.ReferenceDataServiceFacade;

@Component
public class ReferenceDataLoader implements CommandLineRunner {

	@Autowired
	protected ReferenceDataServiceFacade referenceDataServiceFacade;
	
	@Override
	public void run(String... args) throws Exception {
		
		/* Load Data from csv file */
		System.out.println("*** Load Data from csv file - START ***");
		
		/* Load ExpenseCategories.csv */
		loadData("src/main/resources/data/ExpenseCategories.csv");
		
		/* Load AddressTypes.csv */
		loadData("src/main/resources/data/AddressTypes.csv");
		
		System.out.println("*** Load Data from csv file - END ***");
		
	}
	
	private void loadData(String filePath) throws Exception {
		CSVRefDataParser csvRefDataParser =  new CSVRefDataParser();
		List<ReferenceDataCsvDto> referenceDataCsvDtoList = csvRefDataParser.parse(filePath, ReferenceDataCsvDto.class);
		for(ReferenceDataCsvDto refDataCsvDto : referenceDataCsvDtoList) {
			if(!refDataCsvDto.isEmpty()) {
				ReferenceDataDto referenceDataDto = SmartLibraryUtil.map(refDataCsvDto, ReferenceDataDto.class);
				referenceDataServiceFacade.register(referenceDataDto);
			}
		}
	}

}
