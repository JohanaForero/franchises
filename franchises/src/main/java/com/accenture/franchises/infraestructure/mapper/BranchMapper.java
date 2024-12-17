package com.accenture.franchises.infraestructure.mapper;

import com.accenture.franchises.domain.model.Branch;
import com.accenture.franchises.infraestructure.repository.entity.BranchEntity;
import com.accenture.franchises.openapi.model.BranchResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchEntity toEntity(Branch branch);

    Branch toDomain(BranchEntity branchEntity);

    BranchResponseDto toDto(Branch branch);
}
