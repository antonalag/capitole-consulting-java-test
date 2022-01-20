package com.alvarezaguilera.javatest.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvarezaguilera.javatest.converter.ProductPriceConverter;
import com.alvarezaguilera.javatest.exception.NotFoundException;
import com.alvarezaguilera.javatest.model.ProductPriceDto;
import com.alvarezaguilera.javatest.model.ProductPriceJpa;
import com.alvarezaguilera.javatest.repository.ProductPriceRepository;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductPriceServiceImpl.class);
	
	@Autowired
	private ProductPriceRepository priceRepository;
	
	@Autowired
	private ProductPriceConverter priceConverter;

	@Override
	public ProductPriceDto getProductPriceByParameters(LocalDateTime date, Integer productId, Integer brandId) {
		LOG.info("Getting product price by date: {}, product id: {} and brand id: {}", date, productId, brandId);
		validate(productId, brandId);
		List<ProductPriceJpa> productPriceList = priceRepository.findProductPriceByParameters(date, productId, brandId);
		
		if(productPriceList.isEmpty()) {
			LOG.info("There is no product price for the parameters date: {}, product id: {}, brand id: {}", date, productId, brandId);
			return null;
		}
		
		ProductPriceJpa productPrice = productPriceList.stream().findFirst().get();
		
		return priceConverter.fromProductPriceJpaToProductPriceDto(productPrice);
		
	}
	
	/**
	 * Checks if a product and a brand exist in the price table
	 * @param productId product id
	 * @param brandId brand id
	 */
	private void validate(Integer productId, Integer brandId) {
		boolean productExists = priceRepository.existsByProductId(productId);
		
		if(!productExists) {
			LOG.info("The product with id {} has not been found", productId);
			throw new NotFoundException("The product with id " + productId + " has not been found");
		}
		
		boolean brandExists = priceRepository.existsByBrandId(brandId);
		if(!brandExists) {
			LOG.info("The brand with id {} has not been found", brandId);
			throw new NotFoundException("The brand with id " + brandId + " has not been found");
		}
	}

}
