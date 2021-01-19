package ir.parto.crm.modules.product.controller.transientObject.productGroup;

public class ProductGroupRelationalDTO {
    private Long productGroupId;
    private String title;
    private String path;
    private Integer level;

    public ProductGroupRelationalDTO() {
    }

    public ProductGroupRelationalDTO(Long productGroupId, String title, String path, Integer level) {
        this.productGroupId = productGroupId;
        this.title = title;
        this.path = path;
        this.level = level;
    }

    public Long getProductGroupId() {
        return productGroupId;
    }

    public ProductGroupRelationalDTO setProductGroupId(Long productGroupId) {
        this.productGroupId = productGroupId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ProductGroupRelationalDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ProductGroupRelationalDTO setPath(String path) {
        this.path = path;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public ProductGroupRelationalDTO setLevel(Integer level) {
        this.level = level;
        return this;
    }
}
