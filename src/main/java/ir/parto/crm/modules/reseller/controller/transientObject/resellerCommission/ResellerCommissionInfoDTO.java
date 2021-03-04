package ir.parto.crm.modules.reseller.controller.transientObject.resellerCommission;

import ir.parto.crm.modules.product.controller.transientObject.productGroup.ProductGroupRelationalDTO;
import ir.parto.crm.modules.reseller.controller.transientObject.reseller.ResellerRelationalDTO;

import java.time.LocalDateTime;

public class ResellerCommissionInfoDTO {
    private Long resellerCommissionId;
    private ResellerRelationalDTO reseller;
    private ProductGroupRelationalDTO productGroup;
    private Integer percentage;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private Integer isDeleted;

    public ResellerCommissionInfoDTO() {
    }

    public ResellerCommissionInfoDTO(Long resellerCommissionId, ResellerRelationalDTO reseller, ProductGroupRelationalDTO productGroup, Integer percentage, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.resellerCommissionId = resellerCommissionId;
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

    public ResellerRelationalDTO getReseller() {
        return reseller;
    }

    public void setReseller(ResellerRelationalDTO reseller) {
        this.reseller = reseller;
    }

    public ProductGroupRelationalDTO getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupRelationalDTO productGroup) {
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
}
