package com.accenture.franchises.infraestructure.mapper;

import com.accenture.franchises.domain.model.Branch;
import com.accenture.franchises.infraestructure.repository.entity.BranchEntity;
import com.accenture.franchises.openapi.model.BranchResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "idFranchise.id", source = "franchise.id")
    BranchEntity toEntity(Branch branch);

    Branch toDomain(BranchEntity branchEntity);

    BranchResponseDto toDto(Branch branch);
}
