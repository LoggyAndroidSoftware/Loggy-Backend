package upc.edu.LoggyAPI.products.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.products.dto.BrandRequest;
import upc.edu.LoggyAPI.products.dto.BrandResponse;
import upc.edu.LoggyAPI.products.model.Brand;
import upc.edu.LoggyAPI.user.dto.UserRequest;
import upc.edu.LoggyAPI.user.model.User;

import java.util.List;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
    Brand brandRequestToBrand(BrandRequest brandRequest);
    BrandResponse brandToBrandResponse(Brand brand);
    List<BrandResponse> brandListToBrandResponsetList(List<Brand> brands);
}
