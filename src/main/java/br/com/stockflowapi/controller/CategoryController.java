package br.com.stockflowapi.controller;

import br.com.stockflowapi.dto.CategoryDto;
import br.com.stockflowapi.dto.CategoryRequestDto;
import br.com.stockflowapi.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> save(@RequestBody @Valid CategoryRequestDto categoryDto){
        return ResponseEntity.status(CREATED).body(categoryService.save(categoryDto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll(){
        return ResponseEntity.ok().body(categoryService.findAll());
    }


}
