package ir.parto.crm.modules.product.controller.transientObject.productParameter;

import ir.parto.crm.modules.product.controller.transientObject.productParameterGroup.ProductParameterGroupRelationalDTO;

public class ProductParameterDTO {
    private Long productParameterId;
    private ProductParameterGroupRelationalDTO productParameterGroup;
    private String title;
    private String description;
    private String type;
    private String options;

    public ProductParameterDTO() {
    }

    public ProductParameterDTO(Long productParameterId, ProductParameterGroupRelationalDTO productParameterGroup, String title, String description, String type, String options) {
        this.productParameterId = productParameterId;
        this.productParameterGroup = productParameterGroup;
        this.title = title;
        this.description = description;
        this.type = type;
        this.options = options;
    }

    public Long getProductParameterId() {
        return productParameterId;
    }

    public void setProductParameterId(Long productParameterId) {
        this.productParameterId = productParameterId;
    }

    public ProductParameterGroupRelationalDTO getProductParameterGroup() {
        return productParameterGroup;
    }

    public void setProductParameterGroup(ProductParameterGroupRelationalDTO productParameterGroup) {
        this.productParameterGroup = productParameterGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
