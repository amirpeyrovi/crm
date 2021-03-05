package ir.parto.crm.modules.contract.controller.transientObject.contractServiceLink;

import ir.parto.crm.modules.contract.model.entity.ContractServiceLink;
import ir.parto.crm.modules.contract.model.entity.ContractTemplate;
import ir.parto.crm.modules.service.model.entity.Service;
import ir.parto.crm.modules.service.model.entity.ServiceAddon;

public class ContractServiceLinkAddDTO {
    private Long contractTemplateId;
    private Long serviceId;
    private Long serviceAddonId;

    public ContractServiceLinkAddDTO() {
    }

    public ContractServiceLinkAddDTO(Long contractTemplateId, Long serviceId, Long serviceAddonId) {
        this.contractTemplateId = contractTemplateId;
        this.serviceId = serviceId;
        this.serviceAddonId = serviceAddonId;
    }

    public Long getContractTemplateId() {
        return contractTemplateId;
    }

    public void setContractTemplateId(Long contractTemplateId) {
        this.contractTemplateId = contractTemplateId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getServiceAddonId() {
        return serviceAddonId;
    }

    public void setServiceAddonId(Long serviceAddonId) {
        this.serviceAddonId = serviceAddonId;
    }

    public ContractServiceLink convert2Object() {
        ContractServiceLink obj = new ContractServiceLink();
        if (this.contractTemplateId != null) obj.setContractTemplate(new ContractTemplate(this.contractTemplateId));
        if (this.serviceId != null) obj.setService(new Service(this.serviceId));
        if (this.serviceAddonId != null) obj.setServiceAddon(new ServiceAddon(this.serviceAddonId));
        return obj;
    }
}
