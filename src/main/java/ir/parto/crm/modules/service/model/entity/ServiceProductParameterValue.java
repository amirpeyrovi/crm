package ir.parto.crm.modules.service.model.entity;

import ir.parto.crm.modules.product.model.entity.ProductParameter;
import org.hibernate.annotations.CreationTimestamp;

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
    private Long serviceProductHistory;

    @ManyToOne
    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "service_product_parameter_value_service_fk"))
    private Service service;

    @ManyToOne
    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "service_product_parameter_value_product_parameter_fk"))
    private ProductParameter productParameter;

    @Column(name = "value", columnDefinition = "nvarchar2(100)")
    private String value;


    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdDate;

    public ServiceProductParameterValue() {
    }

    public ServiceProductParameterValue(Service service, ProductParameter productParameter, String value, String createdBy, LocalDateTime createdDate) {
        this.service = service;
        this.productParameter = productParameter;
        this.value = value;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Long getServiceProductHistory() {
        return serviceProductHistory;
    }

    public void setServiceProductHistory(Long serviceProductHistory) {
        this.serviceProductHistory = serviceProductHistory;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
