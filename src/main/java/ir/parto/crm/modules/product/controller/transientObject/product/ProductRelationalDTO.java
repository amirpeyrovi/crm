package ir.parto.crm.modules.product.controller.transientObject.product;

import ir.parto.crm.modules.product.controller.transientObject.ProductGroup.ProductGroupRelationalDTO;

public class ProductRelationalDTO {
    private Long productId;
    private String title;
    private ProductGroupRelationalDTO productGroup;

    public ProductRelationalDTO() {
    }

    public ProductRelationalDTO(Long productId, String title, ProductGroupRelationalDTO productGroup) {
        this.productId = productId;
        this.title = title;
        this.productGroup = productGroup;
    }

    public Long getProductId() {
        return productId;
    }

    public ProductRelationalDTO setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ProductRelationalDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductGroupRelationalDTO getProductGroup() {
        return productGroup;
    }

    public ProductRelationalDTO setProductGroup(ProductGroupRelationalDTO productGroup) {
        this.productGroup = productGroup;
        return this;
    }
}
