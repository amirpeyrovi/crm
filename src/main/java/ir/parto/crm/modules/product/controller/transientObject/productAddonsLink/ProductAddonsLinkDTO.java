package ir.parto.crm.modules.product.controller.transientObject.productAddonsLink;

import ir.parto.crm.modules.product.controller.transientObject.product.ProductDTO;
import ir.parto.crm.modules.product.controller.transientObject.productAddon.ProductAddonDTO;

public class ProductAddonsLinkDTO {
    private Long productAddonLinkId;
    private ProductAddonDTO productAddon;
    private ProductDTO product;

    public ProductAddonsLinkDTO() {
    }

    public ProductAddonsLinkDTO(Long productAddonLinkId, ProductAddonDTO productAddon, ProductDTO product) {
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

    public ProductAddonDTO getProductAddon() {
        return productAddon;
    }

    public void setProductAddon(ProductAddonDTO productAddon) {
        this.productAddon = productAddon;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
