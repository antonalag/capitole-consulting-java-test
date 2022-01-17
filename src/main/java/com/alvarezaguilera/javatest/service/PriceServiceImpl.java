package com.alvarezaguilera.javatest.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.alvarezaguilera.javatest.model.PriceDto;

@Service
public class PriceServiceImpl implements PriceService{

	@Override
	public PriceDto getProductPriceByParameters(LocalDateTime date, Integer productId, Integer brandId) {
		// TODO Auto-generated method stub
		return null;
	}

}
