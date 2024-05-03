package upc.edu.LoggyAPI.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.products.dto.ProductRequest;
import upc.edu.LoggyAPI.products.dto.ProductResponse;
import upc.edu.LoggyAPI.products.dto.mapper.ProductMapper;
import upc.edu.LoggyAPI.products.service.BrandService;
import upc.edu.LoggyAPI.products.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;

    @Transactional
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        var productsResponse = ProductMapper.INSTANCE.productListToProductResponseList(productService.getAllProducts());
        return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id){
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(productService.getProductById(id));
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/brands/{brandId}/products")
    public ResponseEntity<List<ProductResponse>> getProductsByBrandId(@PathVariable("brandId") Long brandId){
        var productsResponse = ProductMapper.INSTANCE.productListToProductResponseList(productService.getProductsByBrandId(brandId));
        return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/productionLines/{productionLineId}/products")
    public ResponseEntity<List<ProductResponse>> getProductsByProductionLineId(@PathVariable("productionLineId") Long productionLineId){
        var productsResponse = ProductMapper.INSTANCE.productListToProductResponseList(productService.getProductsByProductionLineId(productionLineId));
        return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/productionLines/{productionLineId}/brands/{brandId}/products")
    public ResponseEntity<List<ProductResponse>> getProductsByBrandIdAndProductionLineId(@PathVariable("brandId") Long brandId, @PathVariable("productionLineId") Long productionLineId){
        var productsResponse = ProductMapper.INSTANCE.productListToProductResponseList(productService.getProductsByBrandIdAndProductionLineId(brandId, productionLineId));
        return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/productionLines/{productionLineId}/brands/{brandId}/products")
    public ResponseEntity<ProductResponse> createProduct(@PathVariable("productionLineId") Long productionLineId, @PathVariable("brandId") Long brandId ,@RequestBody ProductRequest productRequest){
        var product = ProductMapper.INSTANCE.productRequestToProduct(productRequest);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(productService.createProduct(brandId, productionLineId, product));
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/productionLines/{productionLineId}/brands/{brandId}/products/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("productId") Long productId, @PathVariable("brandId") Long brandId, @PathVariable("productionLineId") Long product_line_id,@RequestBody ProductRequest productRequest){
        var product = ProductMapper.INSTANCE.productRequestToProduct(productRequest);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(productService.updateProduct(productId, brandId, product_line_id, product));
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
