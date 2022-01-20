package com.alvarezaguilera.javatest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.alvarezaguilera.javatest.model.ProductPriceJpa;
import com.alvarezaguilera.javatest.repository.ProductPriceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CapitoleConsultingJavaTestApplication.class)
@AutoConfigureMockMvc
class CapitoleConsultingJavaTestApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ProductPriceRepository productPriceRespository;
	
	@Test
	void whenCallingProductPriceEndpoint_withNoValidArguments_thenReturnBadRequestHttpStatus() throws Exception {
		mockMvc.perform(get("/product-price")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price")
				.param("wrongDate", "2020-12-31 23:59:59")
				.param("productId", "1")
				.param("brandId","5")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price")
				.param("date", "2020-12-31 23:59:59")
				.param("prodId", "1")
				.param("brandId","5")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price")
				.param("date", "2020-12-31 23:59:59")
				.param("productId", "1")
				.param("brId","5")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price")
				.param("date", "2020-12-31 23:59:59")
				.param("productId", "1")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price")
				.param("date", "2020-12-31 23:59:59")
				.param("brandId","5")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price")
				.param("prodId", "1")
				.param("brandId","5")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price")
				.param("date", "2020-12-31 23:59:59")
				).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price")
				.param("prodId", "1")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price")
				.param("brandId","5")).andExpect(status().isBadRequest());
	}
	
	@Test
	void whenCallingProductPriceEndpoint_withWrongTypeArguments_thenReturnBadRequestHttpStatus() throws Exception {
		mockMvc.perform(get("/product-price")
				.param("date", "2020-12-31")
				.param("productId", "111")
				.param("brandId", "6")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price")
				.param("date", "Bye bye")
				.param("productId", "111")
				.param("brandId", "6")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price")
				.param("date", "1")
				.param("productId", "111")
				.param("brandId", "6")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price")
				.param("date", "2020-12-31 23:59:59")
				.param("productId", "Hello")
				.param("brandId", "6")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price")
				.param("date", "2020-12-31 23:59:59")
				.param("productId", "111")
				.param("brandId", "Hi!")).andExpect(status().isBadRequest());
	}
	
	@Test
	void whenCallingProductPriceEndpoint_withNotValidConstraintArguments_thenReturnBadRequestHttpStatus() throws Exception {
		mockMvc.perform(get("/product-price?date=null&productId=111&brandId=6")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price?date=&productId=111&brandId=6")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price?date=2020-12-31 23:59:59&productId=null&brandId=6")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price?date=2020-12-31 23:59:59&productId=&brandId=6")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price?date=2020-12-31 23:59:59&productId=111&brandId=null")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/product-price?date=2020-12-31 23:59:59&productId=111&brandId=")).andExpect(status().isBadRequest());
	}
	
	@Test
	void whenCallingProductPriceEndpoint_withNotValidRequestMethod_thenReturnBadRequestHttpStatus() throws Exception {
		mockMvc.perform(post("/product-price?date=2020-12-31 23:59:59&productId=111&brandId=6")).andExpect(status().isBadRequest());
	}
	
	@Test
	void whenCallingProductPriceEndpoint_withCorrectParams_thenReturnNoContentHttpStatus() throws Exception {
		ProductPriceJpa mockJpa = new ProductPriceJpa();
		mockJpa.setId(1L);
		mockJpa.setBrandId(1);
		mockJpa.setStartDate(LocalDateTime.of(2020,  Month.JUNE, 14, 00, 00, 00));
		mockJpa.setEndDate(LocalDateTime.of(2020,  Month.DECEMBER, 31, 23, 59, 59));
		mockJpa.setPricelist(1);
		mockJpa.setProductId(35455);
		mockJpa.setPriority(1);
		mockJpa.setProductPrice(new BigDecimal(35.50));
		mockJpa.setCurrency("EUR");
		
		productPriceRespository.save(mockJpa);
		
		mockMvc.perform(get("/product-price?date=2020-06-01T00:00:00&productId=35455&brandId=1")).andExpect(status().isNoContent());
	}
	
	@Test
	void whenCallingProductPriceEndpoint_withCorrectParams_thenReturnPriceDtoEntity_withOkHttpStatus() throws Exception {
		ProductPriceJpa mockJpa = new ProductPriceJpa();
		mockJpa.setId(1L);
		mockJpa.setBrandId(1);
		mockJpa.setStartDate(LocalDateTime.of(2020,  Month.JUNE, 14, 00, 00, 00));
		mockJpa.setEndDate(LocalDateTime.of(2020,  Month.DECEMBER, 31, 23, 59, 59));
		mockJpa.setPricelist(1);
		mockJpa.setProductId(35455);
		mockJpa.setPriority(1);
		mockJpa.setProductPrice(new BigDecimal(35.50));
		mockJpa.setCurrency("EUR");
		
		productPriceRespository.save(mockJpa);
		
		mockMvc.perform(get("/product-price?date=2020-06-30T14:00:00&productId=35455&brandId=1"))
			.andExpect(status().isOk())
			.andExpect(content().json("{'productId': 35455,"
					+ " 'brandId': 1, "
					+ " 'pricelist' : 1, "
					+ " 'startDate' : '2020-06-14T00:00:00', "
					+ " 'endDate' : '2020-12-31T23:59:59', "
					+ " 'price' : 35.50"
					+ "}"));
	}
	
	@Test
	void whenCallingProductPriceEndpoint_withCorrectParams_thenReturnPriceDtoEntity_withHighPriority_withOkHttpStatus() throws Exception {
		ProductPriceJpa mockJpa = new ProductPriceJpa();
		mockJpa.setId(1L);
		mockJpa.setBrandId(1);
		mockJpa.setStartDate(LocalDateTime.of(2020,  Month.JUNE, 14, 00, 00, 00));
		mockJpa.setEndDate(LocalDateTime.of(2020,  Month.DECEMBER, 31, 23, 59, 59));
		mockJpa.setPricelist(1);
		mockJpa.setProductId(35455);
		mockJpa.setPriority(0);
		mockJpa.setProductPrice(new BigDecimal(35.50));
		mockJpa.setCurrency("EUR");
		
		productPriceRespository.save(mockJpa);
		
		ProductPriceJpa mockJpa2 = new ProductPriceJpa();
		mockJpa2.setId(2L);
		mockJpa2.setBrandId(1);
		mockJpa2.setStartDate(LocalDateTime.of(2020,  Month.JUNE, 15, 16, 00, 00));
		mockJpa2.setEndDate(LocalDateTime.of(2020,  Month.DECEMBER, 31, 23, 59, 59));
		mockJpa2.setPricelist(4);
		mockJpa2.setProductId(35455);
		mockJpa2.setPriority(1);
		mockJpa2.setProductPrice(new BigDecimal(38.95));
		mockJpa2.setCurrency("EUR");
		
		productPriceRespository.save(mockJpa2);
		
		mockMvc.perform(get("/product-price?date=2020-06-30T14:00:00&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().json("{'productId': 35455,"
				+ " 'brandId': 1, "
				+ " 'pricelist' : 4, "
				+ " 'startDate' : '2020-06-15T16:00:00', "
				+ " 'endDate' : '2020-12-31T23:59:59', "
				+ " 'price' : 38.95"
				+ "}"));
	}
	
	
	////// Tests defined in the .txt document of the Capitole consulting test. //////
	
	@Test
	void whenCallingProductPriceEndpoint_TESTS() throws Exception {
		ProductPriceJpa mockJpa = new ProductPriceJpa();
		mockJpa.setId(1L);
		mockJpa.setBrandId(1);
		mockJpa.setStartDate(LocalDateTime.of(2020,  Month.JUNE, 14, 00, 00, 00));
		mockJpa.setEndDate(LocalDateTime.of(2020,  Month.DECEMBER, 31, 23, 59, 59));
		mockJpa.setPricelist(1);
		mockJpa.setProductId(35455);
		mockJpa.setPriority(0);
		mockJpa.setProductPrice(new BigDecimal(35.50));
		mockJpa.setCurrency("EUR");
		
		ProductPriceJpa mockJpa2 = new ProductPriceJpa();
		mockJpa2.setId(2L);
		mockJpa2.setBrandId(1);
		mockJpa2.setStartDate(LocalDateTime.of(2020,  Month.JUNE, 14, 15, 00, 00));
		mockJpa2.setEndDate(LocalDateTime.of(2020,  Month.JUNE, 14, 18, 30, 00));
		mockJpa2.setPricelist(2);
		mockJpa2.setProductId(35455);
		mockJpa2.setPriority(1);
		mockJpa2.setProductPrice(new BigDecimal(25.45));
		mockJpa2.setCurrency("EUR");
		
		ProductPriceJpa mockJpa3 = new ProductPriceJpa();
		mockJpa3.setId(3L);
		mockJpa3.setBrandId(1);
		mockJpa3.setStartDate(LocalDateTime.of(2020,  Month.JUNE, 15, 00, 00, 00));
		mockJpa3.setEndDate(LocalDateTime.of(2020,  Month.JUNE, 15, 11, 00, 00));
		mockJpa3.setPricelist(3);
		mockJpa3.setProductId(35455);
		mockJpa3.setPriority(1);
		mockJpa3.setProductPrice(new BigDecimal(30.50));
		mockJpa3.setCurrency("EUR");
		
		ProductPriceJpa mockJpa4 = new ProductPriceJpa();
		mockJpa4.setId(4L);
		mockJpa4.setBrandId(1);
		mockJpa4.setStartDate(LocalDateTime.of(2020,  Month.JUNE, 15, 16, 00, 00));
		mockJpa4.setEndDate(LocalDateTime.of(2020,  Month.DECEMBER, 31, 23, 59, 59));
		mockJpa4.setPricelist(4);
		mockJpa4.setProductId(35455);
		mockJpa4.setPriority(1);
		mockJpa4.setProductPrice(new BigDecimal(38.95));
		mockJpa4.setCurrency("EUR");
		
		productPriceRespository.saveAll(Arrays.asList(mockJpa, mockJpa2, mockJpa3, mockJpa4));
		
		// Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
		
		mockMvc.perform(get("/product-price?date=2020-06-14T10:00:00&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().json("{'productId': 35455,"
				+ " 'brandId': 1, "
				+ " 'pricelist' : 1, "
				+ " 'startDate' : '2020-06-14T00:00:00', "
				+ " 'endDate' : '2020-12-31T23:59:59', "
				+ " 'price' : 35.50"
				+ "}"));
		
		// Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
		
		mockMvc.perform(get("/product-price?date=2020-06-14T16:00:00&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().json("{'productId': 35455,"
				+ " 'brandId': 1, "
				+ " 'pricelist' : 2, "
				+ " 'startDate' : '2020-06-14T15:00:00', "
				+ " 'endDate' : '2020-06-14T18:30:00', "
				+ " 'price' : 25.45"
				+ "}"));
		
		// Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
		
		mockMvc.perform(get("/product-price?date=2020-06-14T21:00:00&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().json("{'productId': 35455,"
				+ " 'brandId': 1, "
				+ " 'pricelist' : 1, "
				+ " 'startDate' : '2020-06-14T00:00:00', "
				+ " 'endDate' : '2020-12-31T23:59:59', "
				+ " 'price' : 35.50"
				+ "}"));
		
		// Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
		
		mockMvc.perform(get("/product-price?date=2020-06-15T10:00:00&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().json("{'productId': 35455,"
				+ " 'brandId': 1, "
				+ " 'pricelist' : 3, "
				+ " 'startDate' : '2020-06-15T00:00:00', "
				+ " 'endDate' : '2020-06-15T11:00:00', "
				+ " 'price' : 30.50"
				+ "}"));
		
		// Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
		
		mockMvc.perform(get("/product-price?date=2020-06-16T21:00:00&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().json("{'productId': 35455,"
				+ " 'brandId': 1, "
				+ " 'pricelist' : 4, "
				+ " 'startDate' : '2020-06-15T16:00:00', "
				+ " 'endDate' : '2020-12-31T23:59:59', "
				+ " 'price' : 38.95"
				+ "}"));
	}
	

}
