package ir.parto.crm.modules.product.model.entity;

import ir.parto.crm.modules.product.controller.transientObject.productParameterGroup.ProductParameterGroupDTO;
import ir.parto.crm.modules.product.controller.transientObject.productParameterGroup.ProductParameterGroupInfoDTO;
import ir.parto.crm.modules.product.controller.transientObject.productParameterGroup.ProductParameterGroupRelationalDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_product_parameter_group")
public class ProductParameterGroup implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    private Long productParameterGroupId;

    @Column(name = "title", columnDefinition = "nvarchar2(100)")
    private String title;


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

    public ProductParameterGroup() {
    }

    public ProductParameterGroup(String title, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.title = title;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public ProductParameterGroup(Long productParameterGroupId) {
        this.productParameterGroupId = productParameterGroupId;
    }

    public Long getProductParameterGroupId() {
        return productParameterGroupId;
    }

    public void setProductParameterGroupId(Long productParameterGroupId) {
        this.productParameterGroupId = productParameterGroupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public ProductParameterGroupDTO convert2Object() {
        ProductParameterGroupDTO dto = new ProductParameterGroupDTO();
        if (this.productParameterGroupId != null) dto.setProductParameterGroupId(this.productParameterGroupId);
        if (this.title != null) dto.setTitle(this.title);
        return dto;
    }

    public ProductParameterGroupInfoDTO convert2InfoObject() {
        ProductParameterGroupInfoDTO dto = new ProductParameterGroupInfoDTO();
        if (this.productParameterGroupId != null) dto.setProductParameterGroupId(this.productParameterGroupId);
        if (this.title != null) dto.setTitle(this.title);
        if (this.createdBy != null) dto.setCreatedBy(this.createdBy);
        if (this.updatedBy != null) dto.setUpdatedBy(this.updatedBy);
        if (this.deletedBy != null) dto.setDeletedBy(this.deletedBy);
        if (this.createdDate != null) dto.setCreatedDate(this.createdDate);
        if (this.updatedDate != null) dto.setUpdatedDate(this.updatedDate);
        if (this.deletedDate != null) dto.setDeletedDate(this.deletedDate);
        if (this.isDeleted != null) dto.setIsDeleted(this.isDeleted);
        return dto;
    }

    public ProductParameterGroupRelationalDTO convert2RelationalDTO() {
        ProductParameterGroupRelationalDTO dto = new ProductParameterGroupRelationalDTO();
        if (this.productParameterGroupId != null) dto.setProductParameterGroupId(this.productParameterGroupId);
        if (this.title != null) dto.setTitle(this.title);
        return dto;
    }
}
