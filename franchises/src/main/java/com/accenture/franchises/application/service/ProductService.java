package com.accenture.franchises.application.service;

import com.accenture.franchises.application.port.BranchPort;
import com.accenture.franchises.application.port.DbPort;
import com.accenture.franchises.application.validator.ProductValidator;
import com.accenture.franchises.domain.exception.FranchiseException;
import com.accenture.franchises.domain.model.Product;
import reactor.core.publisher.Mono;

public record ProductService(ProductValidator productValidator, DbPort dbPort, BranchPort branchPort) {
    public Mono<Product> createProduct(final String nameBranch, final Product product) {
        return productValidator.getBranch(nameBranch)
                .switchIfEmpty(Mono.error(new FranchiseException.BranchServiceException("FR-BS-005")))
                .flatMap(branch -> {
                    Product newProduct = product.toBuilder()
                            .branch(branch)
                            .build();
                    return branchPort.saveProduct(newProduct);
                });
    }

    public Mono<String> deleteProductByName(final String nameProduct) {
        return dbPort.deleteProductByName(nameProduct)
                .thenReturn("Producto eliminado satisfactoriamente");
    }

    public Mono<Product> updateProductQuantity(String nameProduct, Integer total) {
        return dbPort.findProductByName(nameProduct)
                .switchIfEmpty(Mono.error(new FranchiseException.ProductNotFoundException("FR-PD-404")))
                .flatMap(product -> {
                    Product updatedProduct = product.toBuilder()
                            .total(total)
                            .build();
                    return branchPort.saveProduct(updatedProduct);
                });
    }

    public Mono<Product> getProductWithMaxQuantity() {
        return this.branchPort.findProductWithMaxQuantity();
    }

}
