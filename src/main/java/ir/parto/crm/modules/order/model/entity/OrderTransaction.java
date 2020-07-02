package ir.parto.crm.modules.order.model.entity;

import ir.parto.crm.modules.financial.model.entity.Transaction;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_order_transaction")
public class OrderTransaction implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "order_seq")
    private Long orderTransactionId;

    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "order_transaction_order_fk"))
    private Order order;

    @ManyToOne
    @JoinColumn(name = "transaction_id", foreignKey = @ForeignKey(name = "order_transaction_financial_transaction_fk"))
    private Transaction transaction;


    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "datetime")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private LocalDateTime isDeleted;

    public OrderTransaction() {
    }

    public OrderTransaction(Order order, Transaction transaction, String createdBy, LocalDateTime createdDate) {
        this.order = order;
        this.transaction = transaction;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Long getOrderTransactionId() {
        return orderTransactionId;
    }

    public void setOrderTransactionId(Long orderTransactionId) {
        this.orderTransactionId = orderTransactionId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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
