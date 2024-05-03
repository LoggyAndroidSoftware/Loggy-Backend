package upc.edu.LoggyAPI.products.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.products.dto.ProductionLineRequest;
import upc.edu.LoggyAPI.products.dto.ProductionLineResponse;
import upc.edu.LoggyAPI.products.model.ProductionLine;

import java.util.List;

@Mapper
public interface ProductionLineMapper {
    ProductionLineMapper INSTANCE = Mappers.getMapper(ProductionLineMapper.class);

    List<ProductionLineResponse> productionLineListToProductionLineResponseList(List<ProductionLine> productionLines);
    ProductionLineResponse productionLineToProductionLineResponse(ProductionLine productionLine);
    ProductionLine productionLineRequestToProductionLine(ProductionLineRequest productionLineRequest);
}
