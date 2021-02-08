package ir.parto.crm.modules.product.controller.transientObject.productParameterGroupLink;

import ir.parto.crm.modules.product.model.entity.*;

public class ProductParameterGroupLinkAddDTO {
    private Long productParameterGroupId;
    private Long productId;
    private Long productAddonId;

    public ProductParameterGroupLinkAddDTO() {
    }

    public ProductParameterGroupLinkAddDTO(Long productParameterGroupId, Long productId, Long productAddonId) {
        this.productParameterGroupId = productParameterGroupId;
        this.productId = productId;
        this.productAddonId = productAddonId;
    }

    public Long getProductParameterGroupId() {
        return productParameterGroupId;
    }

    public void setProductParameterGroupId(Long productParameterGroupId) {
        this.productParameterGroupId = productParameterGroupId;
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

    public ProductParameterGroupLink convert2Object() {
        ProductParameterGroupLink obj = new ProductParameterGroupLink();
        if (this.productParameterGroupId != null)
            obj.setProductParameterGroup(new ProductParameterGroup(this.productParameterGroupId));
        if (this.productId != null) obj.setProduct(new Product(this.productId));
        if (this.productAddonId != null) obj.setProductAddon(new ProductAddon(this.productAddonId));
        return obj;
    }
}
