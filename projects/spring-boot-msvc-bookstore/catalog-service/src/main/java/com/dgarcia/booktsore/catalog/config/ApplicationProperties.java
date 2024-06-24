package com.dgarcia.booktsore.catalog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.validation.constraints.Min;


/**
 * Configuration properties for package catalog
 */
@ConfigurationProperties(prefix="catalog")
public record ApplicationProperties(
    @DefaultValue("10")
    @Min(1)
    int pageSize
) {

}
