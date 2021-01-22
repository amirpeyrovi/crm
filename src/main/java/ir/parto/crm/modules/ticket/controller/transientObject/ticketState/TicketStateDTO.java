package ir.parto.crm.modules.ticket.controller.transientObject.ticketState;

import ir.parto.crm.modules.ticket.controller.transientObject.ticketStateAction.TicketStateActionRelationalDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketStateActionType.TicketStateActionTypeRelationalDTO;

public class TicketStateDTO {
    private Long ticketStateId;
    private String title;
    private String description;
    private String color;
    private TicketStateActionRelationalDTO ticketStateAction;
    private Integer isActive;
    private Integer isPending;
    private Integer isClose;

    public TicketStateDTO() {
    }

    public TicketStateDTO(Long ticketStateId, String title, String description, String color, TicketStateActionRelationalDTO ticketStateAction, Integer isActive, Integer isPending, Integer isClose) {
        this.ticketStateId = ticketStateId;
        this.title = title;
        this.description = description;
        this.color = color;
        this.ticketStateAction = ticketStateAction;
        this.isActive = isActive;
        this.isPending = isPending;
        this.isClose = isClose;
    }

    public Long getTicketStateId() {
        return ticketStateId;
    }

    public void setTicketStateId(Long ticketStateId) {
        this.ticketStateId = ticketStateId;
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

    public TicketStateActionRelationalDTO getTicketStateAction() {
        return ticketStateAction;
    }

    public void setTicketStateAction(TicketStateActionRelationalDTO ticketStateAction) {
        this.ticketStateAction = ticketStateAction;
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
}
