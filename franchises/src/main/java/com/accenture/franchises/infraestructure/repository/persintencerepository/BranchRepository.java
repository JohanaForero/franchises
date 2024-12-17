package com.accenture.franchises.infraestructure.repository.persintencerepository;

import com.accenture.franchises.infraestructure.repository.entity.BranchEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BranchRepository extends ReactiveMongoRepository<BranchEntity, Long> {
    Mono<Boolean> existsByName(String nameBranch);

}
