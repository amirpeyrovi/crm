package ir.parto.crm.modules.product.controller.transientObject.productAddon;

import ir.parto.crm.modules.product.controller.transientObject.productGroup.ProductGroupRelationalDTO;

public class ProductAddonRelationalDTO {
    private Long productAddonId;
    private String title;
    private ProductGroupRelationalDTO productGroup;

    public ProductAddonRelationalDTO() {
    }

    public ProductAddonRelationalDTO(Long productAddonId, String title, ProductGroupRelationalDTO productGroup) {
        this.productAddonId = productAddonId;
        this.title = title;
        this.productGroup = productGroup;
    }

    public Long getProductAddonId() {
        return productAddonId;
    }

    public void setProductAddonId(Long productAddonId) {
        this.productAddonId = productAddonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProductGroupRelationalDTO getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupRelationalDTO productGroup) {
        this.productGroup = productGroup;
    }
}
