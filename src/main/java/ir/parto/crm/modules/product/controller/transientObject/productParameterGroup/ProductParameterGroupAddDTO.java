package ir.parto.crm.modules.product.controller.transientObject.productParameterGroup;

import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;

public class ProductParameterGroupAddDTO {
    private String title;

    public ProductParameterGroupAddDTO() {
    }

    public ProductParameterGroupAddDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProductParameterGroup convert2Object() {
        ProductParameterGroup productParameterGroup = new ProductParameterGroup();
        if (this.title != null) productParameterGroup.setTitle(this.title);
        return productParameterGroup;
    }
}
