package ir.parto.crm.modules.product.controller.transientObject.productCycle;

import ir.parto.crm.modules.product.model.entity.ProductCycle;

public class ProductCycleAddDTO {
    private String title;
    private Integer days;
    private Integer month;
    private Integer isOneTime;
    // status => [1: postPaid, 2: prePaid]
    private Integer paidType;
    private Integer isAutoRenew;
    // status => [1: official, 2: unofficial]
    private Integer officialType;

    public ProductCycleAddDTO() {
    }

    public ProductCycleAddDTO(String title, Integer days, Integer month, Integer isOneTime, Integer paidType, Integer isAutoRenew, Integer officialType) {
        this.title = title;
        this.days = days;
        this.month = month;
        this.isOneTime = isOneTime;
        this.paidType = paidType;
        this.isAutoRenew = isAutoRenew;
        this.officialType = officialType;
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

    public ProductCycle convert2Object() {
        ProductCycle dto = new ProductCycle();
        if (this.title != null) dto.setTitle(this.title);
        if (this.days != null) dto.setDays(this.days);
        if (this.month != null) dto.setMonth(this.month);
        if (this.isOneTime != null) dto.setIsOneTime(this.isOneTime);
        if (this.paidType != null) dto.setPaidType(this.paidType);
        if (this.isAutoRenew != null) dto.setIsAutoRenew(this.isAutoRenew);
        if (this.officialType != null) dto.setOfficialType(this.officialType);
        return dto;
    }
}
