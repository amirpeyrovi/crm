package ir.parto.crm.modules.product.controller.transientObject.productParameterGroupLink;

import ir.parto.crm.modules.product.controller.transientObject.product.ProductRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productAddon.ProductAddonRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productParameterGroup.ProductParameterGroupRelationalDTO;

import java.time.LocalDateTime;

public class ProductParameterGroupLinkInfoDTO {
    private Long productParameterGroupLinkId;
    private ProductParameterGroupRelationalDTO productParameterGroup;
    private ProductRelationalDTO product;
    private ProductAddonRelationalDTO productAddon;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private Integer isDeleted;

    public ProductParameterGroupLinkInfoDTO() {
    }


    public ProductParameterGroupLinkInfoDTO(Long productParameterGroupLinkId, ProductParameterGroupRelationalDTO productParameterGroup, ProductRelationalDTO product, ProductAddonRelationalDTO productAddon, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.productParameterGroupLinkId = productParameterGroupLinkId;
        this.productParameterGroup = productParameterGroup;
        this.product = product;
        this.productAddon = productAddon;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getProductParameterGroupLinkId() {
        return productParameterGroupLinkId;
    }

    public void setProductParameterGroupLinkId(Long productParameterGroupLinkId) {
        this.productParameterGroupLinkId = productParameterGroupLinkId;
    }

    public ProductParameterGroupRelationalDTO getProductParameterGroup() {
        return productParameterGroup;
    }

    public void setProductParameterGroup(ProductParameterGroupRelationalDTO productParameterGroup) {
        this.productParameterGroup = productParameterGroup;
    }

    public ProductRelationalDTO getProduct() {
        return product;
    }

    public void setProduct(ProductRelationalDTO product) {
        this.product = product;
    }

    public ProductAddonRelationalDTO getProductAddon() {
        return productAddon;
    }

    public void setProductAddon(ProductAddonRelationalDTO productAddon) {
        this.productAddon = productAddon;
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
