package ir.parto.crm.modules.service.controller.transientObject.service;

import ir.parto.crm.modules.client.controller.transientObject.client.ClientRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productCycle.ProductCycleRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.product.ProductRelationalDTO;

public class ServiceRelationalDTO {
    private Long serviceId;
    private String username;
    private ProductRelationalDTO product;
    private ClientRelationalDTO client;
    private ProductCycleRelationalDTO productCycle;

    public ServiceRelationalDTO() {
    }

    public ServiceRelationalDTO(Long serviceId, String username, ProductRelationalDTO product, ClientRelationalDTO client, ProductCycleRelationalDTO productCycle) {
        this.serviceId = serviceId;
        this.username = username;
        this.product = product;
        this.client = client;
        this.productCycle = productCycle;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public ServiceRelationalDTO setServiceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ServiceRelationalDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public ProductRelationalDTO getProduct() {
        return product;
    }

    public ServiceRelationalDTO setProduct(ProductRelationalDTO product) {
        this.product = product;
        return this;
    }

    public ClientRelationalDTO getClient() {
        return client;
    }

    public ServiceRelationalDTO setClient(ClientRelationalDTO client) {
        this.client = client;
        return this;
    }

    public ProductCycleRelationalDTO getProductCycle() {
        return productCycle;
    }

    public ServiceRelationalDTO setProductCycle(ProductCycleRelationalDTO productCycle) {
        this.productCycle = productCycle;
        return this;
    }
}
