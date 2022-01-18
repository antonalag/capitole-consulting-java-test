package com.alvarezaguilera.javatest.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alvarezaguilera.javatest.model.PriceDto;
import com.alvarezaguilera.javatest.service.PriceService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("/")
@Validated
public class PriceController {

	@Autowired
	private PriceService priceService;
	
	@GetMapping(path = "/product-price", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PriceDto> productPrice(
			@RequestParam(value = "date") 
			@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
			@NotBlank
			@NotNull
			@Valid
			LocalDateTime date,
			@RequestParam(value = "productId")
			@NotBlank
			@NotNull
			@Valid
			Integer productId,
			@RequestParam(value = "brandId")
			@NotBlank
			@NotNull
			@Valid
			Integer brandId
			) {
		PriceDto priceDto = priceService.getProductPriceByParameters(null, null, null);
		return priceDto != null ? ResponseEntity.ok(priceDto) : ResponseEntity.noContent().build();
	}
	
}
