package com.citysos.api.police.mapping.police;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("policeMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PoliceMapper policeMapper(){ return new PoliceMapper(); }
}
