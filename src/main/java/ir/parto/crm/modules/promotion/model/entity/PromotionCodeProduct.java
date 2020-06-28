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
    @SequenceGenerator(name = "promotion_seq", sequenceName = "promotion_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "promotion_seq")
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

    @Column(name = "create_at", updatable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_at", columnDefinition = "datetime")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "datetime")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private LocalDateTime isDeleted;

    public PromotionCodeProduct() {
    }

    public PromotionCodeProduct(Product product, PromotionCode promotionCode, String createdBy, LocalDateTime createdDate) {
        this.product = product;
        this.promotionCode = promotionCode;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Long getPromotionId() {
        return promotionCodeProductId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionCodeProductId = promotionId;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
