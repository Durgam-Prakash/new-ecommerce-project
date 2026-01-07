package com.amazon.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazon.backend.constants.AuthConstants;
import com.amazon.backend.entity.Product;
import com.amazon.backend.exception.ProductNotFoundException;
import com.amazon.backend.pojo.SearchApiData;
import com.amazon.backend.repository.ProductRepository;

@Service
public class ProductsService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public List<Product> searchProduct(SearchApiData searchApiData) {
		
		List<Product> searchProducts = productRepository.searchProducts(searchApiData.getSearchWord());
		
		if(searchProducts.isEmpty()) {
			throw new  ProductNotFoundException(AuthConstants.ERROR_PRODUCT_NOT_FOUND);
		}
		
		return searchProducts;
	}

}
