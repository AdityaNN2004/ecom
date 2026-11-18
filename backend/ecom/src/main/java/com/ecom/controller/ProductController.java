package com.ecom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.ProductResponse;
import com.ecom.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> getProducts(){
		return ResponseEntity.ok(productService.getAllProducts());
	}
	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductResponse dto){
		return new ResponseEntity<ProductResponse>(productService.createProduct(dto),HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> editProduct(@PathVariable Long id,@RequestBody ProductResponse dto){
		return productService.updateProduct(id, dto)
				.map(ResponseEntity::ok)
				.orElseGet(()->ResponseEntity.notFound().build());
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id){
		return productService.deleteProduct(id)
				.map(ResponseEntity::ok)
				.orElseGet(()->ResponseEntity.notFound().build());
	}
	@GetMapping("/search")
	public ResponseEntity<List<ProductResponse>> searchProduct(@RequestParam String keyword){
		return ResponseEntity.ok(productService.searchProducts(keyword));
	}
}
