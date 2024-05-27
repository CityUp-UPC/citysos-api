package com.citysos.api.citizen.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("citizenMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public CitizenMapper citizenMapper(){ return new CitizenMapper(); }
}
