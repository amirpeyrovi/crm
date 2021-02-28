package ir.parto.crm.modules.promotion.controller.transientObject.promotionCodeProduct;

public class PromotionCodeProductEditDTO {
    private Long promotionCodeProductId;
    private Long productId;
    private Long productAddonId;
    private Long promotionCodeId;

    public PromotionCodeProductEditDTO() {
    }

    public PromotionCodeProductEditDTO(Long promotionCodeProductId, Long productId, Long productAddonId, Long promotionCodeId) {
        this.promotionCodeProductId = promotionCodeProductId;
        this.productId = productId;
        this.productAddonId = productAddonId;
        this.promotionCodeId = promotionCodeId;
    }

    public Long getPromotionCodeProductId() {
        return promotionCodeProductId;
    }

    public void setPromotionCodeProductId(Long promotionCodeProductId) {
        this.promotionCodeProductId = promotionCodeProductId;
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
}
