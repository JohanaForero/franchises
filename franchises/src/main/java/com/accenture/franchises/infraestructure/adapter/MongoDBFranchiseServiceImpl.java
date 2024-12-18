package com.accenture.franchises.infraestructure.adapter;

import com.accenture.franchises.application.port.DbPort;
import com.accenture.franchises.domain.exception.FranchiseException;
import com.accenture.franchises.domain.model.Franchise;
import com.accenture.franchises.domain.model.Product;
import com.accenture.franchises.infraestructure.mapper.FranchiseMapper;
import com.accenture.franchises.infraestructure.mapper.ProductMapper;
import com.accenture.franchises.infraestructure.repository.entity.FranchiseEntity;
import com.accenture.franchises.infraestructure.repository.persintencerepository.PersistenceRepository;
import com.accenture.franchises.infraestructure.repository.persintencerepository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MongoDBFranchiseServiceImpl implements DbPort {
    private final FranchiseMapper franchiseMapper;
    private final PersistenceRepository persistenceRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

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

    @Override
    public Mono<Franchise> findByFranchiseId(Long franchiseId) {
        return persistenceRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new RuntimeException("Franquicia no encontrada")))
                .map(franchiseMapper::toDomain);
    }

    @Override
    public Mono<Void> deleteProductByName(String nameProduct) {
        return productRepository.deleteByName(nameProduct)
                .flatMap(count -> {
                    if (count == 0) {
                        return Mono.error(new FranchiseException.ProductNotFoundException("FR-PD-003"));
                    }
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Product> findProductByName(String nameProduct) {
        return this.productRepository.findByName(nameProduct)
                .switchIfEmpty(Mono.error(new FranchiseException.ProductNotFoundException("MD-DB-005")))
                .map(productMapper::toModel);
    }
}
