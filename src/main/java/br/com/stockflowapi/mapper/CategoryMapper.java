package br.com.stockflowapi.mapper;

import br.com.stockflowapi.dto.CategoryDto;
import br.com.stockflowapi.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
}
