package com.accenture.franchises.infraestructure.repository.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@RequiredArgsConstructor
@Document(collection = "branch")
public class BranchEntity {
    private Long id;
    private String name;
    private FranchiseEntity idFranchise;
}
