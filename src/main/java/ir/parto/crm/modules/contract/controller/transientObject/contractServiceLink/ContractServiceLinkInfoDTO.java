package ir.parto.crm.modules.contract.controller.transientObject.contractServiceLink;

import ir.parto.crm.modules.contract.controller.transientObject.contractTemplate.ContractTemplateRelationalDTO;
import ir.parto.crm.modules.service.controller.transientObject.service.ServiceRelationalDTO;
import ir.parto.crm.modules.service.controller.transientObject.serviceAddon.ServiceAddonRelationalDTO;

import java.time.LocalDateTime;

public class ContractServiceLinkInfoDTO {
    private Long contractServiceLinkId;
    private ContractTemplateRelationalDTO contractTemplate;
    private ServiceRelationalDTO service;
    private ServiceAddonRelationalDTO serviceAddon;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private Integer isDeleted;

    public ContractServiceLinkInfoDTO() {
    }

    public ContractServiceLinkInfoDTO(Long contractServiceLinkId, ContractTemplateRelationalDTO contractTemplate, ServiceRelationalDTO service, ServiceAddonRelationalDTO serviceAddon, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.contractServiceLinkId = contractServiceLinkId;
        this.contractTemplate = contractTemplate;
        this.service = service;
        this.serviceAddon = serviceAddon;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getContractServiceLinkId() {
        return contractServiceLinkId;
    }

    public void setContractServiceLinkId(Long contractServiceLinkId) {
        this.contractServiceLinkId = contractServiceLinkId;
    }

    public ContractTemplateRelationalDTO getContractTemplate() {
        return contractTemplate;
    }

    public void setContractTemplate(ContractTemplateRelationalDTO contractTemplate) {
        this.contractTemplate = contractTemplate;
    }

    public ServiceRelationalDTO getService() {
        return service;
    }

    public void setService(ServiceRelationalDTO service) {
        this.service = service;
    }

    public ServiceAddonRelationalDTO getServiceAddon() {
        return serviceAddon;
    }

    public void setServiceAddon(ServiceAddonRelationalDTO serviceAddon) {
        this.serviceAddon = serviceAddon;
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
