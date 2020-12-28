package ir.parto.crm.modules.ticket.controller.transientObject.ticketStage;

public class TicketStageRelationalDTO {
    private Long ticketStageId;
    private String title;

    public TicketStageRelationalDTO() {
    }

    public TicketStageRelationalDTO(Long ticketStageId, String title) {
        this.ticketStageId = ticketStageId;
        this.title = title;
    }

    public Long getTicketStageId() {
        return ticketStageId;
    }

    public TicketStageRelationalDTO setTicketStageId(Long ticketStageId) {
        this.ticketStageId = ticketStageId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TicketStageRelationalDTO setTitle(String title) {
        this.title = title;
        return this;
    }
}
