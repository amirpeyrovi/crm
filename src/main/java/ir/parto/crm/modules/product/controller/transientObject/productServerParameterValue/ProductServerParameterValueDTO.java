package ir.parto.crm.modules.product.controller.transientObject.productServerParameterValue;

import ir.parto.crm.modules.product.controller.transientObject.product.ProductRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productAddon.ProductAddonRelationalDTO;
import ir.parto.crm.modules.server.controller.transientObject.serverParameter.ServerParameterRelationalDTO;

public class ProductServerParameterValueDTO {
    private Long productServerParameterId;
    private ProductRelationalDTO product;
    private ProductAddonRelationalDTO productAddon;
    private ServerParameterRelationalDTO serverParameter;
    private String value;

    public ProductServerParameterValueDTO() {
    }

    public ProductServerParameterValueDTO(Long productServerParameterId, ProductRelationalDTO product, ProductAddonRelationalDTO productAddon, ServerParameterRelationalDTO serverParameter, String value) {
        this.productServerParameterId = productServerParameterId;
        this.product = product;
        this.productAddon = productAddon;
        this.serverParameter = serverParameter;
        this.value = value;
    }

    public Long getProductServerParameterId() {
        return productServerParameterId;
    }

    public void setProductServerParameterId(Long productServerParameterId) {
        this.productServerParameterId = productServerParameterId;
    }

    public ProductRelationalDTO getProduct() {
        return product;
    }

    public void setProduct(ProductRelationalDTO product) {
        this.product = product;
    }

    public ProductAddonRelationalDTO getProductAddon() {
        return productAddon;
    }

    public void setProductAddon(ProductAddonRelationalDTO productAddon) {
        this.productAddon = productAddon;
    }

    public ServerParameterRelationalDTO getServerParameter() {
        return serverParameter;
    }

    public void setServerParameter(ServerParameterRelationalDTO serverParameter) {
        this.serverParameter = serverParameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
