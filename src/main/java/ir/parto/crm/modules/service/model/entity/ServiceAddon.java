package ir.parto.crm.modules.service.model.entity;

import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductCycle;
import ir.parto.crm.modules.server.model.entity.Server;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_service_addon")
public class ServiceAddon implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "service_seq", sequenceName = "service_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "service_seq")
    private Long serviceId;

    @ManyToOne
    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "service_addon_service_fk"))
    private Service service;

    @ManyToOne
    @JoinColumn(name = "product_addon", foreignKey = @ForeignKey(name = "service_addon_product_addon_fk"))
    private ProductAddon productAddon;

    @ManyToOne
    @JoinColumn(name = "product_cycle", foreignKey = @ForeignKey(name = "service_addon_product_cycle_fk"))
    private ProductCycle productCycle;

    @ManyToOne
    @JoinColumn(name = "server_id", foreignKey = @ForeignKey(name = "service_addon_server_fk"))
    private Server server;

    @Column(name = "price", columnDefinition = "number(16,0)")
    private Long price;

    @Column(name = "order_date", columnDefinition = "TIMESTAMP(6)")
    private Long orderDate;

    @Column(name = "start_cycle_date", columnDefinition = "TIMESTAMP(6)")
    private LocalDate startDate;

    @Column(name = "end_cycle_date", columnDefinition = "TIMESTAMP(6)")
    private LocalDate endDate;

    @Column(name = "contract_date", columnDefinition = "TIMESTAMP(6)")
    private LocalDate contractDate;

    @Column(name = "activation_date", columnDefinition = "TIMESTAMP(6)")
    private LocalDate activationDate;

    @Column(name = "termination_date", columnDefinition = "TIMESTAMP(6)")
    private LocalDate terminationDate;

    @Column(name = "suspension_date", columnDefinition = "TIMESTAMP(6)")
    private LocalDate suspensionDate;

    @Column(name = "status", columnDefinition = "nvarchar2(100)")
    private String status;


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

    public ServiceAddon() {
    }

    public ServiceAddon(Service service, ProductAddon productAddon, ProductCycle productCycle, Server server, Long price, Long orderDate, LocalDate startDate, LocalDate endDate, LocalDate contractDate, LocalDate activationDate, LocalDate terminationDate, LocalDate suspensionDate, String status, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, LocalDateTime isDeleted) {
        this.service = service;
        this.productAddon = productAddon;
        this.productCycle = productCycle;
        this.server = server;
        this.price = price;
        this.orderDate = orderDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contractDate = contractDate;
        this.activationDate = activationDate;
        this.terminationDate = terminationDate;
        this.suspensionDate = suspensionDate;
        this.status = status;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ProductAddon getProductAddon() {
        return productAddon;
    }

    public void setProductAddon(ProductAddon productAddon) {
        this.productAddon = productAddon;
    }

    public ProductCycle getProductCycle() {
        return productCycle;
    }

    public void setProductCycle(ProductCycle productCycle) {
        this.productCycle = productCycle;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Long orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public LocalDate getSuspensionDate() {
        return suspensionDate;
    }

    public void setSuspensionDate(LocalDate suspensionDate) {
        this.suspensionDate = suspensionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LocalDateTime getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(LocalDateTime isDeleted) {
        this.isDeleted = isDeleted;
    }
}
