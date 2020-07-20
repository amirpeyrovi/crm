package ir.parto.crm.modules.order.controller.objects;

import java.util.Map;

public class AddNewOrderDTO {
    private Long resellerId;
    private Long clientId;
    private Long productId;
    private Long productCycleId;
    private String promotionCode;
    private Map<Long, String> serviceProductParameterMap;

    public AddNewOrderDTO() {
    }

    public Long getResellerId() {
        return resellerId;
    }

    public AddNewOrderDTO setResellerId(Long resellerId) {
        this.resellerId = resellerId;
        return this;
    }

    public Long getClientId() {
        return clientId;
    }

    public AddNewOrderDTO setClientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public AddNewOrderDTO setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public Long getProductCycleId() {
        return productCycleId;
    }

    public AddNewOrderDTO setProductCycleId(Long productCycleId) {
        this.productCycleId = productCycleId;
        return this;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public AddNewOrderDTO setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
        return this;
    }

    public Map<Long, String> getServiceProductParameterMap() {
        return serviceProductParameterMap;
    }

    public AddNewOrderDTO setServiceProductParameterMap(Map<Long, String> serviceProductParameterMap) {
        this.serviceProductParameterMap = serviceProductParameterMap;
        return this;
    }
}
