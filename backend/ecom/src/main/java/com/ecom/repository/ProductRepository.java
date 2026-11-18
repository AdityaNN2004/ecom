package com.ecom.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecom.dto.ProductResponse;
import com.ecom.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByIsActiveTrue();
//	Optional<ProductResponse> deleteProduct(Long id);
	@Query("select p from Product p where p.isActive=true and p.stocks > 0 and lower(p.name) like lower(concat('%',:keyword,'%'))")
	List<Product> searchProduct(@Param("keyword") String keyword);
}
