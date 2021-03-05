package ir.parto.crm.modules.promotion.model.entity;

import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.promotion.controller.transientObject.promotionCode.PromotionCodeDTO;
import ir.parto.crm.modules.promotion.controller.transientObject.promotionCode.PromotionCodeInfoDTO;
import ir.parto.crm.modules.promotion.controller.transientObject.promotionCode.PromotionCodeRelationalDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_promotion_code")
public class PromotionCode implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "crm_promotion_seq", sequenceName = "crm_promotion_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "crm_promotion_seq")
    private Long promotionCodeId;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "promotion_code_client_fk"))
    private Client client;

    @Column(name = "promotion_code", columnDefinition = "nvarchar2(32)")
    private String promotionCode;

    @Column(name = "promotion_name", columnDefinition = "nvarchar2(32)")
    private String promotionName;

    // type => [1: percentage, 2: fix amount]
    @Column(name = "type", columnDefinition = "number(1)")
    private Integer type = 1;

    // type => [1: buy, 2: renew, 3: change, all: 4]
    @Column(name = "order_type", columnDefinition = "number(1)")
    private Integer orderType = 4;

    @Column(name = "value", columnDefinition = "number(10)")
    private Long value;

    @Column(name = "max_usage", columnDefinition = "number(5)")
    private Integer maxUsage;

    @Column(name = "usage", columnDefinition = "number(5)")
    private Integer usage;

    @Column(name = "valid_until_date", columnDefinition = "date")
    private LocalDateTime validUntilDate;


    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "TIMESTAMP(6)")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_at", columnDefinition = "TIMESTAMP(6)")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime deletedDate;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private Integer isDeleted;

    public PromotionCode() {
    }

    public PromotionCode(Client client, String promotionCode, String promotionName, Integer type, Integer orderType, Long value, Integer maxUsage, Integer usage, LocalDateTime validUntilDate, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
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

    public PromotionCode(Long promotionCodeId) {
        this.promotionCodeId = promotionCodeId;
    }

    public Long getPromotionCodeId() {
        return promotionCodeId;
    }

    public void setPromotionCodeId(Long promotionCodeId) {
        this.promotionCodeId = promotionCodeId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
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

    public PromotionCodeRelationalDTO convert2RelationalObject() {
        PromotionCodeRelationalDTO dto = new PromotionCodeRelationalDTO();
        if (this.promotionCodeId != null) dto.setPromotionCodeId(this.promotionCodeId);
        if (this.client != null) dto.setClient(this.client.convert2RelationalObject());
        if (this.promotionCode != null) dto.setPromotionCode(this.promotionCode);
        if (this.promotionName != null) dto.setPromotionName(this.promotionName);
        if (this.type != null) dto.setType(this.type);
        if (this.orderType != null) dto.setOrderType(this.orderType);
        if (this.value != null) dto.setValue(this.value);
        if (this.maxUsage != null) dto.setMaxUsage(this.maxUsage);
        if (this.usage != null) dto.setUsage(this.usage);
        if (this.validUntilDate != null) dto.setValidUntilDate(this.validUntilDate);
        return dto;
    }

    public PromotionCodeDTO convert2Object() {
        PromotionCodeDTO dto = new PromotionCodeDTO();
        if (this.promotionCodeId != null) dto.setPromotionCodeId(this.promotionCodeId);
        if (this.client != null) dto.setClient(this.client.convert2RelationalObject());
        if (this.promotionCode != null) dto.setPromotionCode(this.promotionCode);
        if (this.promotionName != null) dto.setPromotionName(this.promotionName);
        if (this.type != null) dto.setType(this.type);
        if (this.orderType != null) dto.setOrderType(this.orderType);
        if (this.value != null) dto.setValue(this.value);
        if (this.maxUsage != null) dto.setMaxUsage(this.maxUsage);
        if (this.usage != null) dto.setUsage(this.usage);
        if (this.validUntilDate != null) dto.setValidUntilDate(this.validUntilDate);
        return dto;
    }

    public PromotionCodeInfoDTO convert2InfoObject() {
        PromotionCodeInfoDTO dto = new PromotionCodeInfoDTO();
        if (this.promotionCodeId != null) dto.setPromotionCodeId(this.promotionCodeId);
        if (this.client != null) dto.setClient(this.client.convert2RelationalObject());
        if (this.promotionCode != null) dto.setPromotionCode(this.promotionCode);
        if (this.promotionName != null) dto.setPromotionName(this.promotionName);
        if (this.type != null) dto.setType(this.type);
        if (this.orderType != null) dto.setOrderType(this.orderType);
        if (this.value != null) dto.setValue(this.value);
        if (this.maxUsage != null) dto.setMaxUsage(this.maxUsage);
        if (this.usage != null) dto.setUsage(this.usage);
        if (this.validUntilDate != null) dto.setValidUntilDate(this.validUntilDate);
        if (this.createdBy != null) dto.setCreatedBy(this.createdBy);
        if (this.updatedBy != null) dto.setUpdatedBy(this.updatedBy);
        if (this.deletedBy != null) dto.setDeletedBy(this.deletedBy);
        if (this.createdDate != null) dto.setCreatedDate(this.createdDate);
        if (this.updatedDate != null) dto.setUpdatedDate(this.updatedDate);
        if (this.deletedDate != null) dto.setDeletedDate(this.deletedDate);
        if (this.isDeleted != null) dto.setIsDeleted(this.isDeleted);
        return dto;
    }
}
