package ir.parto.crm.modules.ticket.controller.transientObject.ticketStateActionType;

import ir.parto.crm.modules.ticket.model.entity.TicketStateActionType;

public class TicketStateActionTypeEditDTO {
    private String title;

    public TicketStateActionTypeEditDTO() {
    }

    public TicketStateActionTypeEditDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TicketStateActionType convert2Object() {
        TicketStateActionType ticketStateActionType = new TicketStateActionType();
        if (this.title != null) ticketStateActionType.setTitle(this.title);
        return ticketStateActionType;
    }
}
