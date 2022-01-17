package com.alvarezaguilera.javatest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alvarezaguilera.javatest.util.Constants;


@Entity
@Table(name = Constants.PRICE_TABLE_NAME)
public class Price {

	@Id
	@Column(name = Constants.ID_COLUMN_NAME)
	private Long id;
	@Column(name = Constants.BRAND_ID_COLUMN_NAME)
	private int brandId;
	@Column(name = Constants.START_DATE_COLUMN_NAME)
	private LocalDateTime startDate;
	@Column(name = Constants.END_DATE_COLUMN_NAME)
	private LocalDateTime endDate;
	@Column(name = Constants.PRICE_LIST_COLUMN_NAME)
	private Integer pricelist;
	@Column(name = Constants.PRODUCT_ID_COLUMN_NAME)
	private Integer productId;
	@Column(name = Constants.PRIORITY_COLUMN_NAME)
	private Integer priority;
	@Column(name = Constants.PRICE_COLUMN_NAME)
	private BigDecimal productPrice;
	@Column(name = Constants.CURRENCY_COLUMN_NAME)
	private String currency;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
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
	
	public Integer getPricelist() {
		return pricelist;
	}
	
	public void setPricelist(Integer pricelist) {
		this.pricelist = pricelist;
	}
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getPriority() {
		return priority;
	}
	
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
