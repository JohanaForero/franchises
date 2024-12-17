package com.accenture.franchises.infraestructure.repository.persintencerepository;

import com.accenture.franchises.infraestructure.repository.entity.ProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, Long> {
}
