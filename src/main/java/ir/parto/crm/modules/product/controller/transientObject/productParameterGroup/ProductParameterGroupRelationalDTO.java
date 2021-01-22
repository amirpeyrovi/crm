package ir.parto.crm.modules.product.controller.transientObject.productParameterGroup;

public class ProductParameterGroupRelationalDTO {
    private Long productParameterGroupId;
    private String title;

    public ProductParameterGroupRelationalDTO() {
    }

    public ProductParameterGroupRelationalDTO(Long productParameterGroupId, String title) {
        this.productParameterGroupId = productParameterGroupId;
        this.title = title;
    }

    public Long getProductParameterGroupId() {
        return productParameterGroupId;
    }

    public void setProductParameterGroupId(Long productParameterGroupId) {
        this.productParameterGroupId = productParameterGroupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
