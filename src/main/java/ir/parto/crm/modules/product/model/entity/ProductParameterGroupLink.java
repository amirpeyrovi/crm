package ir.parto.crm.modules.product.model.entity;

import ir.parto.crm.modules.product.controller.transientObject.productParameterGroupLink.ProductParameterGroupLinkDTO;
import ir.parto.crm.modules.product.controller.transientObject.productParameterGroupLink.ProductParameterGroupLinkInfoDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_product_parameter_group_link")
public class ProductParameterGroupLink implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    private Long productParameterGroupLinkId;

    @ManyToOne
    @JoinColumn(name = "parameter_id", foreignKey = @ForeignKey(name = "product_parameter_group_link_product_parameter_group_fk"))
    private ProductParameterGroup productParameterGroup;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "product_parameter_group_link_product_fk"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_addon_id", foreignKey = @ForeignKey(name = "product_parameter_group_link_product_addon_fk"))
    private ProductAddon productAddon;


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

    public ProductParameterGroupLink() {
    }

    public ProductParameterGroupLink(ProductParameterGroup productParameterGroup, Product product, ProductAddon productAddon, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
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

    public ProductParameterGroup getProductParameterGroup() {
        return productParameterGroup;
    }

    public void setProductParameterGroup(ProductParameterGroup productParameterGroup) {
        this.productParameterGroup = productParameterGroup;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductAddon getProductAddon() {
        return productAddon;
    }

    public void setProductAddon(ProductAddon productAddon) {
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

    public ProductParameterGroupLinkInfoDTO convert2InfoObject() {
        ProductParameterGroupLinkInfoDTO dto = new ProductParameterGroupLinkInfoDTO();
        if (this.productParameterGroupLinkId != null)
            dto.setProductParameterGroupLinkId(this.productParameterGroupLinkId);
        if (this.productParameterGroup != null)
            dto.setProductParameterGroup(this.productParameterGroup.convert2RelationalDTO());
        if (this.product != null) dto.setProduct(this.product.convert2RelationalObject());
        if (this.productAddon != null) dto.setProductAddon(this.productAddon.convert2RelationalObject());
        if (this.createdBy != null) dto.setCreatedBy(this.createdBy);
        if (this.updatedBy != null) dto.setUpdatedBy(this.updatedBy);
        if (this.deletedBy != null) dto.setDeletedBy(this.deletedBy);
        if (this.createdDate != null) dto.setCreatedDate(this.createdDate);
        if (this.updatedDate != null) dto.setUpdatedDate(this.updatedDate);
        if (this.deletedDate != null) dto.setDeletedDate(this.deletedDate);
        if (this.isDeleted != null) dto.setIsDeleted(this.isDeleted);

        return dto;
    }

    public ProductParameterGroupLinkDTO convert2Object() {
        ProductParameterGroupLinkDTO dto = new ProductParameterGroupLinkDTO();
        if (this.productParameterGroupLinkId != null)
            dto.setProductParameterGroupLinkId(this.productParameterGroupLinkId);
        if (this.productParameterGroup != null)
            dto.setProductParameterGroup(this.productParameterGroup.convert2RelationalDTO());
        if (this.product != null) dto.setProduct(this.product.convert2RelationalObject());
        if (this.productAddon != null) dto.setProductAddon(this.productAddon.convert2RelationalObject());
        return dto;
    }
}
