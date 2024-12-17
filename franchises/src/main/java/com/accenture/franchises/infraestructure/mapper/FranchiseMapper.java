package com.accenture.franchises.infraestructure.mapper;

import com.accenture.franchises.domain.model.Branch;
import com.accenture.franchises.domain.model.Franchise;
import com.accenture.franchises.infraestructure.repository.entity.BranchEntity;
import com.accenture.franchises.infraestructure.repository.entity.FranchiseEntity;
import com.accenture.franchises.openapi.model.FranchiseRequestDto;
import com.accenture.franchises.openapi.model.FranchiseResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {
    Franchise toModel(FranchiseRequestDto franchiseRequestDto);

    @Mapping(target = "idFranchise", source = "id")
    FranchiseResponseDto toDto(Franchise franchise);

    FranchiseEntity toEntity(Franchise franchise);

    BranchEntity toEntity(Branch branch);

    Franchise toDomain(FranchiseEntity franchiseEntity);
}
