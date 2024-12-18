package com.accenture.franchises.application.port;

import com.accenture.franchises.domain.model.Branch;
import com.accenture.franchises.domain.model.Franchise;
import com.accenture.franchises.domain.model.Product;
import reactor.core.publisher.Mono;

public interface DbPort {

    Mono<Long> findMaxId();

    Mono<Boolean> existsByName(String nameFranchise);

    Mono<Franchise> saveFranchise(Franchise franchise);

    Mono<Franchise> findByFranchiseId(Long idFranchise);

    Mono<Void> deleteProductByName(String nameProduct);

    Mono<Product> findProductByName(String nameProduct);

}
