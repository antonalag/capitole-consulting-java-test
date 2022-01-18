package com.alvarezaguilera.javatest.converter;

import org.springframework.stereotype.Component;

import com.alvarezaguilera.javatest.model.ProductPriceDto;
import com.alvarezaguilera.javatest.model.ProductPriceJpa;

@Component
public class PriceConverterImpl implements ProductPriceConverter{

	@Override
	public ProductPriceDto fromProductPriceJpaToProductPriceDto(ProductPriceJpa jpa) {
		if(jpa != null) {
			ProductPriceDto dto = new ProductPriceDto();
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
