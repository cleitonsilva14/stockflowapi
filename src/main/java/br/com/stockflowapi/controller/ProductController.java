package br.com.stockflowapi.controller;

import br.com.stockflowapi.dto.ProductDto;
import br.com.stockflowapi.projection.ProductCodeProjection;
import br.com.stockflowapi.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductDto productDto){
        log.info("{}", productDto.images());
        return ResponseEntity.ok().body(productService.save(productDto));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<ProductDto>> saveAll(@Valid @RequestBody List<ProductDto> products){
        return ResponseEntity.ok().body(productService.saveAll(products));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(){
        return ResponseEntity.ok().body(productService.findAll());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<ProductDto> findByCode(@PathVariable(name = "code") Long code){
        return ResponseEntity.ok().body(productService.findByCode(code));
    }

    // @GetMapping("/{code:\\d+}")
    @GetMapping("/code")
    public ResponseEntity<List<ProductCodeProjection>> getCode(){
        return ResponseEntity.ok().body(productService.findAllCodes());
    }

    // @GetMapping("/{uuid:[0-9a-fA-F\\-]{36}}")
    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<ProductDto> findByUUID(@PathVariable(name = "uuid") UUID uuid){
        return ResponseEntity.ok().body(productService.findByUUID(uuid));
    }


    @PutMapping("/code/{code}")
    public ResponseEntity<ProductDto>  update(@PathVariable Long code, @Valid @RequestBody ProductDto productDto){
        //productService.update(code, productDto);
        return ResponseEntity.ok().body(productService.update(code, productDto));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable Long code){
        productService.delete(code);
        return ResponseEntity.noContent().build();
    }

}
