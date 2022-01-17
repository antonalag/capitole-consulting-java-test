package com.alvarezaguilera.javatest.converter;

import com.alvarezaguilera.javatest.model.PriceJpa;
import com.alvarezaguilera.javatest.model.PriceDto;

public interface PriceConverter {

	/**
	 * Convert from Price JPA to Price DTO
	 * @param jpa {@link PriceJpa}
	 * @return {@link PriceDto}
	 */
	PriceDto fromPriceToProceDto(PriceJpa jpa);
	
}
