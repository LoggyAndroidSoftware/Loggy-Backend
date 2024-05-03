package upc.edu.LoggyAPI.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.products.model.ProductionLine;

public interface ProductionLineRepository extends JpaRepository<ProductionLine, Long> {
    boolean existsProductionLineByNameIgnoreCase(String name);
}
