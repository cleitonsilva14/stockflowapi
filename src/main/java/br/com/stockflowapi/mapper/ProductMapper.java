package br.com.stockflowapi.mapper;

import br.com.stockflowapi.dto.ProductDto;
import br.com.stockflowapi.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
}
