package com.app.dto;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;
    private double price;
    private Long categoryId;;
}