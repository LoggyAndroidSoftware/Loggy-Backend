package upc.edu.LoggyAPI.products.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.products.model.Product;
import upc.edu.LoggyAPI.products.repository.BrandRepository;
import upc.edu.LoggyAPI.products.repository.ProductRepository;
import upc.edu.LoggyAPI.products.repository.ProductionLineRepository;
import upc.edu.LoggyAPI.products.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ProductionLineRepository productionLineRepository;
    @Override
    public Product getProductById(Long id) {
        existProductById(id);
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        if(allProducts.isEmpty())
        {
            throw new RuntimeException("Unregistered products");
        }
        return allProducts;
    }

    @Override
    public List<Product> getProductsByBrandId(Long brand_id) {
        existBrandId(brand_id);
        List<Product> products = productRepository.findByBrandId(brand_id);
        if(products.isEmpty())
        {
            throw new RuntimeException("Products not found for brand with id " + brand_id);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByProductionLineId(Long productionLine_id) {
        existProductById(productionLine_id);
        List<Product> products = productRepository.findByProductionLineId(productionLine_id);
        if(products.isEmpty())
        {
            throw new RuntimeException("Products not found for production line with id " + productionLine_id);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByBrandIdAndProductionLineId(Long brand_id, Long productionLine_id) {
        existBrandId(brand_id);
        existProductionLineById(productionLine_id);
        List<Product> products = productRepository.findByBrandIdAndProductionLineId(brand_id, productionLine_id);
        if(products.isEmpty())
        {
            throw new RuntimeException("Products not found for brand with id " + brand_id + " and production line with id " + productionLine_id);
        }
        return products;
    }

    @Override
    public Product createProduct(Long id_product_line, Long id_brand, Product product) {
        existBrandId(id_brand);
        existProductionLineById(id_product_line);
        validateProduct(product);
        existProductByProductName(product.getName());
        product.setProductionLine(productionLineRepository.findById(id_product_line).get());
        product.setBrand(brandRepository.findById(id_brand).get());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id_product, Long brand_id, Long product_line_id,Product product) {
        existProductById(id_product);
        existBrandId(brand_id);
        existProductionLineById(product_line_id);
        validateProduct(product);
        existProductByProductName(product.getName());
        Product existingProduct = productRepository.findById(id_product).get();
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setWeight(product.getWeight());
        existingProduct.setBrand(brandRepository.findById(brand_id).get());
        existingProduct.setProductionLine(productionLineRepository.findById(product_line_id).get());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        existProductById(id);
        productRepository.deleteById(id);
    }
    private void existProductionLineById(Long id) {
        if (!productionLineRepository.existsById(id)) {
            throw new RuntimeException("Production line with id " + id + " not found");
        }
    }
    private void existProductById(Long id) {
        if(!productRepository.existsById(id))
        {
            throw new RuntimeException("Product with id " + id + " not found");
        }
    }
    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new RuntimeException("Product name is required");
        }
        if (product.getName().length() > 100) {
            throw new RuntimeException("Product name must not exceed 100 characters");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new RuntimeException("Product description is required");
        }
        if (product.getDescription().length() > 500) {
            throw new RuntimeException("Product description must not exceed 500 characters");
        }
        if (product.getPrice() == null) {
            throw new RuntimeException("Product price is required");
        }
        if (product.getPrice() < 0) {
            throw new RuntimeException("Product price cannot be negative");
        }
        if (product.getStock() == null) {
            throw new RuntimeException("Product stock is required");
        }
        if (product.getStock() < 0) {
            throw new RuntimeException("Product stock cannot be negative");
        }
        if(product.getWeight() == null){
            throw new RuntimeException("Product weight is required");
        }
        if(product.getWeight() < 0){
              throw new RuntimeException("Product weight cannot be negative");
        }
    }
    private void existProductByProductName(String name) {
        if (productRepository.existsProductByNameIgnoreCase(name)) {
            throw new RuntimeException("Product " + name + " already exists");
        }
    }
    private void existBrandId(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new RuntimeException("Brand with id " + id + " not found");
        }
    }

}
