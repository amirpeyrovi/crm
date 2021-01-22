package ir.parto.crm.modules.product.controller.transientObject.productAddonsLink;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductAddonsLink;

public class ProductAddonsLinkEditDTO {
    private Long productAddonId;
    private Long productId;

    public ProductAddonsLinkEditDTO() {
    }

    public ProductAddonsLinkEditDTO(Long productAddonId, Long productId) {
        this.productAddonId = productAddonId;
        this.productId = productId;
    }

    public Long getProductAddonId() {
        return productAddonId;
    }

    public void setProductAddonId(Long productAddonId) {
        this.productAddonId = productAddonId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductAddonsLink convert2Object() {
        ProductAddonsLink productAddonsLink = new ProductAddonsLink();
        if (this.productAddonId != null) productAddonsLink.setProductAddon(new ProductAddon(this.productAddonId));
        if (this.productId != null) productAddonsLink.setProduct(new Product(this.productId));
        return productAddonsLink;
    }
}
