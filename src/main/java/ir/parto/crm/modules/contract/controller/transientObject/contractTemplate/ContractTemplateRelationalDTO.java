package ir.parto.crm.modules.contract.controller.transientObject.contractTemplate;

public class ContractTemplateRelationalDTO {
    private Long contractTemplateId;
    private String title;
    private String contract;

    public ContractTemplateRelationalDTO() {
    }

    public ContractTemplateRelationalDTO(Long contractTemplateId, String title, String contract) {
        this.contractTemplateId = contractTemplateId;
        this.title = title;
        this.contract = contract;
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
}
