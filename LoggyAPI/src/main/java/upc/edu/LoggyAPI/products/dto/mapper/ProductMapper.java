package upc.edu.LoggyAPI.products.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.products.dto.ProductRequest;
import upc.edu.LoggyAPI.products.dto.ProductResponse;
import upc.edu.LoggyAPI.products.model.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Product productRequestToProduct(ProductRequest productRequest);
    ProductResponse productToProductResponse(Product product);
    List<ProductResponse> productListToProductResponseList(List<Product> products);
}
