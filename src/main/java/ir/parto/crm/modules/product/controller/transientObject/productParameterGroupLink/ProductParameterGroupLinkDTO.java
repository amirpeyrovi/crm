package ir.parto.crm.modules.product.controller.transientObject.productParameterGroupLink;

import ir.parto.crm.modules.product.controller.transientObject.product.ProductRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productAddon.ProductAddonRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productParameterGroup.ProductParameterGroupRelationalDTO;

public class ProductParameterGroupLinkDTO {
    private Long productParameterGroupLinkId;
    private ProductParameterGroupRelationalDTO productParameterGroup;
    private ProductRelationalDTO product;
    private ProductAddonRelationalDTO productAddon;

    public ProductParameterGroupLinkDTO() {
    }

    public ProductParameterGroupLinkDTO(Long productParameterGroupLinkId, ProductParameterGroupRelationalDTO productParameterGroup, ProductRelationalDTO product, ProductAddonRelationalDTO productAddon) {
        this.productParameterGroupLinkId = productParameterGroupLinkId;
        this.productParameterGroup = productParameterGroup;
        this.product = product;
        this.productAddon = productAddon;
    }

    public Long getProductParameterGroupLinkId() {
        return productParameterGroupLinkId;
    }

    public void setProductParameterGroupLinkId(Long productParameterGroupLinkId) {
        this.productParameterGroupLinkId = productParameterGroupLinkId;
    }

    public ProductParameterGroupRelationalDTO getProductParameterGroup() {
        return productParameterGroup;
    }

    public void setProductParameterGroup(ProductParameterGroupRelationalDTO productParameterGroup) {
        this.productParameterGroup = productParameterGroup;
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
}
