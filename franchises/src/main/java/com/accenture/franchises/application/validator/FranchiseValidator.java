package com.accenture.franchises.application.validator;

import com.accenture.franchises.application.port.DbPort;
import com.accenture.franchises.domain.exception.FranchiseException;
import reactor.core.publisher.Mono;

public record FranchiseValidator(DbPort dbPort) {
//    public Mono<Void> validateUniqueName(String nameFranchise) {
//        return dbPort.findByName(nameFranchise)
//                .flatMap(existingFranchise -> Mono.error(new FranchiseException.FranchiseAlreadyExistsException("FR-FV-001")))
//                .then();
//    }

    public Mono<Void> validateUniqueName(String nameFranchise) {
        return dbPort.existsByName(nameFranchise)
                .flatMap(exists -> exists
                        ? Mono.error(new FranchiseException.FranchiseAlreadyExistsException("FR-FV-001"))
                        : Mono.empty());
    }
}
