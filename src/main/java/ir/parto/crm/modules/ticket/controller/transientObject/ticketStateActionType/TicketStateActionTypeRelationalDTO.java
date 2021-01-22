package ir.parto.crm.modules.ticket.controller.transientObject.ticketStateActionType;

public class TicketStateActionTypeRelationalDTO {
    private Long ticketStateActionTypeId;
    private String title;

    public TicketStateActionTypeRelationalDTO() {
    }

    public TicketStateActionTypeRelationalDTO(Long ticketStateActionTypeId, String title) {
        this.ticketStateActionTypeId = ticketStateActionTypeId;
        this.title = title;
    }

    public Long getTicketStateActionTypeId() {
        return ticketStateActionTypeId;
    }

    public void setTicketStateActionTypeId(Long ticketStateActionTypeId) {
        this.ticketStateActionTypeId = ticketStateActionTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
