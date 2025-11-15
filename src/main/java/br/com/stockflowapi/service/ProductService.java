package br.com.stockflowapi.service;

import br.com.stockflowapi.dto.ProductDto;
import br.com.stockflowapi.exception.custom.EntityNotFoundException;
import br.com.stockflowapi.exception.custom.ProductCodeUniqueViolation;
import br.com.stockflowapi.mapper.ProductMapper;
import br.com.stockflowapi.model.Product;
import br.com.stockflowapi.projection.ProductCodeProjection;
import br.com.stockflowapi.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Transactional
    public ProductDto save(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);

        log.info("{}", product.getImages());

        try {
            productRepository.save(product);
            return productMapper.toDto(product);
        }catch (ProductCodeUniqueViolation exception){
            throw new ProductCodeUniqueViolation("Product code %d already exists!".formatted(product.getCode()));
        }

    }

    @Transactional(readOnly = true)
    public ProductDto findByUUID(UUID uuid){
        Product product = productRepository
                .findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Product uuid: [%s] not found!".formatted(uuid)));
        return productMapper.toDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        return productRepository
                .findAll()
                .stream()
                .map(productMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ProductDto findByCode(Long code) {
        Product product = productRepository
                .findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Product code %d not found!".formatted(code)));
        return productMapper.toDto(product);

    }

    @Transactional(readOnly = true)
    public List<ProductCodeProjection> findAllCodes() {
        return productRepository.findAllCode();
    }

    @Transactional
    public List<ProductDto> saveAll(@Valid List<ProductDto> productsDto) {

        List<Product> products = productsDto
                .stream()
                .map(productMapper::toEntity)
                .collect(Collectors.toList());

        List<Product> productSaved = productRepository.saveAll(products);
        return productSaved.stream().map(productMapper::toDto).toList();
    }

    public ProductDto update(Long code, ProductDto productDto) {
        Product product = productRepository.findByCode(code).orElseThrow(() -> new EntityNotFoundException("Product code %d not found!".formatted(code)));

        log.info("\n\n{}", product.toString());

        product.setCode(product.getCode());
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());

        log.info("\n\n{}", product.toString());

        return productMapper.toDto(productRepository.save(product));
    }

    public void delete(Long code) {
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Product with code %d not found!".formatted(code)
                ));

        productRepository.delete(product);
    }
}
