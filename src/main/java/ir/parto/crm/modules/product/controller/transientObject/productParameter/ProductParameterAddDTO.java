package ir.parto.crm.modules.product.controller.transientObject.productParameter;

import ir.parto.crm.modules.product.model.entity.ProductParameter;
import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;

public class ProductParameterAddDTO {
    private Long productParameterGroupId;
    private String title;
    private String description;
    private String type;
    private String options;

    public ProductParameterAddDTO() {
    }

    public ProductParameterAddDTO(Long productParameterGroupId, String title, String description, String type, String options) {
        this.productParameterGroupId = productParameterGroupId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.options = options;
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

    public ProductParameter convert2Object() {
        ProductParameter productParameter = new ProductParameter();
        if (this.productParameterGroupId != null)
            productParameter.setProductParameterGroup(new ProductParameterGroup(this.productParameterGroupId));
        if (this.title != null) productParameter.setTitle(this.title);
        if (this.description != null) productParameter.setDescription(this.description);
        if (this.type != null) productParameter.setType(this.type);
        if (this.options != null) productParameter.setOptions(this.options);
        return productParameter;
    }
}
