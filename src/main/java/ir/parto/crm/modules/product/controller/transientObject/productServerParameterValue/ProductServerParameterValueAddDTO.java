package ir.parto.crm.modules.product.controller.transientObject.productServerParameterValue;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductServerParameterValue;
import ir.parto.crm.modules.server.model.entity.ServerParameter;

public class ProductServerParameterValueAddDTO {
    private Long productId;
    private Long productAddonId;
    private Long serverParameterId;
    private String value;

    public ProductServerParameterValueAddDTO() {
    }

    public ProductServerParameterValueAddDTO(Long productId, Long productAddonId, Long serverParameterId, String value) {
        this.productId = productId;
        this.productAddonId = productAddonId;
        this.serverParameterId = serverParameterId;
        this.value = value;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductAddonId() {
        return productAddonId;
    }

    public void setProductAddonId(Long productAddonId) {
        this.productAddonId = productAddonId;
    }

    public Long getServerParameterId() {
        return serverParameterId;
    }

    public void setServerParameterId(Long serverParameterId) {
        this.serverParameterId = serverParameterId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ProductServerParameterValue convert2Object() {
        ProductServerParameterValue obj = new ProductServerParameterValue();
        if (this.productId != null) obj.setProduct(new Product(this.productId));
        if (this.productAddonId != null) obj.setProductAddon(new ProductAddon(this.productAddonId));
        if (this.serverParameterId != null) obj.setServerParameter(new ServerParameter(this.serverParameterId));
        if (this.value != null) obj.setValue(this.value);
        return obj;
    }
}
