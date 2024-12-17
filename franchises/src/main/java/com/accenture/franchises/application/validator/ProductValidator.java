package com.accenture.franchises.application.validator;

import com.accenture.franchises.application.port.BranchPort;
import com.accenture.franchises.domain.model.Branch;
import reactor.core.publisher.Mono;

public record ProductValidator(BranchPort branchPort) {
    public Mono<Branch> getBranch(final String nameBranch) {
        return branchPort.getBranch(nameBranch);
    }
}
