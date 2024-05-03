package upc.edu.LoggyAPI.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.products.dto.ProductionLineRequest;
import upc.edu.LoggyAPI.products.dto.ProductionLineResponse;
import upc.edu.LoggyAPI.products.dto.mapper.ProductionLineMapper;
import upc.edu.LoggyAPI.products.service.ProductionLineService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ProductionLineController {
    @Autowired
    private ProductionLineService productionLineService;

    @Transactional
    @GetMapping("/productionLines")
    public ResponseEntity<List<ProductionLineResponse>> getAllProductionLines(){
        var productionLinesResponse = ProductionLineMapper.INSTANCE.productionLineListToProductionLineResponseList(productionLineService.getAllProductionLines());
        return new ResponseEntity<List<ProductionLineResponse>>(productionLinesResponse, HttpStatus.OK);
    }
    @Transactional
    @GetMapping("/productionLines/{id}")
    public ResponseEntity<ProductionLineResponse> getProductionLineById(@PathVariable("id") Long id){
        var productionLineResponse = ProductionLineMapper.INSTANCE.productionLineToProductionLineResponse(productionLineService.getProductionLineById(id));
        return new ResponseEntity<ProductionLineResponse>(productionLineResponse, HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/productionLines")
    public ResponseEntity<ProductionLineResponse> createProductionLine(@RequestBody ProductionLineRequest productionLineRequest){
        var productionLine = ProductionLineMapper.INSTANCE.productionLineRequestToProductionLine(productionLineRequest);
        var createdProductionLine = productionLineService.createProductionLine(productionLine);
        return new ResponseEntity<>(ProductionLineMapper.INSTANCE.productionLineToProductionLineResponse(createdProductionLine),HttpStatus.CREATED);
    }
    @Transactional
    @PutMapping("/productionLines/{id}")
    public ResponseEntity<ProductionLineResponse> updateProductionLine(@PathVariable("id") Long id, @RequestBody ProductionLineRequest productionLineRequest){
        var productionLine = ProductionLineMapper.INSTANCE.productionLineRequestToProductionLine(productionLineRequest);
        var updatedProductionLine = productionLineService.updateProductionLine(id, productionLine);
        return new ResponseEntity<>(ProductionLineMapper.INSTANCE.productionLineToProductionLineResponse(updatedProductionLine),HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/productionLines/{id}")
    public ResponseEntity<Void> deleteProductionLine(@PathVariable("id") Long id){
        productionLineService.deleteProductionLine(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
