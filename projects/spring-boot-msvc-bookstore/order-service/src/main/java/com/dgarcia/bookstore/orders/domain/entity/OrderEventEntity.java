package com.dgarcia.bookstore.orders.domain.entity;

import java.time.LocalDateTime;

import com.dgarcia.bookstore.orders.domain.models.OrderEventType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="order_events")
public class OrderEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="order_event_id_generator")
    @SequenceGenerator(name="order_event_id_generator", sequenceName = "order_event_id_seq")
    private Long id;

    @Column(nullable=false)
    private String orderNumber;


    @Column(nullable = false, unique = true)
    private String eventId;

    @Enumerated(EnumType.STRING)
    private OrderEventType eventType;

    @Column(nullable = false)
    private String payload;

    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name="updated_at")
    private LocalDateTime updatedAt;


}
