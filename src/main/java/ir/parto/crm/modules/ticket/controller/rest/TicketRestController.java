package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.modules.admin.model.service.AdminService;
import ir.parto.crm.modules.client.model.service.ClientService;
import ir.parto.crm.modules.service.model.service.ServiceService;
import ir.parto.crm.modules.ticket.controller.validate.TicketValidate;
import ir.parto.crm.modules.ticket.model.entity.Ticket;
import ir.parto.crm.modules.ticket.model.service.TicketService;
import ir.parto.crm.modules.ticket.model.service.TicketStageService;
import ir.parto.crm.modules.ticket.model.service.TicketStateService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.TicketAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@TicketAnnotation
@RequestMapping("/v1/ticket/ticket")
public class TicketRestController implements RestControllerInterface {
    private TicketService ticketService;
    private TicketStageService ticketStageService;
    private TicketStateService ticketStateService;
    private ClientService clientService;
    private AdminService adminService;
    private ServiceService serviceService;
    private TicketValidate ticketValidate;

    @Autowired
    public TicketRestController(TicketService ticketService, TicketStageService ticketStageService, TicketStateService ticketStateService, ClientService clientService, AdminService adminService, ServiceService serviceService, TicketValidate ticketValidate) {
        this.ticketService = ticketService;
        this.ticketStageService = ticketStageService;
        this.ticketStateService = ticketStateService;
        this.clientService = clientService;
        this.adminService = adminService;
        this.serviceService = serviceService;
        this.ticketValidate = ticketValidate;
    }


    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "Ticket")) {
            return new ApiResponse("Error", 101, Arrays.asList("Ticket - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.ticketValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<Ticket> ticketPage = this.ticketService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "Ticket", sortProperty, sortOrder));
            return new ApiResponse("Success", ticketPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody Ticket ticket) {
        if (CheckPermission.getInstance().check("admin_add", "Ticket")) {
            return new ApiResponse("Error", 101, Arrays.asList("Ticket - admin_add - access denied!"))
                    .getFaultResponse();
        }

        ticket.setTicketId(null);

        ValidateObject validateObject = this.ticketValidate.validateAddNewItem(ticket);
        if (validateObject.getResult().equals("success")) {
            ticket.setAdmin(this.adminService.findOne(ticket.getAdmin()));
            ticket.setService(this.serviceService.findOne(ticket.getService()));
            ticket.setClient(this.clientService.findOne(ticket.getClient()));
            ticket.setTicketStage(this.ticketStageService.findOne(ticket.getTicketStage()));
            ticket.setTicketState(this.ticketStateService.findOne(ticket.getTicketState()));
            return new ApiResponse("Success", Arrays.asList(this.ticketService.addNewItem(ticket)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody Ticket ticket) {
        if (CheckPermission.getInstance().check("admin_update", "Ticket")) {
            return new ApiResponse("Error", 101, Arrays.asList("Ticket - admin_update - access denied!"))
                    .getFaultResponse();
        }

        ticket.setTicketId(id);

        ValidateObject validateObject = this.ticketValidate.validateUpdateItem(ticket);
        if (validateObject.getResult().equals("success")) {
            try {
                ticket.setAdmin(this.adminService.findOne(ticket.getAdmin()));
                ticket.setService(this.serviceService.findOne(ticket.getService()));
                ticket.setClient(this.clientService.findOne(ticket.getClient()));
                ticket.setTicketStage(this.ticketStageService.findOne(ticket.getTicketStage()));
                ticket.setTicketState(this.ticketStateService.findOne(ticket.getTicketState()));
                return new ApiResponse("Success", Arrays.asList(this.ticketService.updateItem(ticket)))
                        .getSuccessResponse();
            } catch (InvocationTargetException e) {
                return new ApiResponse("Error", 103, Arrays.asList("An error occurred Try again later"))
                        .getFaultResponse();
            } catch (IllegalAccessException e) {
                return new ApiResponse("Error", 104, Arrays.asList("An error occurred Try again later"))
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_delete", "Ticket")) {
            return new ApiResponse("Error", 101, Arrays.asList("Ticket - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        Ticket ticket = new Ticket();
        ticket.setTicketId(id);
        ValidateObject validateObject = this.ticketValidate.deleteItem(ticket);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketService.deleteItem(ticket)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "Ticket")) {
            return new ApiResponse("Error", 101, Arrays.asList("Ticket - admin_show - access denied!"))
                    .getFaultResponse();
        }

        Ticket ticket = new Ticket();
        ticket.setTicketId(id);
        ValidateObject validateObject = this.ticketValidate.findOne(ticket);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketService.findOne(ticket)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}