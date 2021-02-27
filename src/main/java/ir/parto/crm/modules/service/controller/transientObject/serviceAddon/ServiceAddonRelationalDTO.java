package ir.parto.crm.modules.service.controller.transientObject.serviceAddon;

import ir.parto.crm.modules.product.controller.transientObject.productAddon.ProductAddonRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productCycle.ProductCycleRelationalDTO;
import ir.parto.crm.modules.service.controller.transientObject.service.ServiceRelationalDTO;

public class ServiceAddonRelationalDTO {

    private Long serviceId;
    private ServiceRelationalDTO service;
    private ProductAddonRelationalDTO productAddon;
    private ProductCycleRelationalDTO productCycle;
    private Long price;

    public ServiceAddonRelationalDTO() {
    }

    public ServiceAddonRelationalDTO(Long serviceId, ServiceRelationalDTO service, ProductAddonRelationalDTO productAddon, ProductCycleRelationalDTO productCycle, Long price) {
        this.serviceId = serviceId;
        this.service = service;
        this.productAddon = productAddon;
        this.productCycle = productCycle;
        this.price = price;
    }


    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public ServiceRelationalDTO getService() {
        return service;
    }

    public void setService(ServiceRelationalDTO service) {
        this.service = service;
    }

    public ProductAddonRelationalDTO getProductAddon() {
        return productAddon;
    }

    public void setProductAddon(ProductAddonRelationalDTO productAddon) {
        this.productAddon = productAddon;
    }

    public ProductCycleRelationalDTO getProductCycle() {
        return productCycle;
    }

    public void setProductCycle(ProductCycleRelationalDTO productCycle) {
        this.productCycle = productCycle;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
