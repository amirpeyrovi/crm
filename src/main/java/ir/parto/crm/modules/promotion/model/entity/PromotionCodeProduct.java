package ir.parto.crm.modules.promotion.model.entity;

import ir.parto.crm.modules.product.model.entity.Product;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_promotion_code_product")
public class PromotionCodeProduct implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "crm_promotion_seq", sequenceName = "crm_promotion_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "crm_promotion_seq")
    private Long promotionCodeProductId;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "promotion_code_product_product_fk"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "promotion_code_id", foreignKey = @ForeignKey(name = "promotion_code_product_promotion_code_fk"))
    private PromotionCode promotionCode;

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

    public PromotionCodeProduct() {
    }

    public PromotionCodeProduct(Product product, PromotionCode promotionCode, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.product = product;
        this.promotionCode = promotionCode;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getPromotionCodeProductId() {
        return promotionCodeProductId;
    }

    public void setPromotionCodeProductId(Long promotionCodeProductId) {
        this.promotionCodeProductId = promotionCodeProductId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PromotionCode getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(PromotionCode promotionCode) {
        this.promotionCode = promotionCode;
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
