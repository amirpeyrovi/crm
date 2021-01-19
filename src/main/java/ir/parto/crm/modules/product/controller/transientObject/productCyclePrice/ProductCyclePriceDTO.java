package ir.parto.crm.modules.product.controller.transientObject.productCyclePrice;

import ir.parto.crm.modules.product.controller.transientObject.product.ProductRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productAddon.ProductAddonRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productCycle.ProductCycleRelationalDTO;

public class ProductCyclePriceDTO {
    private Long productCyclePriceId;
    private ProductCycleRelationalDTO productCycle;
    private ProductRelationalDTO product;
    private ProductAddonRelationalDTO productAddon;
    private Long setupPrice;
    private Long price;

    public ProductCyclePriceDTO() {
    }

    public ProductCyclePriceDTO(Long productCyclePriceId, ProductCycleRelationalDTO productCycle, ProductRelationalDTO product, ProductAddonRelationalDTO productAddon, Long setupPrice, Long price) {
        this.productCyclePriceId = productCyclePriceId;
        this.productCycle = productCycle;
        this.product = product;
        this.productAddon = productAddon;
        this.setupPrice = setupPrice;
        this.price = price;
    }

    public Long getProductCyclePriceId() {
        return productCyclePriceId;
    }

    public void setProductCyclePriceId(Long productCyclePriceId) {
        this.productCyclePriceId = productCyclePriceId;
    }

    public ProductCycleRelationalDTO getProductCycle() {
        return productCycle;
    }

    public void setProductCycle(ProductCycleRelationalDTO productCycle) {
        this.productCycle = productCycle;
    }

    public ProductRelationalDTO getProduct() {
        return product;
    }

    public void setProduct(ProductRelationalDTO product) {
        this.product = product;
    }

    public ProductAddonRelationalDTO getProductAddon() {
        return productAddon;
    }

    public void setProductAddon(ProductAddonRelationalDTO productAddon) {
        this.productAddon = productAddon;
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
}
