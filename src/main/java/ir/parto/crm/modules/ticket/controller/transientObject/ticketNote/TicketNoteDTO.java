package ir.parto.crm.modules.ticket.controller.transientObject.ticketNote;

import ir.parto.crm.modules.ticket.controller.transientObject.ticket.TicketRelationalDTO;

public class TicketNoteDTO {
    private Long ticketNoteId;
    private String message;
    private TicketRelationalDTO ticket;

    public TicketNoteDTO() {
    }

    public TicketNoteDTO(Long ticketNoteId, String message, TicketRelationalDTO ticket) {
        this.ticketNoteId = ticketNoteId;
        this.message = message;
        this.ticket = ticket;
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
}
