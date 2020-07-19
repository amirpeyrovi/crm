package ir.parto.crm.modules.order.controller.objects;

public class RenewOrderDTO {
    private Long serviceId;
    private String promotionCode;

    public RenewOrderDTO() {
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }
}
