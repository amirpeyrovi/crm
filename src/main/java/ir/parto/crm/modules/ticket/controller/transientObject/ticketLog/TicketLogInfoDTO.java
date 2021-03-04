package ir.parto.crm.modules.ticket.controller.transientObject.ticketLog;

import ir.parto.crm.modules.ticket.controller.transientObject.ticket.TicketRelationalDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketStage.TicketStageRelationalDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketState.TicketStateRelationalDTO;

import java.time.LocalDateTime;

public class TicketLogInfoDTO {
    private Long ticketLogId;
    private TicketRelationalDTO ticket;
    private TicketStageRelationalDTO ticketStage;
    private TicketStateRelationalDTO ticketState;
    private String createdBy;
    private LocalDateTime createdDate;

    public TicketLogInfoDTO() {
    }

    public TicketLogInfoDTO(Long ticketLogId, TicketRelationalDTO ticket, TicketStageRelationalDTO ticketStage, TicketStateRelationalDTO ticketState, String createdBy, LocalDateTime createdDate) {
        this.ticketLogId = ticketLogId;
        this.ticket = ticket;
        this.ticketStage = ticketStage;
        this.ticketState = ticketState;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Long getTicketLogId() {
        return ticketLogId;
    }

    public void setTicketLogId(Long ticketLogId) {
        this.ticketLogId = ticketLogId;
    }

    public TicketRelationalDTO getTicket() {
        return ticket;
    }

    public void setTicket(TicketRelationalDTO ticket) {
        this.ticket = ticket;
    }

    public TicketStageRelationalDTO getTicketStage() {
        return ticketStage;
    }

    public void setTicketStage(TicketStageRelationalDTO ticketStage) {
        this.ticketStage = ticketStage;
    }

    public TicketStateRelationalDTO getTicketState() {
        return ticketState;
    }

    public void setTicketState(TicketStateRelationalDTO ticketState) {
        this.ticketState = ticketState;
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
