package com.accenture.franchises.application.port;

import com.accenture.franchises.domain.model.Branch;
import reactor.core.publisher.Mono;

public interface BranchPort {
    Mono<Boolean> existsByName(String nameBranch);

    Mono<Branch> saveBranch(Branch branch);
}
