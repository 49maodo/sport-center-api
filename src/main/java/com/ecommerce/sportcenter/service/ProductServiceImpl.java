package com.ecommerce.sportcenter.service;

import com.ecommerce.sportcenter.entity.Product;
import com.ecommerce.sportcenter.model.ProductResponse;
import com.ecommerce.sportcenter.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<ProductResponse> getProducts() {
    log.info("Fetching all products");
    List<Product> productList = productRepository.findAll();
    List<ProductResponse> productResponses = productList.stream()
            .map(this::convertToProductResponse)
            .collect(Collectors.toList());
    log.info("Fetched " + productResponses.size() + " products");
    return productResponses;
  }

  @Override
  public ProductResponse getProductById(Integer productId) {
    log.info("Fetching product by id {}",productId);
    Product product = productRepository.findById(productId).orElseThrow(()->new RuntimeException("Product not found"));
    ProductResponse productResponse = convertToProductResponse(product);
    log.info("Fetched product by id {}",productId);
    return productResponse;
  }

  private ProductResponse convertToProductResponse(Product product) {
    return ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .pictureUrl(product.getPictureUrl())
            .productType(product.getType().getName())
            .productBrand(product.getBrand().getName())
            .build();
  }
}
