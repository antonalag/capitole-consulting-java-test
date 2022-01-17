package com.alvarezaguilera.javatest.converter;

import org.springframework.stereotype.Component;

import com.alvarezaguilera.javatest.model.PriceDto;
import com.alvarezaguilera.javatest.model.PriceJpa;

@Component
public class PriceConverterImpl implements PriceConverter{

	@Override
	public PriceDto fromPriceToProceDto(PriceJpa jpa) {
		if(jpa != null) {
			PriceDto dto = new PriceDto();
			dto.setProductId(jpa.getProductId());
			dto.setBrandId(jpa.getBrandId());
			dto.setPricelist(jpa.getPricelist());
			dto.setStartDate(jpa.getStartDate());
			dto.setEndDate(jpa.getEndDate());
			dto.setPrice(jpa.getProductPrice());
			return dto;
		}
		
		return null;
	}

}
