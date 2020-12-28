package ir.parto.crm.modules.ticket.controller.transientObject.ticketState;

public class TicketStateRelationalDTO {
    private Long ticketStateId;
    private String title;
    private String color;

    public TicketStateRelationalDTO() {
    }

    public TicketStateRelationalDTO(Long ticketStateId, String title, String color) {
        this.ticketStateId = ticketStateId;
        this.title = title;
        this.color = color;
    }

    public Long getTicketStateId() {
        return ticketStateId;
    }

    public TicketStateRelationalDTO setTicketStateId(Long ticketStateId) {
        this.ticketStateId = ticketStateId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TicketStateRelationalDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getColor() {
        return color;
    }

    public TicketStateRelationalDTO setColor(String color) {
        this.color = color;
        return this;
    }
}
