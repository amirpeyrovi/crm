package ir.parto.crm.modules.ticket.controller.transientObject.ticketLog;

import ir.parto.crm.modules.ticket.model.entity.Ticket;
import ir.parto.crm.modules.ticket.model.entity.TicketLog;
import ir.parto.crm.modules.ticket.model.entity.TicketStage;
import ir.parto.crm.modules.ticket.model.entity.TicketState;

import java.time.LocalDateTime;

public class TicketLogAddDTO {
    private Long ticketId;
    private Long ticketStageId;
    private Long ticketStateId;

    public TicketLogAddDTO() {
    }

    public TicketLogAddDTO(Long ticketId, Long ticketStageId, Long ticketStateId) {
        this.ticketId = ticketId;
        this.ticketStageId = ticketStageId;
        this.ticketStateId = ticketStateId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getTicketStageId() {
        return ticketStageId;
    }

    public void setTicketStageId(Long ticketStageId) {
        this.ticketStageId = ticketStageId;
    }

    public Long getTicketStateId() {
        return ticketStateId;
    }

    public void setTicketStateId(Long ticketStateId) {
        this.ticketStateId = ticketStateId;
    }

    public TicketLog convert2Object() {
        TicketLog obj = new TicketLog();
        if (this.ticketId != null) obj.setTicket(new Ticket(this.ticketId));
        if (this.ticketStageId != null) obj.setTicketStage(new TicketStage(this.ticketStageId));
        if (this.ticketStateId != null) obj.setTicketState(new TicketState(this.ticketStateId));
        return obj;
    }
}
