package ir.parto.crm.modules.contract.model.entity;

import ir.parto.crm.modules.contract.controller.transientObject.contractServiceLink.ContractServiceLinkDTO;
import ir.parto.crm.modules.contract.controller.transientObject.contractServiceLink.ContractServiceLinkInfoDTO;
import ir.parto.crm.modules.service.model.entity.Service;
import ir.parto.crm.modules.service.model.entity.ServiceAddon;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_contract_service_link")
public class ContractServiceLink implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "crm_contract_seq", sequenceName = "crm_contract_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "crm_contract_seq")
    private Long contractServiceLinkId;

    @ManyToOne
    @JoinColumn(name = "contract_id", foreignKey = @ForeignKey(name = "contract_service_link_contract_template_fk"))
    private ContractTemplate contractTemplate;

    @ManyToOne
    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "contract_service_link_service_fk"))
    private Service service;

    @ManyToOne
    @JoinColumn(name = "service_addon_id", foreignKey = @ForeignKey(name = "contract_service_link_service_addon_fk"))
    private ServiceAddon serviceAddon;


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

    public ContractServiceLink() {
    }

    public ContractServiceLink(ContractTemplate contractTemplate, Service service, ServiceAddon serviceAddon, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
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

    public ContractTemplate getContractTemplate() {
        return contractTemplate;
    }

    public void setContractTemplate(ContractTemplate contractTemplate) {
        this.contractTemplate = contractTemplate;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServiceAddon getServiceAddon() {
        return serviceAddon;
    }

    public void setServiceAddon(ServiceAddon serviceAddon) {
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

    public ContractServiceLinkInfoDTO convert2InfoObject() {
        ContractServiceLinkInfoDTO dto = new ContractServiceLinkInfoDTO();
        if (this.contractServiceLinkId != null) dto.setContractServiceLinkId(this.contractServiceLinkId);
        if (this.contractTemplate != null) dto.setContractTemplate(this.contractTemplate.convert2RelationalObject());
        if (this.service != null) dto.setService(this.service.convert2RelationalObject());
        if (this.serviceAddon != null) dto.setServiceAddon(this.serviceAddon.convert2RelationalObject());
        if (this.createdBy != null) dto.setCreatedBy(this.createdBy);
        if (this.updatedBy != null) dto.setUpdatedBy(this.updatedBy);
        if (this.deletedBy != null) dto.setDeletedBy(this.deletedBy);
        if (this.createdDate != null) dto.setCreatedDate(this.createdDate);
        if (this.updatedDate != null) dto.setUpdatedDate(this.updatedDate);
        if (this.deletedDate != null) dto.setDeletedDate(this.deletedDate);
        if (this.isDeleted != null) dto.setIsDeleted(this.isDeleted);
        return dto;
    }

    public ContractServiceLinkDTO convert2Object() {
        ContractServiceLinkDTO dto = new ContractServiceLinkDTO();
        if (this.contractServiceLinkId != null) dto.setContractServiceLinkId(this.contractServiceLinkId);
        if (this.contractTemplate != null) dto.setContractTemplate(this.contractTemplate.convert2RelationalObject());
        if (this.service != null) dto.setService(this.service.convert2RelationalObject());
        if (this.serviceAddon != null) dto.setServiceAddon(this.serviceAddon.convert2RelationalObject());
        return dto;
    }
}
