package ir.parto.crm.modules.ticket.controller.transientObject.ticket;

import ir.parto.crm.modules.admin.controller.transientObject.admin.AdminRelationalDTO;
import ir.parto.crm.modules.client.controller.transientObject.client.ClientRelationalDTO;
import ir.parto.crm.modules.service.controller.transientObject.service.ServiceRelationalDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketStage.TicketStageRelationalDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketState.TicketStateRelationalDTO;

public class TicketDTO {
    private Long ticketId;
    private String title;
    private String message;
    private ClientRelationalDTO client;
    private ServiceRelationalDTO service;
    private TicketStageRelationalDTO ticketStage;
    private TicketStateRelationalDTO ticketState;
    private AdminRelationalDTO admin;

    public TicketDTO() {
    }

    public TicketDTO(Long ticketId, String title, String message, ClientRelationalDTO client, ServiceRelationalDTO service,
                     TicketStageRelationalDTO ticketStage, TicketStateRelationalDTO ticketState, AdminRelationalDTO admin) {
        this.ticketId = ticketId;
        this.title = title;
        this.message = message;
        this.client = client;
        this.service = service;
        this.ticketStage = ticketStage;
        this.ticketState = ticketState;
        this.admin = admin;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public TicketDTO setTicketId(Long ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TicketDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public TicketDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public ClientRelationalDTO getClient() {
        return client;
    }

    public TicketDTO setClient(ClientRelationalDTO client) {
        this.client = client;
        return this;
    }

    public ServiceRelationalDTO getService() {
        return service;
    }

    public TicketDTO setService(ServiceRelationalDTO service) {
        this.service = service;
        return this;
    }

    public TicketStageRelationalDTO getTicketStage() {
        return ticketStage;
    }

    public TicketDTO setTicketStage(TicketStageRelationalDTO ticketStage) {
        this.ticketStage = ticketStage;
        return this;
    }

    public TicketStateRelationalDTO getTicketState() {
        return ticketState;
    }

    public TicketDTO setTicketState(TicketStateRelationalDTO ticketState) {
        this.ticketState = ticketState;
        return this;
    }

    public AdminRelationalDTO getAdmin() {
        return admin;
    }

    public TicketDTO setAdmin(AdminRelationalDTO admin) {
        this.admin = admin;
        return this;
    }
}
