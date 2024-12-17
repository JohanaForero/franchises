package com.accenture.franchises.infraestructure.configuration;

import com.accenture.franchises.application.port.BranchPort;
import com.accenture.franchises.application.port.DbPort;
import com.accenture.franchises.application.service.BranchService;
import com.accenture.franchises.application.service.FranchiseService;
import com.accenture.franchises.application.validator.BranchValidator;
import com.accenture.franchises.application.validator.FranchiseValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FranchiseService franchiseService(DbPort dbPort, FranchiseValidator franchiseValidator) {
        return new FranchiseService(dbPort, franchiseValidator);
    }

    @Bean
    public FranchiseValidator franchiseValidator(DbPort dbPort) {
        return new FranchiseValidator(dbPort);
    }

    @Bean
    public BranchService branchService(BranchValidator branchValidator, DbPort dbPort,   BranchPort branchPort) {
        return new BranchService(branchValidator, dbPort, branchPort);
    }

    @Bean
    public BranchValidator branchValidator(BranchPort branchPort, DbPort dbPort) {
        return new BranchValidator(branchPort, dbPort);
    }
}
