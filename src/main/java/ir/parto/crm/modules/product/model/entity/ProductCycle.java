package ir.parto.crm.modules.product.model.entity;

import ir.parto.crm.modules.product.controller.transientObject.ProductCycle.ProductCycleRelationalDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_product_cycle")
public class ProductCycle implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    private Long productCycleId;

    @Column(name = "title", columnDefinition = "nvarchar2(32)")
    private String title;

    @Column(name = "days", columnDefinition = "number")
    private Integer days;

    @Column(name = "month", columnDefinition = "number")
    private Integer month;

    @Column(name = "is_one_time", columnDefinition = "number(1)")
    private Integer isOneTime;

    // status => [1: postPaid, 2: prePaid]
    @Column(name = "paid_type", columnDefinition = "number(1)")
    private Integer paidType;

    @Column(name = "is_auto_renew", columnDefinition = "number(1)")
    private Integer isAutoRenew;

    // status => [1: official, 2: unofficial]
    @Column(name = "official_type", columnDefinition = "number(1)")
    private Integer officialType;


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

    public ProductCycle() {
    }

    public ProductCycle(String title, Integer days, Integer month, Integer isOneTime, Integer paidType, Integer isAutoRenew, Integer officialType, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.title = title;
        this.days = days;
        this.month = month;
        this.isOneTime = isOneTime;
        this.paidType = paidType;
        this.isAutoRenew = isAutoRenew;
        this.officialType = officialType;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getProductCycleId() {
        return productCycleId;
    }

    public void setProductCycleId(Long productCycleId) {
        this.productCycleId = productCycleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getIsOneTime() {
        return isOneTime;
    }

    public void setIsOneTime(Integer isOneTime) {
        this.isOneTime = isOneTime;
    }

    public Integer getPaidType() {
        return paidType;
    }

    public void setPaidType(Integer paidType) {
        this.paidType = paidType;
    }

    public Integer getIsAutoRenew() {
        return isAutoRenew;
    }

    public void setIsAutoRenew(Integer isAutoRenew) {
        this.isAutoRenew = isAutoRenew;
    }

    public Integer getOfficialType() {
        return officialType;
    }

    public void setOfficialType(Integer officialType) {
        this.officialType = officialType;
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

    public ProductCycleRelationalDTO convert2RelationalObject() {
        ProductCycleRelationalDTO dto = new ProductCycleRelationalDTO();
        if (this.productCycleId != null) dto.setProductCycleId(this.productCycleId);
        if (this.title != null) dto.setTitle(this.title);
        return dto;
    }
}
