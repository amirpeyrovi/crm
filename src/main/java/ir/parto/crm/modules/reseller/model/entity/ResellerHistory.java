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
    @JoinColumn(name = "reseller_id", foreignKey = @ForeignKey(name = "reseller_history_reseller_fk"))
    private Reseller reseller;


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

    public ResellerHistory() {
    }

    public ResellerHistory(String description, Long amountIn, Long amountOut, Integer percentage, OrderItem orderItem, Reseller reseller, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Integer isDeleted) {
        this.description = description;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
        this.percentage = percentage;
        this.orderItem = orderItem;
        this.reseller = reseller;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.isDeleted = isDeleted;
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

    public Reseller getReseller() {
        return reseller;
    }

    public void setReseller(Reseller reseller) {
        this.reseller = reseller;
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
}
