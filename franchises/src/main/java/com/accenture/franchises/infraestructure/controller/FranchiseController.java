package com.accenture.franchises.infraestructure.controller;

import com.accenture.franchises.application.service.BranchService;
import com.accenture.franchises.application.service.FranchiseService;
import com.accenture.franchises.application.service.ProductService;
import com.accenture.franchises.infraestructure.mapper.BranchMapper;
import com.accenture.franchises.infraestructure.mapper.FranchiseMapper;
import com.accenture.franchises.infraestructure.mapper.ProductMapper;
import com.accenture.franchises.openapi.api.FranchisesApi;
import com.accenture.franchises.openapi.model.BranchRequestDto;
import com.accenture.franchises.openapi.model.BranchResponseDto;
import com.accenture.franchises.openapi.model.FranchiseRequestDto;
import com.accenture.franchises.openapi.model.FranchiseResponseDto;
import com.accenture.franchises.openapi.model.ProductRequestDto;
import com.accenture.franchises.openapi.model.ProductResponseDto;
import com.accenture.franchises.openapi.model.ProductResponseDtoDto;
import com.accenture.franchises.openapi.model.UpdateProductQuantityRequestDto;
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
    private final ProductService productService;
    private final ProductMapper productMapper;
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
    public Mono<ResponseEntity<ProductRequestDto>> createProduct(String nameBranch,
                                                                 Mono<ProductRequestDto> productRequestDto,
                                                                 ServerWebExchange exchange) {
        return productRequestDto
                .flatMap(requestDto -> productService.createProduct(nameBranch, this.productMapper.toModel(requestDto)))
                .map(productMapper::toDto)
                .map(responseDto -> ResponseEntity.status(HttpStatus.CREATED).body(responseDto));
    }

    @Override
    public Mono<ResponseEntity<ProductResponseDto>> deleteProduct(String nameProduct, ServerWebExchange exchange) {
        return productService.deleteProductByName(nameProduct)
                .map(message -> {
                    ProductResponseDto responseDto = productMapper.toResponse(message);
                    return ResponseEntity.ok(responseDto);
                });
    }

    @Override
    public Mono<ResponseEntity<ProductRequestDto>> updateProductQuantity(String nameProduct, Mono<UpdateProductQuantityRequestDto> updateProductQuantityRequestDto, ServerWebExchange exchange) {
        return updateProductQuantityRequestDto
                .flatMap(requestDto ->
                        productService.updateProductQuantity(nameProduct, requestDto.getTotal())
                                .map(updatedProduct -> {
                                    ProductRequestDto responseDto = productMapper.toDto(updatedProduct);
                                    return ResponseEntity.ok(responseDto);
                                })
                );
    }

    @Override
    public Mono<ResponseEntity<ProductResponseDtoDto>> getProductWithMaxQuantity(ServerWebExchange exchange) {
        return this.productService.getProductWithMaxQuantity()
                .map(productMapper::toResponseDto)
                .map(ResponseEntity::ok);
    }
}

