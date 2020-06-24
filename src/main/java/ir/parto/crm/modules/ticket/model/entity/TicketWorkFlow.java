package ir.parto.crm.modules.ticket.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_ticket_workflow")
public class TicketWorkFlow implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "ticket_seq", sequenceName = "ticket_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ticket_seq")
    private Long ticketWorkFlowId;

    @Column(name = "title", columnDefinition = "nvarchar2(100)", unique = true)
    private String title;

    @Column(name = "description", columnDefinition = "nvarchar2(1000)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "previous_ticket_stage_id", foreignKey = @ForeignKey(name = "ticket_previous_ticket_stage_fk"))
    private TicketStage previousTicketStage;

    @ManyToOne
    @JoinColumn(name = "previous_ticket_state_id", foreignKey = @ForeignKey(name = "ticket_previous_ticket_state_fk"))
    private TicketStage previousTicketState;

    @ManyToOne
    @JoinColumn(name = "next_ticket_stage_id", foreignKey = @ForeignKey(name = "ticket_next_ticket_stage_fk"))
    private TicketStage nextTicketStage;

    @ManyToOne
    @JoinColumn(name = "next_ticket_state_id", foreignKey = @ForeignKey(name = "ticket_next_ticket_state_fk"))
    private TicketStage nextTicketState;


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

    public TicketWorkFlow() {
    }

    public TicketWorkFlow(String title, String description, TicketStage previousTicketStage, TicketStage previousTicketState, TicketStage nextTicketStage, TicketStage nextTicketState, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, LocalDateTime isDeleted) {
        this.title = title;
        this.description = description;
        this.previousTicketStage = previousTicketStage;
        this.previousTicketState = previousTicketState;
        this.nextTicketStage = nextTicketStage;
        this.nextTicketState = nextTicketState;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getTicketWorkFlowId() {
        return ticketWorkFlowId;
    }

    public void setTicketWorkFlowId(Long ticketWorkFlowId) {
        this.ticketWorkFlowId = ticketWorkFlowId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStage getPreviousTicketStage() {
        return previousTicketStage;
    }

    public void setPreviousTicketStage(TicketStage previousTicketStage) {
        this.previousTicketStage = previousTicketStage;
    }

    public TicketStage getPreviousTicketState() {
        return previousTicketState;
    }

    public void setPreviousTicketState(TicketStage previousTicketState) {
        this.previousTicketState = previousTicketState;
    }

    public TicketStage getNextTicketStage() {
        return nextTicketStage;
    }

    public void setNextTicketStage(TicketStage nextTicketStage) {
        this.nextTicketStage = nextTicketStage;
    }

    public TicketStage getNextTicketState() {
        return nextTicketState;
    }

    public void setNextTicketState(TicketStage nextTicketState) {
        this.nextTicketState = nextTicketState;
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
