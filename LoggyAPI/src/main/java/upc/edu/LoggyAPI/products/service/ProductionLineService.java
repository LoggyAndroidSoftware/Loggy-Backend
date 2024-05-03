package upc.edu.LoggyAPI.products.service;

import upc.edu.LoggyAPI.products.model.ProductionLine;

import java.util.List;

public interface ProductionLineService {
    List<ProductionLine> getAllProductionLines();
    ProductionLine getProductionLineById(Long id);
    ProductionLine createProductionLine(ProductionLine productionLine);
    ProductionLine updateProductionLine(Long id, ProductionLine productionLine);
    void deleteProductionLine(Long id);
}
