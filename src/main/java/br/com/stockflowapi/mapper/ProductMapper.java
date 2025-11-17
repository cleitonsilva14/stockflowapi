package br.com.stockflowapi.mapper;

import br.com.stockflowapi.dto.ProductDto;
import br.com.stockflowapi.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);
}
