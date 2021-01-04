package ir.parto.crm.modules.product.controller.transientObject.ProductGroup;

import ir.parto.crm.modules.product.model.entity.ProductGroup;

public class ProductEditDTO {
    private Long productGroupId;
    private String title;
    private String adminDescription;
    private String clientDescription;
    private String cover;

    public ProductEditDTO() {
    }

    public ProductEditDTO(Long productGroupId, String title, String adminDescription, String clientDescription, String cover) {
        this.productGroupId = productGroupId;
        this.title = title;
        this.adminDescription = adminDescription;
        this.clientDescription = clientDescription;
        this.cover = cover;
    }

    public Long getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(Long productGroupId) {
        this.productGroupId = productGroupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdminDescription() {
        return adminDescription;
    }

    public void setAdminDescription(String adminDescription) {
        this.adminDescription = adminDescription;
    }

    public String getClientDescription() {
        return clientDescription;
    }

    public void setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public ProductGroup convert2Object() {
        ProductGroup productGroup = new ProductGroup();
        if (this.productGroupId != null) productGroup.setProductGroup(new ProductGroup(this.productGroupId));
        if (this.title != null) productGroup.setTitle(this.title);
        if (this.adminDescription != null) productGroup.setAdminDescription(this.adminDescription);
        if (this.clientDescription != null) productGroup.setClientDescription(this.clientDescription);
        if (this.cover != null) productGroup.setCover(this.cover);
        return productGroup;
    }
}
