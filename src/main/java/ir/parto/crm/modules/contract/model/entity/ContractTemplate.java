package ir.parto.crm.modules.contract.model.entity;

import ir.parto.crm.modules.contract.controller.transientObject.contractTemplate.ContractTemplateDTO;
import ir.parto.crm.modules.contract.controller.transientObject.contractTemplate.ContractTemplateInfoDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_contract_template")
public class ContractTemplate implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "crm_contract_seq", sequenceName = "crm_contract_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "crm_contract_seq")
    private Long contractTemplateId;

    @Column(name = "title", columnDefinition = "nvarchar2(50)")
    private String title;

    @Column(name = "contract", columnDefinition = "clob")
    private String contract;

    @ManyToOne
    @JoinColumn(name = "contract_group_id", foreignKey = @ForeignKey(name = "contract_template_contract_group_fk"))
    private ContractGroup contractGroup;


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

    public ContractTemplate() {
    }

    public Long getContractTemplateId() {
        return contractTemplateId;
    }

    public void setContractTemplateId(Long contractTemplateId) {
        this.contractTemplateId = contractTemplateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public ContractGroup getContractGroup() {
        return contractGroup;
    }

    public void setContractGroup(ContractGroup contractGroup) {
        this.contractGroup = contractGroup;
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

    public ContractTemplateDTO convert2Object() {
        ContractTemplateDTO dto = new ContractTemplateDTO();
        if (this.contractTemplateId != null) dto.setContractTemplateId(this.contractTemplateId);
        if (this.title != null) dto.setTitle(this.title);
        if (this.contract != null) dto.setContract(this.contract);
        if (this.contractGroup != null) dto.setContractGroup(this.contractGroup.convert2Object());
        return dto;
    }

    public ContractTemplateInfoDTO convert2InfoObject() {
        ContractTemplateInfoDTO dto = new ContractTemplateInfoDTO();
        if (this.contractTemplateId != null) dto.setContractTemplateId(this.contractTemplateId);
        if (this.title != null) dto.setTitle(this.title);
        if (this.contract != null) dto.setContract(this.contract);
        if (this.contractGroup != null) dto.setContractGroup(this.contractGroup.convert2Object());
        if (this.createdBy != null) dto.setCreatedBy(this.createdBy);
        if (this.updatedBy != null) dto.setUpdatedBy(this.updatedBy);
        if (this.deletedBy != null) dto.setDeletedBy(this.deletedBy);
        if (this.createdDate != null) dto.setCreatedDate(this.createdDate);
        if (this.updatedDate != null) dto.setUpdatedDate(this.updatedDate);
        if (this.deletedDate != null) dto.setDeletedDate(this.deletedDate);
        if (this.isDeleted != null) dto.setIsDeleted(this.isDeleted);
        return dto;
    }
}
