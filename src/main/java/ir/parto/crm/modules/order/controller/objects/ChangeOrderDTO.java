package ir.parto.crm.modules.order.controller.objects;

import java.util.Map;

public class ChangeOrderDTO {
    private Long serviceId;
    private Long productId;
    private Long productCycleId;
    private String promotionCode;
    private Long changeTime;
    private Map<Long, String> serviceProductParameterMap;

    public ChangeOrderDTO() {
    }

    public Long getServiceId() {
        return serviceId;
    }

    public ChangeOrderDTO setServiceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public ChangeOrderDTO setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public Long getProductCycleId() {
        return productCycleId;
    }

    public ChangeOrderDTO setProductCycleId(Long productCycleId) {
        this.productCycleId = productCycleId;
        return this;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public ChangeOrderDTO setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
        return this;
    }

    public Long getChangeTime() {
        return changeTime;
    }

    public ChangeOrderDTO setChangeTime(Long changeTime) {
        this.changeTime = changeTime;
        return this;
    }

    public Map<Long, String> getServiceProductParameterMap() {
        return serviceProductParameterMap;
    }

    public ChangeOrderDTO setServiceProductParameterMap(Map<Long, String> serviceProductParameterMap) {
        this.serviceProductParameterMap = serviceProductParameterMap;
        return this;
    }
}
