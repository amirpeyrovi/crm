package ir.parto.crm.modules.reseller.controller.transientObject.resellerCommission;

import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.modules.reseller.model.entity.Reseller;
import ir.parto.crm.modules.reseller.model.entity.ResellerCommission;

public class ResellerCommissionEditDTO {
    private Long resellerId;
    private Long productGroupId;
    private Integer percentage;

    public ResellerCommissionEditDTO() {
    }

    public ResellerCommissionEditDTO(Long resellerId, Long productGroupId, Integer percentage) {
        this.resellerId = resellerId;
        this.productGroupId = productGroupId;
        this.percentage = percentage;
    }

    public Long getResellerId() {
        return resellerId;
    }

    public void setResellerId(Long resellerId) {
        this.resellerId = resellerId;
    }

    public Long getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(Long productGroupId) {
        this.productGroupId = productGroupId;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public ResellerCommission convert2Object() {
        ResellerCommission obj = new ResellerCommission();
        if (this.resellerId != null) obj.setReseller(new Reseller(this.resellerId));
        if (this.productGroupId != null) obj.setProductGroup(new ProductGroup(this.productGroupId));
        if (this.percentage != null) obj.setPercentage(this.percentage);
        return obj;
    }
}
