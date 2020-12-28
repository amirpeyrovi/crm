package ir.parto.crm.modules.product.controller.transientObject.ProductCycle;

public class ProductCycleRelationalDTO {
    private Long productCycleId;
    private String title;

    public ProductCycleRelationalDTO() {
    }

    public ProductCycleRelationalDTO(Long productCycleId, String title) {
        this.productCycleId = productCycleId;
        this.title = title;
    }

    public Long getProductCycleId() {
        return productCycleId;
    }

    public ProductCycleRelationalDTO setProductCycleId(Long productCycleId) {
        this.productCycleId = productCycleId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ProductCycleRelationalDTO setTitle(String title) {
        this.title = title;
        return this;
    }
}
