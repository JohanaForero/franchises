package com.accenture.franchises.infraestructure.mapper;

import com.accenture.franchises.domain.model.Product;
import com.accenture.franchises.infraestructure.repository.entity.ProductEntity;
import com.accenture.franchises.openapi.model.ProductRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "branch", ignore = true)
    Product toModel(ProductRequestDto productRequestDto);

    ProductEntity toEntity(Product product);

    Product toModel(ProductEntity productEntity);

    ProductRequestDto toDto(Product product);
}
