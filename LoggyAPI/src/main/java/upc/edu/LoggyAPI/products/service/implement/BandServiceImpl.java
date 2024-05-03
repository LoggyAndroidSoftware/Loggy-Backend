package upc.edu.LoggyAPI.products.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.products.model.Brand;
import upc.edu.LoggyAPI.products.repository.BrandRepository;
import upc.edu.LoggyAPI.products.service.BrandService;

import java.util.List;

@Service
public class BandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Override
    public List<Brand> getAllBrands() {
        List<Brand> allBrands = brandRepository.findAll();
        if(allBrands.isEmpty())
        {
            throw new RuntimeException("Unregistered brands");
        }
        return allBrands;
    }

    @Override
    public Brand getBrandById(Long brandId) {
        existsBrandById(brandId);
        return brandRepository.findById(brandId).get();
    }

    @Override
    public Brand createBrand(Brand brand) {
        validateBrand(brand);
        existsBrandByName(brand.getName());
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Long brandId, Brand brandDetails) {
        existsBrandById(brandId);
        validateBrand(brandDetails);
        existsBrandByName(brandDetails.getName());
        Brand brand = brandRepository.findById(brandId).get();
        brand.setName(brandDetails.getName());
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long brandId) {
        existsBrandById(brandId);
        brandRepository.deleteById(brandId);
    }

    private void existsBrandById(Long brandId) {
        if(!brandRepository.existsById(brandId))
        {
            throw new RuntimeException("Brand with id " + brandId + " not found");
        }
    }
    private void existsBrandByName(String name) {
        if(brandRepository.existsByNameIgnoreCase(name))
        {
            throw new RuntimeException("Brand with name " + name + " already exists");
        }
    }
    private void validateBrand(Brand brand) {
        if(brand.getName() == null || brand.getName().isEmpty())
        {
            throw new RuntimeException("Brand name is required");
        }
        if(brand.getName().length() > 100){
            throw new RuntimeException("Name must be less than 100 characters");
        }
    }
}
