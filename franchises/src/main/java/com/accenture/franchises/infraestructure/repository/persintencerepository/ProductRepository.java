package com.accenture.franchises.infraestructure.repository.persintencerepository;

import com.accenture.franchises.infraestructure.repository.entity.ProductEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, Long> {
    Mono<Long> deleteByName(String nameProduct);

    Mono<ProductEntity> findByName(String nameProduct);

    @Aggregation(pipeline = {"{ $sort: { total: -1 } }", "{ $limit: 1 }"})
    Flux<ProductEntity> findProductWithMaxTotal();
}
