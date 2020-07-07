package ir.parto.crm.modules.product.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_product_cycle_price")
public class ProductCyclePrice implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    private Long productCyclePriceId;

    @ManyToOne
    @JoinColumn(name = "cycle_id", foreignKey = @ForeignKey(name = "product_cycle_price_product_cycle_fk"))
    private ProductCycle productCycle;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "product_cycle_price_product_fk"))
    private Product product;


    @ManyToOne
    @JoinColumn(name = "product_addon_id", foreignKey = @ForeignKey(name = "product_cycle_price_product_addon_fk"))
    private ProductAddon productAddon;

    @Column(name = "setup_price", columnDefinition = "number(16)")
    private Long setupPrice;

    @Column(name = "price", columnDefinition = "number(16)")
    private Long price;


    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "TIMESTAMP(6)")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_at", columnDefinition = "TIMESTAMP(6)")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime deletedDate;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private Integer isDeleted;

    public ProductCyclePrice() {
    }

    public ProductCyclePrice(ProductCycle productCycle, Product product, ProductAddon productAddon, Long setupPrice, Long price, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.productCycle = productCycle;
        this.product = product;
        this.productAddon = productAddon;
        this.setupPrice = setupPrice;
        this.price = price;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getProductCyclePriceId() {
        return productCyclePriceId;
    }

    public void setProductCyclePriceId(Long productCyclePriceId) {
        this.productCyclePriceId = productCyclePriceId;
    }

    public ProductCycle getProductCycle() {
        return productCycle;
    }

    public void setProductCycle(ProductCycle productCycle) {
        this.productCycle = productCycle;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductAddon getProductAddon() {
        return productAddon;
    }

    public void setProductAddon(ProductAddon productAddon) {
        this.productAddon = productAddon;
    }

    public Long getSetupPrice() {
        return setupPrice;
    }

    public void setSetupPrice(Long setupPrice) {
        this.setupPrice = setupPrice;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
