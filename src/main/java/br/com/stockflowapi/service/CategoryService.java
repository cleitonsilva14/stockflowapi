package br.com.stockflowapi.service;

import br.com.stockflowapi.dto.CategoryDto;
import br.com.stockflowapi.dto.CategoryRequestDto;
import br.com.stockflowapi.exception.custom.EntityNotFoundException;
import br.com.stockflowapi.mapper.CategoryMapper;
import br.com.stockflowapi.model.Category;
import br.com.stockflowapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDto save(CategoryRequestDto categoryDto){
        Category category = categoryMapper.toEntity(categoryDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public CategoryDto findById(Long id){
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category id: %d not found!".formatted(id)));

        return categoryMapper.toDto(category);

    }


    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
    }
}
