package com.accenture.franchises.infraestructure.repository.persintencerepository;

import com.accenture.franchises.infraestructure.repository.entity.FranchiseEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersistenceRepository extends ReactiveMongoRepository<FranchiseEntity, Long> {
    Mono<Boolean> existsByName(String name);

    Mono<FranchiseEntity> findTopByOrderByIdDesc();

    Mono<FranchiseEntity> findById(Long franchiseId);
}
