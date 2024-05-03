package upc.edu.LoggyAPI.products.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.products.model.ProductionLine;
import upc.edu.LoggyAPI.products.repository.ProductionLineRepository;
import upc.edu.LoggyAPI.products.service.ProductionLineService;

import java.util.List;

@Service
public class ProductionLineServiceImpl implements ProductionLineService {

    @Autowired
    private ProductionLineRepository productionLineRepository;
    @Override
    public List<ProductionLine> getAllProductionLines() {
        List<ProductionLine> productionLines = productionLineRepository.findAll();
        if(productionLines.isEmpty()){
            throw new RuntimeException("No production lines found");
        }
        return productionLines;
    }

    @Override
    public ProductionLine getProductionLineById(Long id) {
        existProductionLineById(id);
        return productionLineRepository.findById(id).get();
    }

    @Override
    public ProductionLine createProductionLine(ProductionLine productionLine) {
        validateProductionLine(productionLine);
        existProductionLineByName(productionLine.getName());
        return productionLineRepository.save(productionLine);
    }

    @Override
    public ProductionLine updateProductionLine(Long id, ProductionLine productionLine) {
        existProductionLineById(id);
        validateProductionLine(productionLine);
        existProductionLineByName(productionLine.getName());
        ProductionLine productionLineToUpdate = productionLineRepository.findById(id).get();
        productionLineToUpdate.setName(productionLine.getName());
        return productionLineRepository.save(productionLineToUpdate);
    }

    @Override
    public void deleteProductionLine(Long id) {
        existProductionLineById(id);
        productionLineRepository.deleteById(id);
    }

    private void existProductionLineById(Long id){
        if(!productionLineRepository.existsById(id)){
            throw new RuntimeException("Production line by id  " + id + "  not found");
        }
    }
    private void existProductionLineByName(String name){
        if(productionLineRepository.existsProductionLineByNameIgnoreCase(name)){
            throw new RuntimeException("Production line by name  " + name + "  already exists");
        }
    }
    private void validateProductionLine(ProductionLine productionLine){
        if(productionLine.getName() == null || productionLine.getName().isEmpty()){
            throw new RuntimeException("Production line name is required");
        }
    }
}
