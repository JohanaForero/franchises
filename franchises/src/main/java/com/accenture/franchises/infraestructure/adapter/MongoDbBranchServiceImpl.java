package com.accenture.franchises.infraestructure.adapter;

import com.accenture.franchises.application.port.BranchPort;
import com.accenture.franchises.domain.exception.FranchiseException;
import com.accenture.franchises.domain.model.Branch;
import com.accenture.franchises.domain.model.Product;
import com.accenture.franchises.infraestructure.mapper.BranchMapper;
import com.accenture.franchises.infraestructure.mapper.ProductMapper;
import com.accenture.franchises.infraestructure.repository.persintencerepository.BranchRepository;
import com.accenture.franchises.infraestructure.repository.persintencerepository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MongoDbBranchServiceImpl implements BranchPort {
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
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

    @Override
    public Mono<Branch> getBranch(String nameBranch) {
        return this.branchRepository.findByName(nameBranch)
                .switchIfEmpty(Mono.error(new FranchiseException.BranchServiceException("FR-BS-004")))
                .map(branchMapper::toDomain);
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return this.productRepository.save(this.productMapper.toEntity(product))
                .flatMap(saveEntity -> Mono.just(this.productMapper.toModel(saveEntity)))
                .switchIfEmpty(Mono.error(new FranchiseException.ServerExceptionDB("FR-DB-001")));
    }

    @Override
    public Mono<Product> findProductWithMaxQuantity() {
        return productRepository.findProductWithMaxTotal()
                .next()
                .switchIfEmpty(Mono.error(new FranchiseException.ProductNotFoundException("MD-DB-006")))
                .map(productMapper::toModel);
    }

}
