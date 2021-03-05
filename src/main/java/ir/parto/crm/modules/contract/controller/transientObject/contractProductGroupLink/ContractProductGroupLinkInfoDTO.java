package ir.parto.crm.modules.contract.controller.transientObject.contractProductGroupLink;

import ir.parto.crm.modules.contract.controller.transientObject.contractTemplate.ContractTemplateRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productGroup.ProductGroupRelationalDTO;

import java.time.LocalDateTime;

public class ContractProductGroupLinkInfoDTO {
    private Long contractProductGroupLinkId;
    private ContractTemplateRelationalDTO contractTemplate;
    private ProductGroupRelationalDTO productGroup;
    private Integer automatic;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private Integer isDeleted;

    public ContractProductGroupLinkInfoDTO() {
    }

    public ContractProductGroupLinkInfoDTO(Long contractProductGroupLinkId, ContractTemplateRelationalDTO contractTemplate, ProductGroupRelationalDTO productGroup, Integer automatic, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.contractProductGroupLinkId = contractProductGroupLinkId;
        this.contractTemplate = contractTemplate;
        this.productGroup = productGroup;
        this.automatic = automatic;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getContractProductGroupLinkId() {
        return contractProductGroupLinkId;
    }

    public void setContractProductGroupLinkId(Long contractProductGroupLinkId) {
        this.contractProductGroupLinkId = contractProductGroupLinkId;
    }

    public ContractTemplateRelationalDTO getContractTemplate() {
        return contractTemplate;
    }

    public void setContractTemplate(ContractTemplateRelationalDTO contractTemplate) {
        this.contractTemplate = contractTemplate;
    }

    public ProductGroupRelationalDTO getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupRelationalDTO productGroup) {
        this.productGroup = productGroup;
    }

    public Integer getAutomatic() {
        return automatic;
    }

    public void setAutomatic(Integer automatic) {
        this.automatic = automatic;
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
