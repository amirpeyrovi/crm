package ir.parto.crm.modules.service.model.entity;

import ir.parto.crm.modules.product.model.entity.ProductParameter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_service_product_parameter_value")
public class ServiceProductParameterValue implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "service_seq", sequenceName = "service_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "service_seq")
    private Long serviceProductParameterValueId;

    @ManyToOne
    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "service_product_parameter_value_service_fk"))
    private Service service;

    @ManyToOne
    @JoinColumn(name = "service_addon_id", foreignKey = @ForeignKey(name = "service_addon_product_parameter_value_service_fk"))
    private ServiceAddon serviceAddon;

    @ManyToOne
    @JoinColumn(name = "product_parameter_id", foreignKey = @ForeignKey(name = "service_product_parameter_value_product_parameter_fk"))
    private ProductParameter productParameter;

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
    private Integer isDeleted;

    public ServiceProductParameterValue() {
    }

    public ServiceProductParameterValue(Service service, ServiceAddon serviceAddon, ProductParameter productParameter, String value, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer isDeleted) {
        this.service = service;
        this.serviceAddon = serviceAddon;
        this.productParameter = productParameter;
        this.value = value;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getServiceProductParameterValueId() {
        return serviceProductParameterValueId;
    }

    public void setServiceProductParameterValueId(Long serviceProductParameterValueId) {
        this.serviceProductParameterValueId = serviceProductParameterValueId;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServiceAddon getServiceAddon() {
        return serviceAddon;
    }

    public void setServiceAddon(ServiceAddon serviceAddon) {
        this.serviceAddon = serviceAddon;
    }

    public ProductParameter getProductParameter() {
        return productParameter;
    }

    public void setProductParameter(ProductParameter productParameter) {
        this.productParameter = productParameter;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
