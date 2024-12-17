package com.accenture.franchises.controller;

import com.accenture.franchises.BaseIT;
import com.accenture.franchises.infraestructure.repository.entity.FranchiseEntity;
import org.junit.jupiter.api.Test;

class CreateProductIntegrationTest extends BaseIT {
    private static final String BASE_PATH = "/franchises/{nameBranch}/product";

    @Test
    void createProduct_withRequestValid_shouldReturnProductRequestDtoAndStatusCreated() {
        // Given
        FranchiseEntity franchiseEntity = new FranchiseEntity();
        franchiseEntity.setName("macdonals");
        franchiseEntity.setId(123L);
        this.reactiveMongoTemplate.insert(franchiseEntity).block();

    }
}
