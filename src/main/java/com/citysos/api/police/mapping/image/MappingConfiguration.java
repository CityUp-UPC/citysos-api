package com.citysos.api.police.mapping.image;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("imageMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ImageMapper imageMapper(){return new ImageMapper();}
}
