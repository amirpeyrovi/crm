package ir.parto.crm.modules.ticket.controller.transientObject.ticketStateAction;

import ir.parto.crm.modules.ticket.controller.transientObject.ticketStateActionType.TicketStateActionTypeRelationalDTO;

public class TicketStateActionRelationalDTO {
    private Long ticketStateActionId;
    private String title;
    private TicketStateActionTypeRelationalDTO ticketStateActionType;

    public TicketStateActionRelationalDTO() {
    }

    public TicketStateActionRelationalDTO(Long ticketStateActionId, String title, TicketStateActionTypeRelationalDTO ticketStateActionType) {
        this.ticketStateActionId = ticketStateActionId;
        this.title = title;
        this.ticketStateActionType = ticketStateActionType;
    }

    public Long getTicketStateActionId() {
        return ticketStateActionId;
    }

    public void setTicketStateActionId(Long ticketStateActionId) {
        this.ticketStateActionId = ticketStateActionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TicketStateActionTypeRelationalDTO getTicketStateActionType() {
        return ticketStateActionType;
    }

    public void setTicketStateActionType(TicketStateActionTypeRelationalDTO ticketStateActionType) {
        this.ticketStateActionType = ticketStateActionType;
    }
}
