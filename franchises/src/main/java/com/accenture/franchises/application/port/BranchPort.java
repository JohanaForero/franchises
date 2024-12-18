package com.accenture.franchises.application.port;

import com.accenture.franchises.domain.model.Branch;
import com.accenture.franchises.domain.model.Product;
import reactor.core.publisher.Mono;

public interface BranchPort {
    Mono<Boolean> existsByName(String nameBranch);

    Mono<Branch> saveBranch(Branch branch);

    Mono<Branch> getBranch(String nameBranch);

    Mono<Product> saveProduct(Product product);

    Mono<Product> findProductWithMaxQuantity();
}
