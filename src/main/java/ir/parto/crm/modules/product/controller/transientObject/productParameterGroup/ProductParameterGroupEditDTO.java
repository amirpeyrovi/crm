package ir.parto.crm.modules.product.controller.transientObject.productParameterGroup;

import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;

public class ProductParameterGroupEditDTO {
    private String title;

    public ProductParameterGroupEditDTO() {
    }

    public ProductParameterGroupEditDTO(String title) {
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
