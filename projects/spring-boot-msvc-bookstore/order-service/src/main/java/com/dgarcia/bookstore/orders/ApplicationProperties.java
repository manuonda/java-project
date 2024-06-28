package com.dgarcia.bookstore.orders;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ConfigurationProperties(prefix = "orders"): Indica que esta clase 
 * tomará las propiedades que empiezan con "orders" en el archivo de configuración.
 */
@ConfigurationProperties(prefix = "orders")
public record ApplicationProperties(
    String catalogServiceUrl,
    String orderEventsExchange,
    String newOrdersQueue,
    String deliveredOrdersQueue,
    String cancelledOrdersQueue,
    String errorOrdersQueue
) {

}
