package upc.edu.LoggyAPI.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.products.dto.BrandRequest;
import upc.edu.LoggyAPI.products.dto.BrandResponse;
import upc.edu.LoggyAPI.products.dto.mapper.BrandMapper;
import upc.edu.LoggyAPI.products.service.BrandService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @Transactional
    @GetMapping("/brands")
    public ResponseEntity<List<BrandResponse>> getAllBrands(){
        var brandsResponse = BrandMapper.INSTANCE.brandListToBrandResponsetList(brandService.getAllBrands());
        return new ResponseEntity<List<BrandResponse>>(brandsResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/brands/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable("id") Long id){
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brandService.getBrandById(id));
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/brands")
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandRequest brandRequest){
        var brand = BrandMapper.INSTANCE.brandRequestToBrand(brandRequest);
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brandService.createBrand(brand));
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/brands/{id}")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable(value = "id") Long brandId, @RequestBody BrandRequest brandRequest){
        var brand = BrandMapper.INSTANCE.brandRequestToBrand(brandRequest);
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brandService.updateBrand(brandId, brand));
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/brands/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable(value = "id") Long brandId){
        brandService.deleteBrand(brandId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
