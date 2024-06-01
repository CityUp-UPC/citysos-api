package com.citysos.api.police.mapping.news;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("newsMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public NewMapper newMapper(){ return new NewMapper(); }
}
