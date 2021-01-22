package ir.parto.crm.modules.product.model.entity;

import ir.parto.crm.modules.product.controller.transientObject.productParameter.ProductParameterDTO;
import ir.parto.crm.modules.product.controller.transientObject.productParameter.ProductParameterInfoDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_product_parameter")
public class ProductParameter implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    private Long productParameterId;

    @ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "product_parameter_product_parameter_group_fk"))
    private ProductParameterGroup productParameterGroup;

    @Column(name = "title", columnDefinition = "nvarchar2(100)")
    private String title;

    @Column(name = "description", columnDefinition = "nvarchar2(200)")
    private String description;

    @Column(name = "type", columnDefinition = "nvarchar2(16)")
    private String type;

    @Column(name = "options", columnDefinition = "nvarchar2(500)")
    private String options;


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

    public ProductParameter() {
    }

    public ProductParameter(ProductParameterGroup productParameterGroup, String title, String description, String type, String options, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.productParameterGroup = productParameterGroup;
        this.title = title;
        this.description = description;
        this.type = type;
        this.options = options;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public Long getProductParameterId() {
        return productParameterId;
    }

    public void setProductParameterId(Long productParameterId) {
        this.productParameterId = productParameterId;
    }

    public ProductParameterGroup getProductParameterGroup() {
        return productParameterGroup;
    }

    public void setProductParameterGroup(ProductParameterGroup productParameterGroup) {
        this.productParameterGroup = productParameterGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
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

    public ProductParameterDTO convert2Object() {
        ProductParameterDTO dto = new ProductParameterDTO();
        if (this.productParameterId != null) dto.setProductParameterId(this.productParameterId);
        if (this.productParameterGroup != null)
            dto.setProductParameterGroup(this.productParameterGroup.convert2RelationalDTO());
        if (this.title != null) dto.setProductParameterId(this.productParameterId);
        if (this.description != null) dto.setDescription(this.description);
        if (this.type != null) dto.setType(this.type);
        if (this.options != null) dto.setOptions(this.options);
        return dto;
    }

    public ProductParameterInfoDTO convert2InfoObject() {
        ProductParameterInfoDTO dto = new ProductParameterInfoDTO();
        if (this.productParameterId != null) dto.setProductParameterId(this.productParameterId);
        if (this.productParameterGroup != null)
            dto.setProductParameterGroup(this.productParameterGroup.convert2RelationalDTO());
        if (this.title != null) dto.setTitle(this.title);
        if (this.description != null) dto.setDescription(this.description);
        if (this.type != null) dto.setType(this.type);
        if (this.options != null) dto.setOptions(this.options);
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
