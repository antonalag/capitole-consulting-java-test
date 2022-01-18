package com.alvarezaguilera.javatest.converter;

import com.alvarezaguilera.javatest.model.ProductPriceJpa;
import com.alvarezaguilera.javatest.model.ProductPriceDto;

public interface ProductPriceConverter {

	/**
	 * Convert from Price JPA to Price DTO
	 * @param jpa {@link ProductPriceJpa}
	 * @return {@link ProductPriceDto}
	 */
	ProductPriceDto fromProductPriceJpaToProductPriceDto(ProductPriceJpa jpa);
	
}
