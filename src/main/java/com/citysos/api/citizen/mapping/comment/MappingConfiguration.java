package com.citysos.api.citizen.mapping.comment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("commentMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public CommentMapper commentMapper(){ return new CommentMapper(); }
}
