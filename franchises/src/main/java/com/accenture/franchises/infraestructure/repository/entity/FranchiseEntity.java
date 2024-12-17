package com.accenture.franchises.infraestructure.repository.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@RequiredArgsConstructor
@Document(collection = "franchises")
public class FranchiseEntity {
    @Id
    private Long id;
    private String name;
    private BranchEntity branch;
}
