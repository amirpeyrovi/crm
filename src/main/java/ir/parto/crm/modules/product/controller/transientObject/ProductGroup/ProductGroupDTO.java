package ir.parto.crm.modules.product.controller.transientObject.ProductGroup;

public class ProductGroupDTO {
    private Long productGroupId;
    private ProductGroupRelationalDTO productGroup;
    private String title;
    private String adminDescription;
    private String clientDescription;
    private String cover;
    private String path;
    private Integer lvl;

    public ProductGroupDTO() {
    }

    public ProductGroupDTO(Long productGroupId, ProductGroupRelationalDTO productGroup, String title, String adminDescription, String clientDescription, String cover, String path, Integer lvl) {
        this.productGroupId = productGroupId;
        this.productGroup = productGroup;
        this.title = title;
        this.adminDescription = adminDescription;
        this.clientDescription = clientDescription;
        this.cover = cover;
        this.path = path;
        this.lvl = lvl;
    }

    public Long getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(Long productGroupId) {
        this.productGroupId = productGroupId;
    }

    public ProductGroupRelationalDTO getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupRelationalDTO productGroup) {
        this.productGroup = productGroup;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }
}
