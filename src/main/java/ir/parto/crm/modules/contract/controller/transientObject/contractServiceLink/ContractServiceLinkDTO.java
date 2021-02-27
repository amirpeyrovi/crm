package ir.parto.crm.modules.contract.controller.transientObject.contractServiceLink;

import ir.parto.crm.modules.contract.controller.transientObject.contractTemplate.ContractTemplateRelationalDTO;
import ir.parto.crm.modules.service.controller.transientObject.service.ServiceRelationalDTO;
import ir.parto.crm.modules.service.controller.transientObject.serviceAddon.ServiceAddonRelationalDTO;

public class ContractServiceLinkDTO {
    private Long contractServiceLinkId;
    private ContractTemplateRelationalDTO contractTemplate;
    private ServiceRelationalDTO service;
    private ServiceAddonRelationalDTO serviceAddon;

    public ContractServiceLinkDTO() {
    }

    public ContractServiceLinkDTO(Long contractServiceLinkId, ContractTemplateRelationalDTO contractTemplate, ServiceRelationalDTO service, ServiceAddonRelationalDTO serviceAddon) {
        this.contractServiceLinkId = contractServiceLinkId;
        this.contractTemplate = contractTemplate;
        this.service = service;
        this.serviceAddon = serviceAddon;
    }

    public Long getContractServiceLinkId() {
        return contractServiceLinkId;
    }

    public void setContractServiceLinkId(Long contractServiceLinkId) {
        this.contractServiceLinkId = contractServiceLinkId;
    }

    public ContractTemplateRelationalDTO getContractTemplate() {
        return contractTemplate;
    }

    public void setContractTemplate(ContractTemplateRelationalDTO contractTemplate) {
        this.contractTemplate = contractTemplate;
    }

    public ServiceRelationalDTO getService() {
        return service;
    }

    public void setService(ServiceRelationalDTO service) {
        this.service = service;
    }

    public ServiceAddonRelationalDTO getServiceAddon() {
        return serviceAddon;
    }

    public void setServiceAddon(ServiceAddonRelationalDTO serviceAddon) {
        this.serviceAddon = serviceAddon;
    }
}
