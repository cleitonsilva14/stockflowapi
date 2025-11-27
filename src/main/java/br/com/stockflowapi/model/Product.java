package br.com.stockflowapi.model;

import br.com.stockflowapi.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_product")
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "code", nullable = false, unique = true)
    private Long code;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;


    @ElementCollection//(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_product_images", joinColumns = @JoinColumn(name = "product_id"))
    private List<String> images = new ArrayList<>();


    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "category_id"
    )
    private Category category;

    @PrePersist
    private void prePersist(){
        if(Objects.isNull(price)){
            price = BigDecimal.ZERO;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "uuid=" + uuid +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
