package br.com.stockflowapi.mapper;

import br.com.stockflowapi.dto.ProductDto;
import br.com.stockflowapi.dto.ProductResponseDto;
import br.com.stockflowapi.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // o campo DTO categoryId deve receber o valor de product.category.id
    // source: É o campo de onde os dados vêm, no objeto de entrada (Product).
    // Pegue o atributo id dentro do objeto category do Product. Ou seja, equivalente a: product.getCategory().getId()
    // target: É o campo para onde os dados vão, no objeto de saída (ProductDto):
    // Ou seja: Coloque esse valor (category.id) no campo categoryId do DTO.


    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    @Mapping(source = "category", target = "category")
    ProductResponseDto toResponseDto(Product product);

    Product toEntity(ProductDto productDto);


}
