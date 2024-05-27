package com.citysos.api.incident.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("incidentMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public IncidentMapper incidentMapper(){ return new IncidentMapper(); }
}
