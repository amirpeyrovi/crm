package ir.parto.crm.modules.product.controller.transientObject.productCyclePrice;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductCycle;
import ir.parto.crm.modules.product.model.entity.ProductCyclePrice;

public class ProductCyclePriceProductAddDTO {
    private Long productCycleId;
    private Long productId;
    private Long setupPrice;
    private Long price;

    public ProductCyclePriceProductAddDTO() {
    }

    public ProductCyclePriceProductAddDTO(Long productCycleId, Long productId, Long setupPrice, Long price) {
        this.productCycleId = productCycleId;
        this.productId = productId;
        this.setupPrice = setupPrice;
        this.price = price;
    }

    public Long getProductCycleId() {
        return productCycleId;
    }

    public void setProductCycleId(Long productCycleId) {
        this.productCycleId = productCycleId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSetupPrice() {
        return setupPrice;
    }

    public void setSetupPrice(Long setupPrice) {
        this.setupPrice = setupPrice;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public ProductCyclePrice convert2Object() {
        ProductCyclePrice productCyclePrice = new ProductCyclePrice();
        if (this.productCycleId != null) productCyclePrice.setProductCycle(new ProductCycle(this.productCycleId));
        if (this.productId != null) productCyclePrice.setProduct(new Product(this.productId));
        if (this.setupPrice != null) productCyclePrice.setSetupPrice(this.setupPrice);
        if (this.price != null) productCyclePrice.setPrice(this.price);
        return productCyclePrice;
    }
}
