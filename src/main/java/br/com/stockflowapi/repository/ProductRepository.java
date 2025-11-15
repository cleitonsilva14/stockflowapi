package br.com.stockflowapi.repository;

import br.com.stockflowapi.model.Product;
import br.com.stockflowapi.projection.ProductCodeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findByCode(Long code);

    @Query("SELECT p.code AS code FROM Product p")
    List<ProductCodeProjection> findAllCode();
}