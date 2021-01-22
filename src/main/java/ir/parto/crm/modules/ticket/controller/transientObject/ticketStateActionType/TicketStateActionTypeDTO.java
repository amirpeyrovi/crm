package ir.parto.crm.modules.ticket.controller.transientObject.ticketStateActionType;

public class TicketStateActionTypeDTO {
    private Long ticketStateActionTypeId;
    private String title;

    public TicketStateActionTypeDTO() {
    }

    public TicketStateActionTypeDTO(Long ticketStateActionTypeId, String title) {
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
