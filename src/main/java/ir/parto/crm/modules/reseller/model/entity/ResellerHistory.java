package ir.parto.crm.modules.reseller.model.entity;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.order.model.entity.OrderItem;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_reseller_history")
public class ResellerHistory implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "reseller_seq", sequenceName = "reseller_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "reseller_seq")
    private Long resellerHistoryId;

    @Column(name = "description", columnDefinition = "nvarchar2(32)")
    private String description;

    @Column(name = "amount_in", columnDefinition = "number(16,0)")
    private Long amountIn;

    @Column(name = "amount_out", columnDefinition = "number(16,0)")
    private Long amountOut;

    @Column(name = "percentage", columnDefinition = "number(3)")
    private Integer percentage;

    @ManyToOne
    @JoinColumn(name = "order_item_id", foreignKey = @ForeignKey(name = "reseller_history_order_item_fk"))
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "admin_id", foreignKey = @ForeignKey(name = "reseller_history_admin_fk"))
    private Admin admin;


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

    public ResellerHistory() {
    }

    public ResellerHistory(String description, Long amountIn, Long amountOut, Integer percentage, OrderItem orderItem, Admin admin, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, LocalDateTime isDeleted) {
        this.description = description;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
        this.percentage = percentage;
        this.orderItem = orderItem;
        this.admin = admin;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Long getResellerHistoryId() {
        return resellerHistoryId;
    }

    public void setResellerHistoryId(Long resellerHistoryId) {
        this.resellerHistoryId = resellerHistoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
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
