package ir.parto.crm.modules.contract.controller.transientObject.contractProductGroupLink;

import ir.parto.crm.modules.contract.model.entity.ContractProductGroupLink;
import ir.parto.crm.modules.contract.model.entity.ContractTemplate;
import ir.parto.crm.modules.product.model.entity.ProductGroup;

public class ContractProductGroupLinkAddDTO {
    private Long contractTemplateId;
    private Long productGroupId;
    private Integer automatic;

    public ContractProductGroupLinkAddDTO() {
    }

    public ContractProductGroupLinkAddDTO(Long contractTemplateId, Long productGroupId, Integer automatic) {
        this.contractTemplateId = contractTemplateId;
        this.productGroupId = productGroupId;
        this.automatic = automatic;
    }

    public Long getContractTemplateId() {
        return contractTemplateId;
    }

    public void setContractTemplateId(Long contractTemplateId) {
        this.contractTemplateId = contractTemplateId;
    }

    public Long getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(Long productGroupId) {
        this.productGroupId = productGroupId;
    }

    public Integer getAutomatic() {
        return automatic;
    }

    public void setAutomatic(Integer automatic) {
        this.automatic = automatic;
    }

    public ContractProductGroupLink convert2Object() {
        ContractProductGroupLink obj = new ContractProductGroupLink();
        if(this.contractTemplateId != null) obj.setContractTemplate(new ContractTemplate(this.contractTemplateId));
        if(this.productGroupId != null) obj.setProductGroup(new ProductGroup(this.productGroupId));
        if(this.automatic != null) obj.setAutomatic(this.automatic);
        return obj;
    }
}
