package com.accenture.franchises.domain.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record Franchise(Long id, String name, Branch branch) {
}
