package ir.parto.crm.modules.ticket.controller.transientObject.ticketNote;

import ir.parto.crm.modules.ticket.model.entity.Ticket;
import ir.parto.crm.modules.ticket.model.entity.TicketNote;

import java.time.LocalDateTime;

public class TicketNoteAddDTO {
    private String message;
    private Long ticketId;

    public TicketNoteAddDTO() {
    }

    public TicketNoteAddDTO(String message, Long ticketId) {
        this.message = message;
        this.ticketId = ticketId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public TicketNote convert2Object() {
        TicketNote obj = new TicketNote();
        if (this.message != null) obj.setMessage(this.message);
        if (this.ticketId != null) obj.setTicket(new Ticket(this.ticketId));
        return obj;
    }
}
