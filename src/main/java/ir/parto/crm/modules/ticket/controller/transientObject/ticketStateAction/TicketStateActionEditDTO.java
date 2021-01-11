package ir.parto.crm.modules.ticket.controller.transientObject.ticketStateAction;

import ir.parto.crm.modules.ticket.model.entity.TicketStateAction;
import ir.parto.crm.modules.ticket.model.entity.TicketStateActionType;

public class TicketStateActionEditDTO {
    private String title;
    private Long ticketStateActionTypeId;

    public TicketStateActionEditDTO() {
    }

    public TicketStateActionEditDTO(String title, Long ticketStateActionTypeId) {
        this.title = title;
        this.ticketStateActionTypeId = ticketStateActionTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTicketStateActionTypeId() {
        return ticketStateActionTypeId;
    }

    public void setTicketStateActionTypeId(Long ticketStateActionTypeId) {
        this.ticketStateActionTypeId = ticketStateActionTypeId;
    }

    public TicketStateAction convert2Object() {
        TicketStateAction ticketStateAction = new TicketStateAction();
        if (this.title != null) ticketStateAction.setTitle(this.title);
        if (this.ticketStateActionTypeId != null)
            ticketStateAction.setTicketStateActionType(new TicketStateActionType(this.ticketStateActionTypeId));
        return ticketStateAction;
    }
}
