package com.ecommerce.sportcenter.controller;

import com.ecommerce.sportcenter.model.BrandResponse;
import com.ecommerce.sportcenter.model.ProductResponse;
import com.ecommerce.sportcenter.model.TypeResponse;
import com.ecommerce.sportcenter.service.BrandService;
import com.ecommerce.sportcenter.service.ProductService;
import com.ecommerce.sportcenter.service.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<Page<ProductResponse>> getProducts(
          @RequestParam(name = "page", defaultValue = "0") int page,
          @RequestParam(name = "size", defaultValue = "10") int size,
          @RequestParam(name = "keyword", required = false) String keyword,
          @RequestParam(name = "brandId", required = false) Integer brandId,
          @RequestParam(name = "typeId", required = false) Integer typeId,
          @RequestParam(name = "sort", defaultValue = "name") String sort,
          @RequestParam(name = "order", defaultValue = "asc") String order
  ){
    //Convert order to Sort direction
    Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC: Sort.Direction.ASC;
    Sort sorting = Sort.by(direction, sort);
    Pageable pageable = PageRequest.of(page, size, sorting);

    Page<ProductResponse> productResponses = productService.getProducts(pageable, brandId, typeId, keyword);
    return new ResponseEntity<>(productResponses, HttpStatus.OK);
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
