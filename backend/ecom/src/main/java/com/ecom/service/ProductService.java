package com.ecom.service;



import java.util.List;
import java.util.Optional;


import com.ecom.dto.ProductResponse;

public interface ProductService {

	ProductResponse createProduct(ProductResponse dto);

	
	Optional<ProductResponse> updateProduct(Long id,ProductResponse dto);


	List<ProductResponse> getAllProducts();


	Optional<ProductResponse> deleteProduct(Long id);


	List<ProductResponse> searchProducts(String keyword);

}
