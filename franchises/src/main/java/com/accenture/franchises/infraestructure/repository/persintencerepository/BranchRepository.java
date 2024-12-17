package com.accenture.franchises.infraestructure.repository.persintencerepository;

import com.accenture.franchises.infraestructure.repository.entity.BranchEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BranchRepository extends ReactiveMongoRepository<BranchEntity, ObjectId> {
    Mono<Boolean> existsByName(String nameBranch);

    Mono<BranchEntity> findByName(String nameBranch);
}
