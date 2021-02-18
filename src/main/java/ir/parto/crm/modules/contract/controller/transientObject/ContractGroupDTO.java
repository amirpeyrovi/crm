package ir.parto.crm.modules.contract.controller.transientObject;

public class ContractGroupDTO {
    private Long contractGroupId;
    private String title;
    private String description;

    public ContractGroupDTO() {
    }

    public ContractGroupDTO(Long contractGroupId, String title, String description) {
        this.contractGroupId = contractGroupId;
        this.title = title;
        this.description = description;
    }

    public Long getContractGroupId() {
        return contractGroupId;
    }

    public void setContractGroupId(Long contractGroupId) {
        this.contractGroupId = contractGroupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
