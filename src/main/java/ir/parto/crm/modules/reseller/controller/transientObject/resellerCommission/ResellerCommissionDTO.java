package ir.parto.crm.modules.reseller.controller.transientObject.resellerCommission;

import ir.parto.crm.modules.product.controller.transientObject.productGroup.ProductGroupRelationalDTO;
import ir.parto.crm.modules.reseller.controller.transientObject.reseller.ResellerRelationalDTO;

public class ResellerCommissionDTO {
    private Long resellerCommissionId;
    private ResellerRelationalDTO reseller;
    private ProductGroupRelationalDTO productGroup;
    private Integer percentage;

    public ResellerCommissionDTO() {
    }

    public ResellerCommissionDTO(Long resellerCommissionId, ResellerRelationalDTO reseller, ProductGroupRelationalDTO productGroup, Integer percentage) {
        this.resellerCommissionId = resellerCommissionId;
        this.reseller = reseller;
        this.productGroup = productGroup;
        this.percentage = percentage;
    }

    public Long getResellerCommissionId() {
        return resellerCommissionId;
    }

    public void setResellerCommissionId(Long resellerCommissionId) {
        this.resellerCommissionId = resellerCommissionId;
    }

    public ResellerRelationalDTO getReseller() {
        return reseller;
    }

    public void setReseller(ResellerRelationalDTO reseller) {
        this.reseller = reseller;
    }

    public ProductGroupRelationalDTO getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupRelationalDTO productGroup) {
        this.productGroup = productGroup;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
