package ir.parto.crm.modules.order.controller.objects;

public class AddNewOrderDTO {
    private Long resellerId;
    private Long clientId;
    private Long productId;
    private Long productCycleId;
    private String promotionCode;

    public AddNewOrderDTO() {
    }

    public Long getResellerId() {
        return resellerId;
    }

    public void setResellerId(Long resellerId) {
        this.resellerId = resellerId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductCycleId() {
        return productCycleId;
    }

    public void setProductCycleId(Long productCycleId) {
        this.productCycleId = productCycleId;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }
}
