package ir.parto.crm.modules.contract.controller.transientObject.contractTemplate;

import ir.parto.crm.modules.contract.controller.transientObject.contractGroup.ContractGroupDTO;

public class ContractTemplateDTO {
    private Long contractTemplateId;
    private String title;
    private String contract;
    private ContractGroupDTO contractGroup;

    public ContractTemplateDTO() {
    }

    public ContractTemplateDTO(Long contractTemplateId, String title, String contract, ContractGroupDTO contractGroup) {
        this.contractTemplateId = contractTemplateId;
        this.title = title;
        this.contract = contract;
        this.contractGroup = contractGroup;
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

    public ContractGroupDTO getContractGroup() {
        return contractGroup;
    }

    public void setContractGroup(ContractGroupDTO contractGroup) {
        this.contractGroup = contractGroup;
    }
}
