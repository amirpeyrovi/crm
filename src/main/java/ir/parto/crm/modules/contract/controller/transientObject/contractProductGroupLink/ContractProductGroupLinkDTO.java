package ir.parto.crm.modules.contract.controller.transientObject.contractProductGroupLink;

import ir.parto.crm.modules.contract.controller.transientObject.contractTemplate.ContractTemplateRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productGroup.ProductGroupRelationalDTO;

public class ContractProductGroupLinkDTO {
    private Long contractProductGroupLinkId;
    private ContractTemplateRelationalDTO contractTemplate;
    private ProductGroupRelationalDTO productGroup;
    private Integer automatic;

    public ContractProductGroupLinkDTO() {
    }

    public ContractProductGroupLinkDTO(Long contractProductGroupLinkId, ContractTemplateRelationalDTO contractTemplate, ProductGroupRelationalDTO productGroup, Integer automatic) {
        this.contractProductGroupLinkId = contractProductGroupLinkId;
        this.contractTemplate = contractTemplate;
        this.productGroup = productGroup;
        this.automatic = automatic;
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
}
