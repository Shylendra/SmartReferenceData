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
		CSVRefDataParser csvRefDataParser =  new CSVRefDataParser();
		List<ReferenceDataCsvDto> referenceDataCsvDtoList = csvRefDataParser.parse("src/main/resources/data/ExpenseCategories.csv", ReferenceDataCsvDto.class);
		for(ReferenceDataCsvDto refDataCsvDto : referenceDataCsvDtoList) {
			if(!refDataCsvDto.isEmpty()) {
				ReferenceDataDto referenceDataDto = SmartLibraryUtil.map(refDataCsvDto, ReferenceDataDto.class);
				referenceDataServiceFacade.register(referenceDataDto);
			}
		}
		
//		List<ReferenceDataCsvDto> tClassList = new ArrayList<>();
//		tClassList.add(new ReferenceDataCsvDto("TSTCODE1", "TSTTYP1", "TSTDESC1", "TSTDETDESC1"));
//		tClassList.add(new ReferenceDataCsvDto("TSTCODE2", "TSTTYP2", "TSTDESC2", "TSTDETDESC2"));
//		
//		String writtenFilePath = csvRefDataParser.write("src/main/resources/data/ExpenseCategories_1.csv", tClassList, ReferenceDataCsvDto.class);
//		System.out.println(">>> writtenFilePath: " + writtenFilePath);
		
		System.out.println("*** Load Data from csv file - END ***");
		
	}

}
