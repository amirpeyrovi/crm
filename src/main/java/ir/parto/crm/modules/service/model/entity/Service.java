package ir.parto.crm.modules.service.model.entity;

import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductCycle;
import ir.parto.crm.modules.server.model.entity.Server;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_Service")
public class Service implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "service_seq", sequenceName = "service_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "service_seq")
    private Long serviceId;

    @Column(name = "identify_code", columnDefinition = "nvarchar2(100)")
    private String identifyCode;

    @Column(name = "username", columnDefinition = "nvarchar2(100)")
    private String username;

    @Column(name = "password", columnDefinition = "nvarchar2(100)")
    private String password;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "service_product_fk"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "service_client_fk"))
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_cycle", foreignKey = @ForeignKey(name = "service_product_cycle_fk"))
    private ProductCycle productCycle;

    @ManyToOne
    @JoinColumn(name = "server_id", foreignKey = @ForeignKey(name = "service_server_fk"))
    private Server server;

    @Column(name = "price", columnDefinition = "number(16,0)")
    private Long price;

    @Column(name = "order_date", columnDefinition = "date")
    private Long orderDate;

    @Column(name = "start_cycle_date", columnDefinition = "date")
    private LocalDate startDate;

    @Column(name = "end_cycle_date", columnDefinition = "date")
    private LocalDate endDate;

    @Column(name = "contract_date", columnDefinition = "date")
    private LocalDate contractDate;

    @Column(name = "activation_date", columnDefinition = "date")
    private LocalDate activationDate;

    @Column(name = "termination_date", columnDefinition = "date")
    private LocalDate terminationDate;

    @Column(name = "suspension_date", columnDefinition = "date")
    private LocalDate suspensionDate;

    @Column(name = "status", columnDefinition = "nvarchar2(100)")
    private String status;


    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_by", columnDefinition = "datetime")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_by", columnDefinition = "datetime")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private LocalDateTime isDeleted;

    public Service() {
    }

    public Service(String identifyCode, String username, String password, Product product, Client client, ProductCycle productCycle, Server server, Long price, Long orderDate, LocalDate startDate, LocalDate endDate, LocalDate contractDate, LocalDate activationDate, LocalDate terminationDate, LocalDate suspensionDate, String status, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, LocalDateTime isDeleted) {
        this.identifyCode = identifyCode;
        this.username = username;
        this.password = password;
        this.product = product;
        this.client = client;
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

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
