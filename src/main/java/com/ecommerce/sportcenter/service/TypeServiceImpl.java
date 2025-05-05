package com.ecommerce.sportcenter.service;

import com.ecommerce.sportcenter.entity.Type;
import com.ecommerce.sportcenter.model.TypeResponse;
import com.ecommerce.sportcenter.repository.TypeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TypeServiceImpl  implements TypeService {
  private final TypeRepository typeRepository;

  public TypeServiceImpl(TypeRepository typeRepository) {
    this.typeRepository = typeRepository;
  }

  @Override
  public List<TypeResponse> getAllTypes() {
    log.info("fetching all types");
    List<Type> typeList = typeRepository.findAll();
    List<TypeResponse> typeResponseList = typeList.stream()
            .map(this::convertToTypeResponse)
            .collect(Collectors.toList());
    log.info("Fetched all types successfully");
    return typeResponseList;
  }

  private TypeResponse convertToTypeResponse(Type type) {
    return TypeResponse.builder()
            .id(type.getId())
            .name(type.getName())
            .build();
  }
}
