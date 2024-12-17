package com.accenture.franchises.infraestructure.adapter;

import com.accenture.franchises.application.port.DbPort;
import com.accenture.franchises.domain.exception.FranchiseException;
import com.accenture.franchises.domain.model.Franchise;
import com.accenture.franchises.infraestructure.mapper.FranchiseMapper;
import com.accenture.franchises.infraestructure.repository.entity.FranchiseEntity;
import com.accenture.franchises.infraestructure.repository.persintencerepository.PersistenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MongoDBFranchiseServiceImpl implements DbPort {
    private final FranchiseMapper franchiseMapper;
    private final PersistenceRepository persistenceRepository;

    @Override
    public Mono<Long> findMaxId() {
        return persistenceRepository.findTopByOrderByIdDesc()
                .map(FranchiseEntity::getId)
                .defaultIfEmpty(0L);
    }

    @Override
    public Mono<Boolean> existsByName(String nameFranchise) {
        return persistenceRepository.existsByName(nameFranchise);
    }

    @Override
    public Mono<Franchise> saveFranchise(Franchise franchise) {
        return this.persistenceRepository.save(this.franchiseMapper.toEntity(franchise))
                .flatMap(saveEntity -> Mono.just(this.franchiseMapper.toDomain(saveEntity)))
                .switchIfEmpty(Mono.error(new FranchiseException.ServerExceptionDB("FR-DB-001")));
    }
}
