package ir.parto.crm.modules.order.controller.objects;

import java.util.Map;

public class AddNewAddonOrder {
    private Long resellerId;
    private Long serviceId;
    private Long productAddonId;
    private Long productCycleId;
    private String promotionCode;
    private Map<Long, String> serviceProductParameterMap;

    public AddNewAddonOrder() {
    }

    public Long getResellerId() {
        return resellerId;
    }

    public AddNewAddonOrder setResellerId(Long resellerId) {
        this.resellerId = resellerId;
        return this;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public AddNewAddonOrder setServiceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public Long getProductAddonId() {
        return productAddonId;
    }

    public AddNewAddonOrder setProductAddonId(Long productAddonId) {
        this.productAddonId = productAddonId;
        return this;
    }

    public Long getProductCycleId() {
        return productCycleId;
    }

    public AddNewAddonOrder setProductCycleId(Long productCycleId) {
        this.productCycleId = productCycleId;
        return this;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public AddNewAddonOrder setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
        return this;
    }

    public Map<Long, String> getServiceProductParameterMap() {
        return serviceProductParameterMap;
    }

    public AddNewAddonOrder setServiceProductParameterMap(Map<Long, String> serviceProductParameterMap) {
        this.serviceProductParameterMap = serviceProductParameterMap;
        return this;
    }
}
