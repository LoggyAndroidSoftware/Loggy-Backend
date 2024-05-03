package upc.edu.LoggyAPI.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.products.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByNameIgnoreCase(String name);
}
