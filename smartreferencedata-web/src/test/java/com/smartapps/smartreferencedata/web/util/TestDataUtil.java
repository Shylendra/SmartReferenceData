package com.smartapps.smartreferencedata.web.util;

import java.util.ArrayList;
import java.util.List;

import com.smartapps.smartlib.dto.AddressDto;

public class TestDataUtil {

	
	public static List<AddressDto> prepareAddresses(int count) {
		List<AddressDto> addresses = new ArrayList<>();
		for(int i=0;i<=count;i++) {
			addresses.add(prepareAddress(i));
		}
		return addresses;
	}
	
	public static AddressDto prepareAddress(int id) {
		return AddressDto.builder()
				.id(id)
				.customerId(id+1)
				.addressLine1("addressLine"+id)
				.addressLine2("addressLine"+ (id +1))
				.city("city"+id)
				.state("state"+id)
				.country("country"+id)
				.postalCode("postalCode"+id).build();
				
	}

}
