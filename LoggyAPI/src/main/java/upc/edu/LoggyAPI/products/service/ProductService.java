package upc.edu.LoggyAPI.products.service;

import upc.edu.LoggyAPI.products.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByBrandId(Long brand_id);
    List<Product> getProductsByProductionLineId(Long productionLine_id);
    List<Product> getProductsByBrandIdAndProductionLineId(Long brand_id, Long productionLine_id);
    Product createProduct(Long product_line_id, Long id_brand, Product product);
    Product updateProduct(Long id_product, Long brand_id, Long product_line_id,Product product);
    void deleteProduct(Long id);
}
