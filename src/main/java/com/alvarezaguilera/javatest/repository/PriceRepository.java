package com.alvarezaguilera.javatest.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alvarezaguilera.javatest.model.PriceJpa;

@Repository
public interface PriceRepository extends CrudRepository<PriceJpa, Long> {
	
	@Query("SELECT p "
			+ "FROM Price p "
			+ "WHERE p.productId = :productId "
			+ "AND p.brandId = :brandId "
			+ "AND p.startDate >= :date "
			+ "AND p.endDate <= :date "
			+ "ORDER BY p.priority DESC")
	List<PriceJpa> findProductPriceByParameters(@Param("date")LocalDateTime date, @Param("productId") Integer productId, 
			@Param("brandId") Integer brandId);

}
