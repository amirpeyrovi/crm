package ir.parto.crm.modules.ticket.controller.transientObject.ticket;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.service.model.entity.Service;
import ir.parto.crm.modules.ticket.model.entity.Ticket;
import ir.parto.crm.modules.ticket.model.entity.TicketStage;
import ir.parto.crm.modules.ticket.model.entity.TicketState;

public class TicketAddDTO {
    private String title;
    private String message;
    private Long clientId;
    private Long serviceId;
    private Long ticketStageId;
    private Long ticketStateId;
    private Long adminId;

    public TicketAddDTO() {
    }

    public TicketAddDTO(String title, String message, Long clientId, Long serviceId, Long ticketStageId, Long ticketStateId, Long adminId) {
        this.title = title;
        this.message = message;
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.ticketStageId = ticketStageId;
        this.ticketStateId = ticketStateId;
        this.adminId = adminId;
    }

    public String getTitle() {
        return title;
    }

    public TicketAddDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public TicketAddDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public Long getClientId() {
        return clientId;
    }

    public TicketAddDTO setClientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public TicketAddDTO setServiceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public Long getTicketStageId() {
        return ticketStageId;
    }

    public TicketAddDTO setTicketStageId(Long ticketStageId) {
        this.ticketStageId = ticketStageId;
        return this;
    }

    public Long getTicketStateId() {
        return ticketStateId;
    }

    public TicketAddDTO setTicketStateId(Long ticketStateId) {
        this.ticketStateId = ticketStateId;
        return this;
    }

    public Long getAdminId() {
        return adminId;
    }

    public TicketAddDTO setAdminId(Long adminId) {
        this.adminId = adminId;
        return this;
    }

    public Ticket convert2Object(){
        Ticket ticket = new Ticket();
        if(this.title != null) ticket.setTitle(this.title);
        if(this.message != null) ticket.setMessage(this.message);
        if(this.clientId != null) ticket.setClient(new Client(this.clientId));
        if(this.serviceId != null) ticket.setService(new Service(this.serviceId));
        if(this.ticketStageId != null) ticket.setTicketStage(new TicketStage(this.ticketStageId));
        if(this.ticketStateId != null) ticket.setTicketState(new TicketState(this.ticketStateId));
        if(this.adminId != null) ticket.setAdmin(new Admin(this.adminId));
        return ticket;
    }
}
