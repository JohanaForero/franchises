package com.accenture.franchises.infraestructure.controller;

import com.accenture.franchises.application.service.BranchService;
import com.accenture.franchises.application.service.FranchiseService;
import com.accenture.franchises.infraestructure.mapper.BranchMapper;
import com.accenture.franchises.infraestructure.mapper.FranchiseMapper;
import com.accenture.franchises.openapi.api.FranchisesApi;
import com.accenture.franchises.openapi.model.BranchRequestDto;
import com.accenture.franchises.openapi.model.BranchResponseDto;
import com.accenture.franchises.openapi.model.FranchiseRequestDto;
import com.accenture.franchises.openapi.model.FranchiseResponseDto;
import com.accenture.franchises.openapi.model.ProductRequestDto;
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
    private final BranchService branchService;
    private final BranchMapper branchMapper;
    private final FranchiseMapper franchiseMapper;

    @Override
    public Mono<ResponseEntity<BranchResponseDto>> createBranch(Integer idFranchise, Mono<BranchRequestDto> branchRequestDto, ServerWebExchange exchange) {
        return branchRequestDto
                .flatMap(requestDto -> branchService.createBranch(idFranchise, requestDto.getName())
                        .map(branchMapper::toDto)
                        .map(responseDto -> ResponseEntity.status(HttpStatus.CREATED).body(responseDto)));
    }


    @Override
    public Mono<ResponseEntity<FranchiseResponseDto>> createFranchise(Mono<FranchiseRequestDto> franchiseRequestDto, ServerWebExchange exchange) {
        return franchiseRequestDto
                .flatMap(requestDto -> franchiseService.createFranchise(requestDto.getName())
                        .map(franchiseMapper::toDto)
                        .map(responseDto -> ResponseEntity.status(HttpStatus.CREATED).body(responseDto)));
    }

    @Override
    public Mono<ResponseEntity<ProductRequestDto>> createProduct(String nameBranch, Mono<ProductRequestDto> productRequestDto, ServerWebExchange exchange) {
        return FranchisesApi.super.createProduct(nameBranch, productRequestDto, exchange);
    }
}

