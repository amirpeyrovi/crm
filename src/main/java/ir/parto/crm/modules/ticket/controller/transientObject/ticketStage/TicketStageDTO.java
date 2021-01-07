package ir.parto.crm.modules.ticket.controller.transientObject.ticketStage;

import ir.parto.crm.modules.admin.controller.transientObject.admin.AdminRelationalDTO;

public class TicketStageDTO {
    private Long ticketStageId;
    private String title;
    private String description;
    private AdminRelationalDTO admin;

    public TicketStageDTO() {
    }

    public TicketStageDTO(Long ticketStageId, String title, String description, AdminRelationalDTO admin) {
        this.ticketStageId = ticketStageId;
        this.title = title;
        this.description = description;
        this.admin = admin;
    }

    public Long getTicketStageId() {
        return ticketStageId;
    }

    public void setTicketStageId(Long ticketStageId) {
        this.ticketStageId = ticketStageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AdminRelationalDTO getAdmin() {
        return admin;
    }

    public void setAdmin(AdminRelationalDTO admin) {
        this.admin = admin;
    }
}
