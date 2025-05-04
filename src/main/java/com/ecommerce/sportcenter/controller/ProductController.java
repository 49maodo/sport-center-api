package com.ecommerce.sportcenter.controller;

import com.ecommerce.sportcenter.model.BrandResponse;
import com.ecommerce.sportcenter.model.ProductResponse;
import com.ecommerce.sportcenter.model.TypeResponse;
import com.ecommerce.sportcenter.service.BrandService;
import com.ecommerce.sportcenter.service.ProductService;
import com.ecommerce.sportcenter.service.ProductServiceImpl;
import com.ecommerce.sportcenter.service.TypeService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService productService;
  private final BrandService brandService;
  private final TypeService typeService;

  public ProductController(ProductService productService, BrandService brandService, TypeService typeService) {
    this.productService = productService;
    this.brandService = brandService;
    this.typeService = typeService;
  }

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAllProducts() {
    List<ProductResponse> productResponses = productService.getProducts();
    return ResponseEntity.ok(productResponses);
  }

  @GetMapping("{id}")
  public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Integer productId) {
    ProductResponse productResponse = productService.getProductById(productId);
    return ResponseEntity.ok(productResponse);
  }

  @GetMapping("/brands")
  public ResponseEntity<List<BrandResponse>> getAllBrands() {
    List<BrandResponse> brandResponses = brandService.getAllBrands();
    return ResponseEntity.ok(brandResponses);
  }

  @GetMapping("/types")
  public ResponseEntity<List<TypeResponse>> getAllTypes() {
    List<TypeResponse> typeResponses = typeService.getAllTypes();
    return ResponseEntity.ok(typeResponses);
  }
}
