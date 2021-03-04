package ir.parto.crm.modules.reseller.model.entity;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.reseller.controller.transientObject.resellerCommission.ResellerCommissionDTO;
import ir.parto.crm.modules.reseller.controller.transientObject.resellerCommission.ResellerCommissionInfoDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_reseller_commission")
public class ResellerCommission implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "reseller_seq", sequenceName = "reseller_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "reseller_seq")
    private Long resellerCommissionId;

    @ManyToOne
    @JoinColumn(name = "reseller_id", foreignKey = @ForeignKey(name = "reseller_commission_reseller_fk"))
    private Reseller reseller;

    @ManyToOne
    @JoinColumn(name = "product_group_id", foreignKey = @ForeignKey(name = "reseller_commission_product_group_fk"))
    private ProductGroup productGroup;

    @Column(name = "percentage", columnDefinition = "number(3)")
    private Integer percentage;


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

    public ResellerCommission() {
    }

    public ResellerCommission(Reseller reseller, ProductGroup productGroup, Integer percentage, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.reseller = reseller;
        this.productGroup = productGroup;
        this.percentage = percentage;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getResellerCommissionId() {
        return resellerCommissionId;
    }

    public void setResellerCommissionId(Long resellerCommissionId) {
        this.resellerCommissionId = resellerCommissionId;
    }

    public Reseller getReseller() {
        return reseller;
    }

    public void setReseller(Reseller reseller) {
        this.reseller = reseller;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
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

    public ResellerCommissionDTO convert2Object() {
        ResellerCommissionDTO dto = new ResellerCommissionDTO();
        if (this.resellerCommissionId != null) dto.setResellerCommissionId(this.resellerCommissionId);
        if (this.reseller != null) dto.setReseller(this.reseller.convert2RelationalObject());
        if (this.productGroup != null) dto.setProductGroup(this.productGroup.convert2RelationalObject());
        if (this.percentage != null) dto.setPercentage(this.percentage);
        return dto;
    }

    public ResellerCommissionInfoDTO convert2InfoObject() {
        ResellerCommissionInfoDTO dto = new ResellerCommissionInfoDTO();
        if(this.resellerCommissionId != null) dto.setResellerCommissionId(this.resellerCommissionId);
        if(this.reseller != null) dto.setReseller(this.reseller.convert2RelationalObject());
        if(this.productGroup != null) dto.setProductGroup(this.productGroup.convert2RelationalObject());
        if(this.percentage != null) dto.setPercentage(this.percentage);
        if(this.createdBy != null) dto.setCreatedBy(this.createdBy);
        if(this.updatedBy != null) dto.setUpdatedBy(this.updatedBy);
        if(this.deletedBy != null) dto.setDeletedBy(this.deletedBy);
        if(this.createdDate != null) dto.setCreatedDate(this.createdDate);
        if(this.updatedDate != null) dto.setUpdatedDate(this.updatedDate);
        if(this.deletedDate != null) dto.setDeletedDate(this.deletedDate);
        if(this.isDeleted != null) dto.setIsDeleted(this.isDeleted);
        return dto;
    }
}
