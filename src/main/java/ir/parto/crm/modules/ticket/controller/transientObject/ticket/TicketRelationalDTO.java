package ir.parto.crm.modules.ticket.controller.transientObject.ticket;

import ir.parto.crm.modules.ticket.controller.transientObject.ticketStage.TicketStageRelationalDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketState.TicketStateRelationalDTO;

public class TicketRelationalDTO {
    private Long ticketId;
    private String title;
    private TicketStageRelationalDTO ticketStage;
    private TicketStateRelationalDTO ticketState;

    public TicketRelationalDTO() {
    }

    public TicketRelationalDTO(Long ticketId, String title, TicketStageRelationalDTO ticketStage, TicketStateRelationalDTO ticketState) {
        this.ticketId = ticketId;
        this.title = title;
        this.ticketStage = ticketStage;
        this.ticketState = ticketState;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public TicketRelationalDTO setTicketId(Long ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TicketRelationalDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public TicketStageRelationalDTO getTicketStage() {
        return ticketStage;
    }

    public TicketRelationalDTO setTicketStage(TicketStageRelationalDTO ticketStage) {
        this.ticketStage = ticketStage;
        return this;
    }

    public TicketStateRelationalDTO getTicketState() {
        return ticketState;
    }

    public TicketRelationalDTO setTicketState(TicketStateRelationalDTO ticketState) {
        this.ticketState = ticketState;
        return this;
    }
}
