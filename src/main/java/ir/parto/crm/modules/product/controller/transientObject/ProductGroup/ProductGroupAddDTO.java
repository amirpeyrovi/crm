package ir.parto.crm.modules.product.controller.transientObject.ProductGroup;

public class ProductGroupAddDTO {

    private Long productGroupId;
    private String title;
    private String adminDescription;
    private String clientDescription;
    private String cover;

    public ProductGroupAddDTO() {
    }

    public ProductGroupAddDTO(Long productGroupId, String title, String adminDescription, String clientDescription, String cover) {
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
}
