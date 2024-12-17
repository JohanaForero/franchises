package com.accenture.franchises.infraestructure.adapter;

import com.accenture.franchises.application.port.BranchPort;
import com.accenture.franchises.domain.exception.FranchiseException;
import com.accenture.franchises.domain.model.Branch;
import com.accenture.franchises.infraestructure.mapper.BranchMapper;
import com.accenture.franchises.infraestructure.repository.persintencerepository.BranchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MongoDbBranchServiceImpl implements BranchPort {
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public Mono<Boolean> existsByName(String nameBranch) {
        return branchRepository.existsByName(nameBranch);
    }

    @Override
    public Mono<Branch> saveBranch(Branch branch) {
        return this.branchRepository.save(this.branchMapper.toEntity(branch))
                .flatMap(saveEntity -> Mono.just(this.branchMapper.toDomain(saveEntity)))
                .switchIfEmpty(Mono.error(new FranchiseException.ServerExceptionDB("FR-DB-001")));
    }
}
