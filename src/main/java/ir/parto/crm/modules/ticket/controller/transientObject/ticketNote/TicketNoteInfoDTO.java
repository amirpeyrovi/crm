package ir.parto.crm.modules.ticket.controller.transientObject.ticketNote;

import ir.parto.crm.modules.ticket.controller.transientObject.ticket.TicketRelationalDTO;

import java.time.LocalDateTime;

public class TicketNoteInfoDTO {
    private Long ticketNoteId;
    private String message;
    private TicketRelationalDTO ticket;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Integer isDeleted;

    public TicketNoteInfoDTO() {
    }

    public TicketNoteInfoDTO(Long ticketNoteId, String message, TicketRelationalDTO ticket, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer isDeleted) {
        this.ticketNoteId = ticketNoteId;
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

    public TicketRelationalDTO getTicket() {
        return ticket;
    }

    public void setTicket(TicketRelationalDTO ticket) {
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
}
