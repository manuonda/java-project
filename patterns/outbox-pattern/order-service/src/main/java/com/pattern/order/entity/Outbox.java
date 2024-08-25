package com.pattern.order.entity;

import java.sql.Date;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is the outobx pattern
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OUTBOX_TBL")
@Builder
public class Outbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aggregateId;
    private String payload;
    private java.util.Date createdAt;
    private Boolean processed;


}
