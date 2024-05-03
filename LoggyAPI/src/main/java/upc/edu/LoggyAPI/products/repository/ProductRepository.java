package upc.edu.LoggyAPI.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.products.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBrandId(Long brand_id);
    List<Product> findByProductionLineId(Long productionLine_id);
    List<Product> findByBrandIdAndProductionLineId(Long brand_id, Long productionLine_id);
    boolean existsProductByNameIgnoreCase(String name);
}
