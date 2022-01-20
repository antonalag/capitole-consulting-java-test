package com.alvarezaguilera.javatest.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alvarezaguilera.javatest.model.ProductPriceDto;
import com.alvarezaguilera.javatest.service.ProductPriceService;

@RestController
@RequestMapping("/")
@Validated
public class ProductPriceController {

	@Autowired
	private ProductPriceService productPriceService;
	
	@GetMapping(path = "/product-price", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductPriceDto> productPrice(
			@RequestParam(value = "date", required = true) 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			@Valid
			LocalDateTime date,
			@RequestParam(value = "productId", required = true)
			@Valid
			Integer productId,
			@RequestParam(value = "brandId", required = true)
			@Valid
			Integer brandId
			) {
		ProductPriceDto priceDto = productPriceService.getProductPriceByParameters(date, productId, brandId);
		return priceDto != null ? ResponseEntity.ok(priceDto) : ResponseEntity.noContent().build();
	}
	
}
