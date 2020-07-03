package ir.parto.crm.modules.service.model.entity;

import ir.parto.crm.modules.product.model.entity.Product;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_service_product_history")
public class ServiceProductHistory implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "service_seq", sequenceName = "service_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "service_seq")
    private Long serviceProductHistoryId;

    @ManyToOne
    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "service_product_history_service_fk"))
    private Service service;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "service_product_history_product_fk"))
    private Product product;


    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "TIMESTAMP(6)")
    @CreationTimestamp
    private LocalDateTime createdDate;

    public ServiceProductHistory() {
    }

    public ServiceProductHistory(Service service, Product product, String createdBy, LocalDateTime createdDate) {
        this.service = service;
        this.product = product;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Long getServiceProductHistoryId() {
        return serviceProductHistoryId;
    }

    public void setServiceProductHistoryId(Long serviceProductHistoryId) {
        this.serviceProductHistoryId = serviceProductHistoryId;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
