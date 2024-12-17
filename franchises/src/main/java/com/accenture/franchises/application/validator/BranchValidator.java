package com.accenture.franchises.application.validator;

import com.accenture.franchises.application.port.BranchPort;
import com.accenture.franchises.application.port.DbPort;
import com.accenture.franchises.domain.model.Franchise;
import reactor.core.publisher.Mono;

public record BranchValidator(BranchPort branchPort, DbPort dbPort) {
    public Mono<Boolean> doesBranchExist(final String nameBranch) {
        return branchPort.existsByName(nameBranch)
                .map(existingBranch -> true)
                .defaultIfEmpty(false);
    }

    public Mono<Franchise> getFranchise(final long franchiseId) {
        return dbPort.findByFranchiseId(franchiseId);
    }

}
