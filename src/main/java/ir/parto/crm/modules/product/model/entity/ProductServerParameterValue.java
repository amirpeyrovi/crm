package ir.parto.crm.modules.product.model.entity;

import ir.parto.crm.modules.server.model.entity.ServerParameter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_product_server_parameter_value")
public class ProductServerParameterValue implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    private Long productServerParameterId;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "product_server_parameter_value_product_fk"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "server_parameter_id", foreignKey = @ForeignKey(name = "product_server_parameter_value_server_parameter_fk"))
    private ServerParameter serverParameter;

    @Column(name = "value", columnDefinition = "nvarchar2(100)")
    private String value;


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
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private LocalDateTime isDeleted;

    public ProductServerParameterValue() {
    }

    public ProductServerParameterValue(Product product, ServerParameter serverParameter, String value, String createdBy, LocalDateTime createdDate) {
        this.product = product;
        this.serverParameter = serverParameter;
        this.value = value;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Long getProductServerParameterId() {
        return productServerParameterId;
    }

    public void setProductServerParameterId(Long productServerParameterId) {
        this.productServerParameterId = productServerParameterId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ServerParameter getServerParameter() {
        return serverParameter;
    }

    public void setServerParameter(ServerParameter serverParameter) {
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public ProductServerParameterValue setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public ProductServerParameterValue setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public ProductServerParameterValue setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public ProductServerParameterValue setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public LocalDateTime getIsDeleted() {
        return isDeleted;
    }

    public ProductServerParameterValue setIsDeleted(LocalDateTime isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }
}
