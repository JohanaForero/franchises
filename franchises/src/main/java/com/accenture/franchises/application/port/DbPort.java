package com.accenture.franchises.application.port;

import com.accenture.franchises.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface DbPort {

    Mono<Long> findMaxId();

    Mono<Boolean> existsByName(String nameFranchise);

    Mono<Franchise> saveFranchise(Franchise franchise);
}
