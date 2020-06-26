package ir.parto.crm.modules.product.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "crm_product_addons_link")
public class ProductAddonsLink implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    private Long productAddonLinkId;

    @ManyToOne
    @JoinColumn(name = "product_group_id", foreignKey = @ForeignKey(name = "product_addons_link_product_addon_fk"))
    private ProductAddon productAddon;

    @ManyToOne
    @JoinColumn(name = "product_group_id", foreignKey = @ForeignKey(name = "product_addons_link_product_fk"))
    private Product product;

    public ProductAddonsLink() {
    }

    public ProductAddonsLink(ProductAddon productAddon, Product product) {
        this.productAddon = productAddon;
        this.product = product;
    }

    public Long getProductAddonLinkId() {
        return productAddonLinkId;
    }

    public void setProductAddonLinkId(Long productAddonLinkId) {
        this.productAddonLinkId = productAddonLinkId;
    }

    public ProductAddon getProductAddon() {
        return productAddon;
    }

    public void setProductAddon(ProductAddon productAddon) {
        this.productAddon = productAddon;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
