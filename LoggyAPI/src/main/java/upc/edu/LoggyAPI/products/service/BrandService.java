package upc.edu.LoggyAPI.products.service;

import upc.edu.LoggyAPI.products.model.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrands();
    Brand getBrandById(Long brandId);
    Brand createBrand(Brand brand);
    Brand updateBrand(Long brandId, Brand brandDetails);
    void deleteBrand(Long brandId);
}
