package upc.edu.LoggyAPI.products.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String image;
    private Double weight;
}
