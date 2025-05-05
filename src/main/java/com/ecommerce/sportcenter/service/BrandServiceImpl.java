package com.ecommerce.sportcenter.service;

import com.ecommerce.sportcenter.entity.Brand;
import com.ecommerce.sportcenter.model.BrandResponse;
import com.ecommerce.sportcenter.repository.BrandRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BrandServiceImpl implements BrandService {
  private final BrandRepository brandRepository;

  public BrandServiceImpl(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  @Override
  public List<BrandResponse> getAllBrands() {
    log.info("Fetching all brands");
    // Fetch Brand
    List<Brand> brandList = brandRepository.findAll();
    //use stream to convert Brand to BrandResponse
    List<BrandResponse> brandResponseList = brandList.stream()
            .map(this::ConvertToBrandResponse).
            collect(Collectors.toList());
    log.info("Fetched all brands successfully");
    return brandResponseList;
  }

  private BrandResponse ConvertToBrandResponse(Brand brand) {
    return BrandResponse.builder()
            .id(brand.getId())
            .name(brand.getName())
            .build();
  }
}
