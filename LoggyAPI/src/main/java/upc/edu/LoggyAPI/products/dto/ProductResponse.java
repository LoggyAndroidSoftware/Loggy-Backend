package upc.edu.LoggyAPI.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import upc.edu.LoggyAPI.products.model.Brand;
import upc.edu.LoggyAPI.products.model.ProductionLine;

@Data
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String image;
    private Double weight;
    @JsonProperty("brand")
    private Brand brand;
    @JsonProperty("production_line")
    private ProductionLine productionLine;
}
