package ir.parto.crm.modules.client.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_client_credit")
public class ClientCredit implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ClientCreditId;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "client_contact_fk"))
    private Client client;

    @Column(name = "description", columnDefinition = "nvarchar2(100)")
    private String description;

    @Column(name = "caption", columnDefinition = "nvarchar2(1000)")
    private String caption;

    @Column(name = "type", columnDefinition = "nvarchar2(100)")
    private String type;

    @Column(name = "amount_in", columnDefinition = "number")
    private Long amountIn;

    @Column(name = "amount_out", columnDefinition = "number")
    private Long amountOut;

    @Column(name = "total", columnDefinition = "number")
    private Long total;

    @Column(name = "invoice_transaction_id", columnDefinition = "number")
    private Long invoiceTransactionId;


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

    public ClientCredit() {
    }

    public ClientCredit(Client client, String description, String caption, String type, Long amountIn, Long amountOut, Long total, Long invoiceTransactionId, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, LocalDateTime isDeleted) {
        this.client = client;
        this.description = description;
        this.caption = caption;
        this.type = type;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
        this.total = total;
        this.invoiceTransactionId = invoiceTransactionId;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getClientCreditId() {
        return ClientCreditId;
    }

    public void setClientCreditId(Long clientCreditId) {
        ClientCreditId = clientCreditId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAmountIn() {
        return amountIn;
    }

    public void setAmountIn(Long amountIn) {
        this.amountIn = amountIn;
    }

    public Long getAmountOut() {
        return amountOut;
    }

    public void setAmountOut(Long amountOut) {
        this.amountOut = amountOut;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getInvoiceTransactionId() {
        return invoiceTransactionId;
    }

    public void setInvoiceTransactionId(Long invoiceTransactionId) {
        this.invoiceTransactionId = invoiceTransactionId;
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
