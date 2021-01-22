package ir.parto.crm.modules.product.controller.transientObject.productAddonsLink;

import ir.parto.crm.modules.product.controller.transientObject.product.ProductRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productAddon.ProductAddonRelationalDTO;

public class ProductAddonsLinkRelationalDTO {
    private Long productAddonLinkId;
    private ProductAddonRelationalDTO productAddon;
    private ProductRelationalDTO product;

    public ProductAddonsLinkRelationalDTO() {
    }

    public ProductAddonsLinkRelationalDTO(Long productAddonLinkId, ProductAddonRelationalDTO productAddon, ProductRelationalDTO product) {
        this.productAddonLinkId = productAddonLinkId;
        this.productAddon = productAddon;
        this.product = product;
    }

    public Long getProductAddonLinkId() {
        return productAddonLinkId;
    }

    public void setProductAddonLinkId(Long productAddonLinkId) {
        this.productAddonLinkId = productAddonLinkId;
    }

    public ProductAddonRelationalDTO getProductAddon() {
        return productAddon;
    }

    public void setProductAddon(ProductAddonRelationalDTO productAddon) {
        this.productAddon = productAddon;
    }

    public ProductRelationalDTO getProduct() {
        return product;
    }

    public void setProduct(ProductRelationalDTO product) {
        this.product = product;
    }
}
