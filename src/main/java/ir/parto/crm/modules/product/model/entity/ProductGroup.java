package ir.parto.crm.modules.product.model.entity;

import ir.parto.crm.modules.product.controller.transientObject.ProductGroup.ProductGroupDTO;
import ir.parto.crm.modules.product.controller.transientObject.ProductGroup.ProductGroupRelationalDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_product_group")
public class ProductGroup implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    private Long productGroupId;

    @ManyToOne
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "product_group_product_group_fk"))
    private ProductGroup productGroup;

    @Column(name = "title", columnDefinition = "nvarchar2(100)")
    private String title;

    @Column(name = "admin_description", columnDefinition = "nvarchar2(1000)")
    private String adminDescription;

    @Column(name = "client_description", columnDefinition = "nvarchar2(1000)")
    private String clientDescription;

    @Column(name = "cover", columnDefinition = "nvarchar2(256)")
    private String cover;

    @Column(name = "path", columnDefinition = "nvarchar2(256)")
    private String path;

    @Column(name = "lvl", columnDefinition = "number(6)")
    private Integer lvl;


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

    public ProductGroup() {
    }

    public ProductGroup(ProductGroup productGroup, String title, String adminDescription, String clientDescription,
                        String cover, String path, Integer lvl, String createdBy, String updatedBy, String deletedBy,
                        LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.productGroup = productGroup;
        this.title = title;
        this.adminDescription = adminDescription;
        this.clientDescription = clientDescription;
        this.cover = cover;
        this.path = path;
        this.lvl = lvl;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
    }

    public ProductGroup(Long productGroupId) {
        this.productGroupId = productGroupId;
    }

    public Long getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(Long productGroupId) {
        this.productGroupId = productGroupId;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdminDescription() {
        return adminDescription;
    }

    public void setAdminDescription(String adminDescription) {
        this.adminDescription = adminDescription;
    }

    public String getClientDescription() {
        return clientDescription;
    }

    public void setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPath() {
        return path;
    }

    public ProductGroup setPath(String path) {
        this.path = path;
        return this;
    }

    public Integer getlvl() {
        return lvl;
    }

    public ProductGroup setlvl(Integer lvl) {
        this.lvl = lvl;
        return this;
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

    public ProductGroupRelationalDTO convert2RelationalObject() {
        ProductGroupRelationalDTO dto = new ProductGroupRelationalDTO();
        if (this.productGroupId != null) dto.setProductGroupId(this.productGroupId);
        if (this.title != null) dto.setTitle(this.title);
        if (this.path != null) dto.setPath(this.path);
        if (this.lvl != null) dto.setLevel(this.lvl);
        return dto;

    }

    public ProductGroupDTO convert2Object() {
        ProductGroupDTO productGroupDTO = new ProductGroupDTO();
        if (this.productGroupId != null) productGroupDTO.setProductGroupId(this.productGroupId);
        if (this.productGroup != null) productGroupDTO.setProductGroup(this.productGroup.convert2RelationalObject());
        if (this.title != null) productGroupDTO.setTitle(this.title);
        if (this.adminDescription != null) productGroupDTO.setAdminDescription(this.adminDescription);
        if (this.clientDescription != null) productGroupDTO.setClientDescription(this.clientDescription);
        if (this.cover != null) productGroupDTO.setCover(this.cover);
        if (this.path != null) productGroupDTO.setPath(this.path);
        if (this.lvl != null) productGroupDTO.setLvl(this.lvl);
        return productGroupDTO;
    }
}
