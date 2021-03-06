package ir.parto.crm.modules.ticket.model.entity;

import ir.parto.crm.modules.ticket.controller.transientObject.ticketState.TicketStateDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketState.TicketStateRelationalDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_ticket_state")
public class TicketState implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "ticket_seq", sequenceName = "ticket_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ticket_seq")
    private Long ticketStateId;

    @Column(name = "title", columnDefinition = "nvarchar2(100)", unique = true)
    private String title;

    @Column(name = "description", columnDefinition = "nvarchar2(500)")
    private String description;

    @Column(name = "color", columnDefinition = "nvarchar2(12)")
    private String color;

    @ManyToOne
    @JoinColumn(name = "ticket_state_action_id", foreignKey = @ForeignKey(name = "thicket_state_ticket_state_action_fk"))
    private TicketStateAction ticketStateAction;

    @Column(name = "is_active", columnDefinition = "number(1)")
    private Integer isActive;

    @Column(name = "is_pending", columnDefinition = "number(1)")
    private Integer isPending;

    @Column(name = "is_close", columnDefinition = "number(1)")
    private Integer isClose;

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

    public TicketState() {
    }

    public TicketState(String title, String description, String color, TicketStateAction ticketStateAction, Integer isActive, Integer isPending, Integer isClose, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer isDeleted) {
        this.title = title;
        this.description = description;
        this.color = color;
        this.ticketStateAction = ticketStateAction;
        this.isActive = isActive;
        this.isPending = isPending;
        this.isClose = isClose;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public TicketState(Long ticketStateId) {
        this.ticketStateId = ticketStateId;
    }

    public Long getTicketStateId() {
        return ticketStateId;
    }

    public void setTicketStateId(Long ticketStateId) {
        this.ticketStateId = ticketStateId;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TicketStateAction getTicketStateAction() {
        return ticketStateAction;
    }

    public void setTicketStateAction(TicketStateAction ticketStateAction) {
        this.ticketStateAction = ticketStateAction;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsPending() {
        return isPending;
    }

    public void setIsPending(Integer isPending) {
        this.isPending = isPending;
    }

    public Integer getIsClose() {
        return isClose;
    }

    public void setIsClose(Integer isClose) {
        this.isClose = isClose;
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

    public TicketStateRelationalDTO convert2RelationalObject() {
        TicketStateRelationalDTO dto = new TicketStateRelationalDTO();
        if(this.ticketStateId != null) dto.setTicketStateId(this.ticketStateId);
        if(this.title != null) dto.setTitle(this.title);
        if(this.color != null) dto.setColor(this.color);
        return dto;
    }

    public TicketStateDTO convert2Object() {
        TicketStateDTO dto = new TicketStateDTO();
        if(this.ticketStateId != null) dto.setTicketStateId(this.ticketStateId);
        if(this.title != null) dto.setTitle(this.title);
        if(this.description != null) dto.setDescription(this.description);
        if(this.color != null) dto.setColor(this.color);
        if(this.ticketStateAction != null) dto.setTicketStateAction(this.ticketStateAction.convert2RelationalObject());
        if(this.isActive != null) dto.setIsActive(this.isActive);
        if(this.isPending != null) dto.setIsPending(this.isPending);
        if(this.isClose != null) dto.setIsClose(this.isClose);

        return dto;
    }
}
