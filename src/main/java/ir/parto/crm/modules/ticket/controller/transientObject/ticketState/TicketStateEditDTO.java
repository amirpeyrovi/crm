package ir.parto.crm.modules.ticket.controller.transientObject.ticketState;

import ir.parto.crm.modules.ticket.model.entity.TicketState;
import ir.parto.crm.modules.ticket.model.entity.TicketStateAction;

public class TicketStateEditDTO {
    private String title;
    private String description;
    private String color;
    private Long ticketStateActionId;
    private Integer isActive;
    private Integer isPending;
    private Integer isClose;

    public TicketStateEditDTO() {
    }

    public TicketStateEditDTO(String title, String description, String color, Long ticketStateActionId, Integer isActive, Integer isPending, Integer isClose) {
        this.title = title;
        this.description = description;
        this.color = color;
        this.ticketStateActionId = ticketStateActionId;
        this.isActive = isActive;
        this.isPending = isPending;
        this.isClose = isClose;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getTicketStateActionId() {
        return ticketStateActionId;
    }

    public void setTicketStateActionId(Long ticketStateActionId) {
        this.ticketStateActionId = ticketStateActionId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsPending() {
        return isPending;
    }

    public void setIsPending(Integer isPending) {
        this.isPending = isPending;
    }

    public Integer getIsClose() {
        return isClose;
    }

    public void setIsClose(Integer isClose) {
        this.isClose = isClose;
    }

    public TicketState convert2Object() {
        TicketState ticketState = new TicketState();
        if (this.title != null) ticketState.setTitle(this.title);
        if (this.description != null) ticketState.setDescription(this.description);
        if (this.color != null) ticketState.setColor(this.color);
        if (this.ticketStateActionId != null)
            ticketState.setTicketStateAction(new TicketStateAction(this.ticketStateActionId));
        if (this.isActive != null) ticketState.setIsActive(this.isActive);
        if (this.isPending != null) ticketState.setIsPending(this.isPending);
        if (this.isClose != null) ticketState.setIsClose(this.isClose);
        return ticketState;
    }

}
