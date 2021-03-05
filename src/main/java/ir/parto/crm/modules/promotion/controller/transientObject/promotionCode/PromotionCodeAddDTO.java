package ir.parto.crm.modules.promotion.controller.transientObject.promotionCode;

import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.promotion.model.entity.PromotionCode;

import java.time.LocalDateTime;

public class PromotionCodeAddDTO {
    private Long clientId;
    private String promotionCode;
    private String promotionName;
    // type => [1: percentage, 2: fix amount]
    private Integer type = 1;
    // type => [1: buy, 2: renew, 3: change, all: 4]
    private Integer orderType = 4;
    private Long value;
    private Integer maxUsage;
    private Integer usage;
    private LocalDateTime validUntilDate;

    public PromotionCodeAddDTO() {
    }

    public PromotionCodeAddDTO(Long clientId, String promotionCode, String promotionName, Integer type, Integer orderType, Long value, Integer maxUsage, Integer usage, LocalDateTime validUntilDate) {
        this.clientId = clientId;
        this.promotionCode = promotionCode;
        this.promotionName = promotionName;
        this.type = type;
        this.orderType = orderType;
        this.value = value;
        this.maxUsage = maxUsage;
        this.usage = usage;
        this.validUntilDate = validUntilDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Integer getMaxUsage() {
        return maxUsage;
    }

    public void setMaxUsage(Integer maxUsage) {
        this.maxUsage = maxUsage;
    }

    public Integer getUsage() {
        return usage;
    }

    public void setUsage(Integer usage) {
        this.usage = usage;
    }

    public LocalDateTime getValidUntilDate() {
        return validUntilDate;
    }

    public void setValidUntilDate(LocalDateTime validUntilDate) {
        this.validUntilDate = validUntilDate;
    }

    public PromotionCode convert2Object() {
        PromotionCode obj = new PromotionCode();
        if (this.clientId != null) obj.setClient(new Client(this.clientId));
        if (this.promotionCode != null) obj.setPromotionCode(this.promotionCode);
        if (this.promotionName != null) obj.setPromotionName(this.promotionName);
        if (this.type != null) obj.setType(this.type);
        if (this.value != null) obj.setValue(this.value);
        if (this.maxUsage != null) obj.setMaxUsage(this.maxUsage);
        if (this.usage != null) obj.setUsage(this.usage);
        if (this.validUntilDate != null) obj.setValidUntilDate(this.validUntilDate);
        return obj;

    }
}
