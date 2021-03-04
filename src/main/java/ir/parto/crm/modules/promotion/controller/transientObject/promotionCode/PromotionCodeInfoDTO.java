package ir.parto.crm.modules.promotion.controller.transientObject.promotionCode;

import ir.parto.crm.modules.client.controller.transientObject.client.ClientRelationalDTO;

import java.time.LocalDateTime;

public class PromotionCodeInfoDTO {
    private Long promotionCodeId;
    private ClientRelationalDTO client;
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
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private Integer isDeleted;

    public PromotionCodeInfoDTO() {
    }

    public PromotionCodeInfoDTO(Long promotionCodeId, ClientRelationalDTO client, String promotionCode, String promotionName, Integer type, Integer orderType, Long value, Integer maxUsage, Integer usage, LocalDateTime validUntilDate, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.promotionCodeId = promotionCodeId;
        this.client = client;
        this.promotionCode = promotionCode;
        this.promotionName = promotionName;
        this.type = type;
        this.orderType = orderType;
        this.value = value;
        this.maxUsage = maxUsage;
        this.usage = usage;
        this.validUntilDate = validUntilDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getPromotionCodeId() {
        return promotionCodeId;
    }

    public void setPromotionCodeId(Long promotionCodeId) {
        this.promotionCodeId = promotionCodeId;
    }

    public ClientRelationalDTO getClient() {
        return client;
    }

    public void setClient(ClientRelationalDTO client) {
        this.client = client;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
