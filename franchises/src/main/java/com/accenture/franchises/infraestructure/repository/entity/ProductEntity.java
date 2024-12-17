package com.accenture.franchises.infraestructure.repository.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@RequiredArgsConstructor
@Document(collection = "product")
public class ProductEntity {
    @Id
    private ObjectId id;
    private String name;
    private Long total;
    private BranchEntity branch;
}
