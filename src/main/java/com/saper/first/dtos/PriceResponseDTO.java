package com.saper.first.dtos;

public class PriceResponseDTO {
    public double motorcycle;
    public double car;

    public PriceResponseDTO(double motorcycle, double car) {
        this.motorcycle = motorcycle;
        this.car = car;
    }
}
