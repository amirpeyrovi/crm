package ir.parto.crm.modules.promotion.controller.transientObject.promotionCodeProduct;

import ir.parto.crm.modules.product.controller.transientObject.product.ProductRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productAddon.ProductAddonRelationalDTO;
import ir.parto.crm.modules.promotion.controller.transientObject.promotionCode.PromotionCodeRelationalDTO;

public class PromotionCodeProductDTO {
    private Long promotionCodeProductId;
    private ProductRelationalDTO product;
    private ProductAddonRelationalDTO productAddon;
    private PromotionCodeRelationalDTO promotionCode;

    public PromotionCodeProductDTO() {
    }

    public PromotionCodeProductDTO(Long promotionCodeProductId, ProductRelationalDTO product, ProductAddonRelationalDTO productAddon, PromotionCodeRelationalDTO promotionCode) {
        this.promotionCodeProductId = promotionCodeProductId;
        this.product = product;
        this.productAddon = productAddon;
        this.promotionCode = promotionCode;
    }

    public Long getPromotionCodeProductId() {
        return promotionCodeProductId;
    }

    public void setPromotionCodeProductId(Long promotionCodeProductId) {
        this.promotionCodeProductId = promotionCodeProductId;
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

    public PromotionCodeRelationalDTO getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(PromotionCodeRelationalDTO promotionCode) {
        this.promotionCode = promotionCode;
    }
}
