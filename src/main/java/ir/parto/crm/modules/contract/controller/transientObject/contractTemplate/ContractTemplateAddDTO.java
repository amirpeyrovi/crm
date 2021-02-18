package ir.parto.crm.modules.contract.controller.transientObject.contractTemplate;

import ir.parto.crm.modules.contract.model.entity.ContractGroup;
import ir.parto.crm.modules.contract.model.entity.ContractTemplate;

public class ContractTemplateAddDTO {
    private String title;
    private String contract;
    private Long contractGroupId;

    public ContractTemplateAddDTO() {
    }

    public ContractTemplateAddDTO(String title, String contract, Long contractGroupId) {
        this.title = title;
        this.contract = contract;
        this.contractGroupId = contractGroupId;
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

    public Long getContractGroupId() {
        return contractGroupId;
    }

    public void setContractGroupId(Long contractGroupId) {
        this.contractGroupId = contractGroupId;
    }

    public ContractTemplate convert2Object() {
        ContractTemplate obj = new ContractTemplate();
        if (this.title != null) obj.setTitle(this.title);
        if (this.contract != null) obj.setContract(this.contract);
        if (this.contractGroupId != null) obj.setContractGroup(new ContractGroup(this.contractGroupId));
        return obj;
    }
}
