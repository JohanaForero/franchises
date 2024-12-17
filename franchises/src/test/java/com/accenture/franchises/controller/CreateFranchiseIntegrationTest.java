package com.accenture.franchises.controller;

import com.accenture.franchises.BaseIT;
import com.accenture.franchises.openapi.model.FranchiseRequestDto;
import com.accenture.franchises.openapi.model.FranchiseResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateFranchiseIntegrationTest extends BaseIT {
    private static final String BASE_PATH = "/franchises";

    @Test
    void createFranchise_withRequestValid_shouldReturnFranchiseResponseAndStatusCreated() {
        // Given
        final FranchiseRequestDto franchiseRequestDto = new FranchiseRequestDto();
        franchiseRequestDto.setName("macdonals");

        final FranchiseResponseDto expect = new FranchiseResponseDto();
        expect.setName("macdonals");
        expect.setIdFranchise(1L);

        // When
        final FranchiseResponseDto actualResponse = this.webTestClient.post()
                .uri(BASE_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(franchiseRequestDto))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(FranchiseResponseDto.class)
                .returnResult()
                .getResponseBody();

        // Then
        assertEquals(expect, actualResponse);
    }
}
