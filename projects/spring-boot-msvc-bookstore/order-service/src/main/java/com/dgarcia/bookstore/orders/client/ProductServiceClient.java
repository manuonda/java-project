package com.dgarcia.bookstore.orders.client;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

/**
 * Componente que interactúa con un servicio REST para obtener información de productos.
 * Utiliza un RestClient para realizar llamadas al servicio externo.
 * Incluye mecanismos de Circuit Breaker y Retry para mejorar la resiliencia del sistema
 * ante fallos temporales en la comunicación con el servicio.
 */


@Component
public class ProductServiceClient {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceClient.class);

    private final RestClient restClient;

    ProductServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @CircuitBreaker(name = "catalog-service")
    @Retry(name = "catalog-service", fallbackMethod = "getProductByCodeFallback")
    public Optional<Product> getProductByCode(String code) {
        log.info("Fetching product for code: {}", code);
        var product =
                restClient.get()
                .uri("/api/v1/products/code/{code}", code)
                .retrieve()
                .body(Product.class);
        return Optional.ofNullable(product);
    }

    Optional<Product> getProductByCodeFallback(String code, Throwable t) {
        log.info("catalog-service get product by code fallback: code:{}, Error: {} ", code, t.getMessage());
        return Optional.empty();
    }
    
   
}
