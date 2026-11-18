package com.ecom.service;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecom.dto.ProductResponse;
import com.ecom.entity.Product;
import com.ecom.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	@Override
	public ProductResponse createProduct(ProductResponse dto) {
		Product product = new Product();
		addDetailsToProduct(product,dto);
		Product savedProduct = productRepository.save(product);
		return mapToProductResponse(savedProduct);
	}

	private ProductResponse mapToProductResponse(Product savedProduct) {
		ProductResponse dto= new ProductResponse(
					String.valueOf(savedProduct.getId()),
					savedProduct.getName(),
					savedProduct.getDescription(),
					savedProduct.getPrice(),
					savedProduct.getStocks(),
					savedProduct.getCategory(),
					savedProduct.getImageUrl()
				);
		
		return dto;
	}

	private void addDetailsToProduct(Product product, ProductResponse dto) {
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setStocks(dto.getStocks());
		product.setCategory(dto.getCategory());
		product.setImageUrl(dto.getImageUrl());
	}

	@Override
	public Optional<ProductResponse> updateProduct(Long id, ProductResponse dto) {
		return productRepository.findById(id)
				.map(existingProduct -> {
					addDetailsToProduct(existingProduct, dto);
					Product savedProduct = productRepository.save(existingProduct);
					return mapToProductResponse(savedProduct);
				});
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		return productRepository.findByIsActiveTrue().stream()
				.map(this::mapToProductResponse)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<ProductResponse> deleteProduct(Long id) {
		return productRepository.findById(id).map(product ->{
			product.setIsActive(false);
			Product deletedProduct = productRepository.save(product);
			return mapToProductResponse(deletedProduct);
		});
	}

	@Override
	public List<ProductResponse> searchProducts(String keyword) {
		return productRepository.searchProduct(keyword).stream()
				.map(product -> mapToProductResponse(product))
				.toList();
	}
}
