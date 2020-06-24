package ir.parto.crm.modules.product.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "crm_product_addon")
public class ProductAddon implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productAddonId;
}
