package ir.parto.crm.modules.contract.model.entity;

import ir.parto.crm.modules.contract.controller.transientObject.contractProductGroupLink.ContractProductGroupLinkDTO;
import ir.parto.crm.modules.contract.controller.transientObject.contractProductGroupLink.ContractProductGroupLinkInfoDTO;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_contract_product_group_link")
public class ContractProductGroupLink implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "crm_contract_seq", sequenceName = "crm_contract_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "crm_contract_seq")
    private Long contractProductGroupLinkId;

    @ManyToOne
    @JoinColumn(name = "contract_id", foreignKey = @ForeignKey(name = "contract_product_group_link_contract_template_fk"))
    private ContractTemplate contractTemplate;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "contract_product_group_link_product_group_fk"))
    private ProductGroup productGroup;

    @Column(name = "automatic", columnDefinition = "number(1)")
    private Integer automatic;


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

    public ContractProductGroupLink() {
    }

    public Long getContractProductGroupLinkId() {
        return contractProductGroupLinkId;
    }

    public void setContractProductGroupLinkId(Long contractProductGroupLinkId) {
        this.contractProductGroupLinkId = contractProductGroupLinkId;
    }

    public ContractTemplate getContractTemplate() {
        return contractTemplate;
    }

    public void setContractTemplate(ContractTemplate contractTemplate) {
        this.contractTemplate = contractTemplate;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
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

    public ContractProductGroupLinkDTO convert2Object() {
        ContractProductGroupLinkDTO dto = new ContractProductGroupLinkDTO();
        if (this.contractProductGroupLinkId != null) dto.setContractProductGroupLinkId(this.contractProductGroupLinkId);
        if (this.contractTemplate != null) dto.setContractTemplate(this.contractTemplate.convert2RelationalObject());
        if (this.productGroup != null) dto.setProductGroup(this.productGroup.convert2RelationalObject());
        if (this.automatic != null) dto.setAutomatic(this.automatic);
        return dto;
    }

    public ContractProductGroupLinkInfoDTO convert2InfoObject() {
        ContractProductGroupLinkInfoDTO dto = new ContractProductGroupLinkInfoDTO();
        if (this.contractProductGroupLinkId != null) dto.setContractProductGroupLinkId(this.contractProductGroupLinkId);
        if (this.contractTemplate != null) dto.setContractTemplate(this.contractTemplate.convert2RelationalObject());
        if (this.productGroup != null) dto.setProductGroup(this.productGroup.convert2RelationalObject());
        if (this.automatic != null) dto.setAutomatic(this.automatic);
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
