package com.omererken.patikastore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mobile extends Product {
    @Column(name = "battery_power")
    private Integer batteryPower;
    @Column(name = "color")
    private String color;
}

