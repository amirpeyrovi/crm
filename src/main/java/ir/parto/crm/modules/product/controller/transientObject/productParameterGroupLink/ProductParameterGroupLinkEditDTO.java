package ir.parto.crm.modules.product.controller.transientObject.productParameterGroupLink;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;
import ir.parto.crm.modules.product.model.entity.ProductParameterGroupLink;

public class ProductParameterGroupLinkEditDTO {
    private Long productParameterGroupId;
    private Long productId;
    private Long productAddonId;

    public ProductParameterGroupLinkEditDTO() {
    }

    public ProductParameterGroupLinkEditDTO(Long productParameterGroupId, Long productId, Long productAddonId) {
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
        ProductParameterGroupLink productParameterGroupLink = new ProductParameterGroupLink();
        if (this.productParameterGroupId != null)
            productParameterGroupLink.setProductParameterGroup(new ProductParameterGroup(this.productParameterGroupId));
        if (this.productId != null) productParameterGroupLink.setProduct(new Product(this.productId));
        if (this.productAddonId != null)
            productParameterGroupLink.setProductAddon(new ProductAddon(this.productAddonId));
        return productParameterGroupLink;
    }
}
