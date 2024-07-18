package com.dgarcia.bookstore.notifications.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.logging.log4j.spi.LocationAwareLogger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="order_events")
public class OrderEventEntity {
  
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator ="order_event_id_generator")
    @SequenceGenerator(name="order_event_id_generator" , sequenceName = "order_event_id_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    private String eventId;

    @Column(name="created_at", nullable =  false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name="updated_at", nullable =  true, updatable =  false)
    private LocalDateTime updatedAt;

}
