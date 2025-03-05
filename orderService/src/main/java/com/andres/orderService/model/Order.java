package com.andres.orderService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String userId;

    @NotNull
    private Long productId;

    @NotNull
    private int quantity;

    @NotNull
    private BigDecimal totalPrice;

    private LocalDateTime createdAt = LocalDateTime.now();
}
