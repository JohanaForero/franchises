package com.accenture.franchises.controller;

import com.accenture.franchises.BaseIT;
import com.accenture.franchises.infraestructure.repository.entity.FranchiseEntity;
import com.accenture.franchises.openapi.model.BranchRequestDto;
import com.accenture.franchises.openapi.model.BranchResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateBranchIntegrationTest extends BaseIT {
    private static final String BASE_PATH = "/franchises/{idFranchise}/branch";

    @Test
    void createFranchise_withRequestValid_shouldReturnFranchiseResponseAndStatusCreated() {
        // Given
        FranchiseEntity franchiseEntity = new FranchiseEntity();
        franchiseEntity.setName("macdonals");
        franchiseEntity.setId(123L);
        this.reactiveMongoTemplate.insert(franchiseEntity).block();

        final BranchRequestDto request = new BranchRequestDto();
        request.setName("cucuta");

        final BranchResponseDto expect = new BranchResponseDto();
        expect.setName("cucuta");

        // When
        final BranchResponseDto actualResponse = this.webTestClient.post()
                .uri(BASE_PATH, 123)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BranchResponseDto.class)
                .returnResult()
                .getResponseBody();

        // Then
        assertEquals(expect, actualResponse);
    }
}
