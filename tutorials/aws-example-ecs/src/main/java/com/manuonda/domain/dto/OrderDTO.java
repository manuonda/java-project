package com.manuonda.domain.dto;

public record OrderDTO( Long id, String orderNumber, String status, int qty, double mount) {
}
