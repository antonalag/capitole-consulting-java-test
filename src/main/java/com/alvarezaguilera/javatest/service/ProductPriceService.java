package com.alvarezaguilera.javatest.service;

import java.time.LocalDateTime;

import com.alvarezaguilera.javatest.model.ProductPriceDto;

public interface ProductPriceService {
	
	/**
	 * Get Product price data
	 * @param date application date
	 * @param productId product identifier
	 * @param brandId brand identifier
	 * @return The product price {@link ProductPriceDto}
	 */
	ProductPriceDto getProductPriceByParameters(LocalDateTime date, Integer productId, Integer brandId);

}
