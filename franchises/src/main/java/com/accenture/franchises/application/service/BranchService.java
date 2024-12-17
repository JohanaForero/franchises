package com.accenture.franchises.application.service;

import com.accenture.franchises.application.port.BranchPort;
import com.accenture.franchises.application.port.DbPort;
import com.accenture.franchises.application.validator.BranchValidator;
import com.accenture.franchises.domain.model.Branch;
import reactor.core.publisher.Mono;

public record BranchService(BranchValidator branchValidator, DbPort dbPort, BranchPort branchPort) {
    public Mono<Branch> createBranch(final Integer idFranchise, final String nameBranch) {
        return branchValidator.doesBranchExist(nameBranch)
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.error(new RuntimeException("Ya existe una sucursal con ese nombre"));
                    } else {
                        return branchValidator.getFranchise(idFranchise)
                                .flatMap(franchise -> {
                                    Branch newBranch = new Branch(nameBranch, franchise);
                                    return branchPort.saveBranch(newBranch);
                                })
                                .switchIfEmpty(Mono.error(new RuntimeException("Franquicia no encontrada"))); // Si no se encuentra la franquicia, lanzamos un error
                    }
                });
    }

}
