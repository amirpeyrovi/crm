package ir.parto.crm.modules.promotion.controller.transientObject.promotionCodeProduct;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.promotion.model.entity.PromotionCode;
import ir.parto.crm.modules.promotion.model.entity.PromotionCodeProduct;

public class PromotionCodeProductAddDTO {
    private Long productId;
    private Long productAddonId;
    private Long promotionCodeId;

    public PromotionCodeProductAddDTO() {
    }

    public PromotionCodeProductAddDTO(Long productId, Long productAddonId, Long promotionCodeId) {
        this.productId = productId;
        this.productAddonId = productAddonId;
        this.promotionCodeId = promotionCodeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductAddonId() {
        return productAddonId;
    }

    public void setProductAddonId(Long productAddonId) {
        this.productAddonId = productAddonId;
    }

    public Long getPromotionCodeId() {
        return promotionCodeId;
    }

    public void setPromotionCodeId(Long promotionCodeId) {
        this.promotionCodeId = promotionCodeId;
    }

    public PromotionCodeProduct convert2Object() {
        PromotionCodeProduct obj = new PromotionCodeProduct();
        if (this.productId != null) obj.setProduct(new Product(this.productId));
        if (this.productAddonId != null) obj.setProductAddon(new ProductAddon(this.productAddonId));
        if (this.promotionCodeId != null) obj.setPromotionCode(new PromotionCode(this.promotionCodeId));
        return obj;
    }
}
