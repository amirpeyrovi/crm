package ir.parto.crm.modules.ticket.controller.transientObject.ticketStage;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.ticket.model.entity.TicketStage;

public class TicketStageAddDTO {
    private String title;
    private String description;
    private Long adminId;

    public TicketStageAddDTO() {
    }

    public TicketStageAddDTO(String title, String description, Long adminId) {
        this.title = title;
        this.description = description;
        this.adminId = adminId;
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

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public TicketStage convert2Object() {
        TicketStage ticketStage = new TicketStage();
        if (this.title != null) ticketStage.setTitle(this.title);
        if (this.description != null) ticketStage.setDescription(this.description);
        if (this.adminId != null) ticketStage.setAdmin(new Admin(this.adminId));
        return ticketStage;
    }
}
