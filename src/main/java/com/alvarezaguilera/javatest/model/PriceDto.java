package com.alvarezaguilera.javatest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceDto {
	
	private Integer productId;
	private Integer brandId;
	private Integer pricelist;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private BigDecimal price;
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getBrandId() {
		return brandId;
	}
	
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	
	public Integer getPricelist() {
		return pricelist;
	}
	
	public void setPricelist(Integer pricelist) {
		this.pricelist = pricelist;
	}
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	
	public LocalDateTime getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
