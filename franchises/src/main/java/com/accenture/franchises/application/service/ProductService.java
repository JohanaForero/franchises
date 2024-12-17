package com.accenture.franchises.application.service;

import com.accenture.franchises.application.port.BranchPort;
import com.accenture.franchises.application.port.DbPort;
import com.accenture.franchises.application.validator.ProductValidator;
import com.accenture.franchises.domain.model.Product;
import reactor.core.publisher.Mono;

public record ProductService(ProductValidator productValidator, DbPort dbPort, BranchPort branchPort) {
    public Mono<Product> createProduct(final String nameBranch, final Product product) {
        return productValidator.getBranch(nameBranch)
                .switchIfEmpty(Mono.error(new FranchiseException.BranchServiceException("FR-BS-003"))) // Si no existe, lanza excepción
                .flatMap(branch -> {
                    Product newProduct = product.toBuilder()
                            .branch(branch)
                            .build();
                    return branchPort.saveProduct(newProduct); // Guardar el producto
                });
    }
}
