package ir.parto.crm.modules.ticket.model.entity;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketNote.TicketNoteDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketNote.TicketNoteInfoDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_ticket_note")
public class TicketNote implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "ticket_seq", sequenceName = "ticket_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ticket_seq")
    private Long ticketNoteId;

    @Column(name = "message", columnDefinition = "nvarchar2(1000)")
    private String message;

    @ManyToOne
    @JoinColumn(name = "ticket_stage_id", foreignKey = @ForeignKey(name = "thicket_note_ticket_fk"))
    private Ticket ticket;


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

    public TicketNote() {
    }

    public TicketNote(String message, Ticket ticket, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer isDeleted) {
        this.message = message;
        this.ticket = ticket;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getTicketNoteId() {
        return ticketNoteId;
    }

    public void setTicketNoteId(Long ticketNoteId) {
        this.ticketNoteId = ticketNoteId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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

    public TicketNoteDTO convert2Object() {
        TicketNoteDTO dto = new TicketNoteDTO();
        if (this.ticketNoteId != null) dto.setTicketNoteId(this.ticketNoteId);
        if (this.message != null) dto.setMessage(this.message);
        if (this.ticket != null) dto.setTicket(this.ticket.convert2RelationalObject());
        return dto;
    }

    public TicketNoteInfoDTO convert2InfoObject() {
        TicketNoteInfoDTO dto = new TicketNoteInfoDTO();
        if (this.ticketNoteId != null) dto.setTicketNoteId(this.ticketNoteId);
        if (this.message != null) dto.setMessage(this.message);
        if (this.ticket != null) dto.setTicket(this.ticket.convert2RelationalObject());
        if (this.createdBy != null) dto.setCreatedBy(this.createdBy);
        if (this.updatedBy != null) dto.setUpdatedBy(this.updatedBy);
        if (this.deletedBy != null) dto.setDeletedBy(this.deletedBy);
        if (this.createdDate != null) dto.setCreatedDate(this.createdDate);
        if (this.updatedAt != null) dto.setUpdatedAt(this.updatedAt);
        if (this.deletedAt != null) dto.setDeletedAt(this.deletedAt);
        if (this.isDeleted != null) dto.setIsDeleted(this.isDeleted);
        return dto;
    }
}
