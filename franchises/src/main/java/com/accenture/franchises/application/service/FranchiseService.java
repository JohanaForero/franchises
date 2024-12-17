package com.accenture.franchises.application.service;

import com.accenture.franchises.application.port.DbPort;
import com.accenture.franchises.application.validator.FranchiseValidator;
import com.accenture.franchises.domain.model.Franchise;
import reactor.core.publisher.Mono;

public record FranchiseService(DbPort dbPort, FranchiseValidator franchiseValidator) {

    public Mono<Franchise> createFranchise(final String nameFranchise) {
        return franchiseValidator.validateUniqueName(nameFranchise)
                .then(Mono.defer(() -> generateNewId()
                        .flatMap(newId -> {
                            final Franchise newFranchise = new Franchise(newId, nameFranchise);
                            return dbPort.saveFranchise(newFranchise);
                        })));
    }

    public Mono<Long> generateNewId() {
        return dbPort.findMaxId()
                .map(maxId -> maxId + 1);
    }
}
