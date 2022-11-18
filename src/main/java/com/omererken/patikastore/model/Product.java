package com.omererken.patikastore.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount_rate")
    private Integer discountRate;

    @Column(name = "amount_stock")
    private Integer amountOfStock;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "screen_size")
    private Double screenSize;

    @Column(name = "memory")
    private Integer memory;

    @Column(name = "storage")
    private Integer storage;

}
