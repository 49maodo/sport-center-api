package com.ecommerce.sportcenter.service;


import com.ecommerce.sportcenter.model.ProductResponse;

import java.util.List;

public interface ProductService {
  List<ProductResponse> getProducts();
  ProductResponse getProductById(Integer productId);
}
