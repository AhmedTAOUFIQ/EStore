package com.example.filrougefo.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Table(name = "OrderLines")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "idOrder")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;
    private BigDecimal quantity;
    private BigDecimal discount;

    public OrderLine(Order order, Product product, long quantity) {
        this.order = order;
        this.product = product;
        this.quantity = BigDecimal.valueOf(quantity);
    }
}
