package ir.parto.crm.modules.contract.controller.transientObject.contractGroup;

import ir.parto.crm.modules.contract.model.entity.ContractGroup;

public class ContractGroupEditDTO {
    private String title;
    private String description;

    public ContractGroupEditDTO() {
    }

    public ContractGroupEditDTO(String title, String description) {
        this.title = title;
        this.description = description;
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

    public ContractGroup convert2Object() {
        ContractGroup contractGroup = new ContractGroup();
        if (this.title != null) contractGroup.setTitle(this.title);
        if (this.description != null) contractGroup.setDescription(this.description);
        return contractGroup;
    }
}
