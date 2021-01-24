package ir.parto.crm.modules.product.controller.transientObject.productServerParameterValue;

import ir.parto.crm.modules.product.controller.transientObject.product.ProductRelationalDTO;
import ir.parto.crm.modules.product.controller.transientObject.productAddon.ProductAddonRelationalDTO;
import ir.parto.crm.modules.server.controller.transientObject.serverParameter.ServerParameterRelationalDTO;

import java.time.LocalDateTime;

public class ProductServerParameterValueInfoDTO {
    private Long productServerParameterId;
    private ProductRelationalDTO product;
    private ProductAddonRelationalDTO productAddon;
    private ServerParameterRelationalDTO serverParameter;
    private String value;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private Integer isDeleted;

    public ProductServerParameterValueInfoDTO() {
    }

    public ProductServerParameterValueInfoDTO(Long productServerParameterId, ProductRelationalDTO product, ProductAddonRelationalDTO productAddon, ServerParameterRelationalDTO serverParameter, String value, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.productServerParameterId = productServerParameterId;
        this.product = product;
        this.productAddon = productAddon;
        this.serverParameter = serverParameter;
        this.value = value;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getProductServerParameterId() {
        return productServerParameterId;
    }

    public void setProductServerParameterId(Long productServerParameterId) {
        this.productServerParameterId = productServerParameterId;
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

    public ServerParameterRelationalDTO getServerParameter() {
        return serverParameter;
    }

    public void setServerParameter(ServerParameterRelationalDTO serverParameter) {
        this.serverParameter = serverParameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
