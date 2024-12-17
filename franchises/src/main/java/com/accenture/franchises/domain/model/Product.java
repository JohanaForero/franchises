package com.accenture.franchises.domain.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record Product(String name, Integer total, Branch branch) {
}
