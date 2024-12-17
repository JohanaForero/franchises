package com.accenture.franchises.infraestructure.controller;

import com.accenture.franchises.application.service.FranchiseService;
import com.accenture.franchises.infraestructure.mapper.FranchiseMapper;
import com.accenture.franchises.openapi.api.FranchisesApi;
import com.accenture.franchises.openapi.model.FranchiseRequestDto;
import com.accenture.franchises.openapi.model.FranchiseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class FranchiseController implements FranchisesApi {
    private final FranchiseService franchiseService;
    private final FranchiseMapper franchiseMapper;

    @Override
    public Mono<ResponseEntity<FranchiseResponseDto>> createFranchise(Mono<FranchiseRequestDto> franchiseRequestDto, ServerWebExchange exchange) {
        return franchiseRequestDto
                .flatMap(requestDto -> franchiseService.createFranchise(requestDto.getName())
                        .map(franchiseMapper::toDto)
                        .map(responseDto -> ResponseEntity.status(HttpStatus.CREATED).body(responseDto)));
    }
}

