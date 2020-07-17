package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.modules.ticket.controller.validate.TicketLogValidate;
import ir.parto.crm.modules.ticket.model.entity.TicketLog;
import ir.parto.crm.modules.ticket.model.service.TicketLogService;
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
@RequestMapping("/v1/ticket/ticketLog")
public class TicketLogRestControlller implements RestControllerInterface {
    private TicketLogService ticketLogService;
    private TicketService ticketService;
    private TicketStateService ticketStateService;
    private TicketStageService ticketStageService;
    private TicketLogValidate ticketLogValidate;

    @Autowired
    public TicketLogRestControlller(TicketLogService ticketLogService, TicketService ticketService, TicketStateService ticketStateService, TicketStageService ticketStageService, TicketLogValidate ticketLogValidate) {
        this.ticketLogService = ticketLogService;
        this.ticketService = ticketService;
        this.ticketStateService = ticketStateService;
        this.ticketStageService = ticketStageService;
        this.ticketLogValidate = ticketLogValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "TicketLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketLog - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.ticketLogValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<TicketLog> ticketLogPage = this.ticketLogService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "TicketLog", sortProperty, sortOrder));
            return new ApiResponse("Success", ticketLogPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody TicketLog ticketLog) {
        if (CheckPermission.getInstance().check("admin_add", "TicketLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketLog - admin_add - access denied!"))
                    .getFaultResponse();
        }

        ticketLog.setTicketLogId(null);

        ValidateObject validateObject = this.ticketLogValidate.validateAddNewItem(ticketLog);
        if (validateObject.getResult().equals("success")) {
            ticketLog.setTicket(this.ticketService.findOne(ticketLog.getTicket()));
            ticketLog.setTicketStage(this.ticketStageService.findOne(ticketLog.getTicketStage()));
            ticketLog.setTicketState(this.ticketStateService.findOne(ticketLog.getTicketState()));
            return new ApiResponse("Success", Arrays.asList(this.ticketLogService.addNewItem(ticketLog)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody TicketLog ticketLog) {
        if (CheckPermission.getInstance().check("admin_update", "TicketLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketLog - admin_update - access denied!"))
                    .getFaultResponse();
        }

        ticketLog.setTicketLogId(id);

        ValidateObject validateObject = this.ticketLogValidate.validateUpdateItem(ticketLog);
        if (validateObject.getResult().equals("success")) {
            try {
                ticketLog.setTicket(this.ticketService.findOne(ticketLog.getTicket()));
                ticketLog.setTicketStage(this.ticketStageService.findOne(ticketLog.getTicketStage()));
                ticketLog.setTicketState(this.ticketStateService.findOne(ticketLog.getTicketState()));
                return new ApiResponse("Success", Arrays.asList(this.ticketLogService.updateItem(ticketLog)))
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
        if (CheckPermission.getInstance().check("admin_delete", "TicketLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketLog - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        TicketLog ticketLog = new TicketLog();
        ticketLog.setTicketLogId(id);
        ValidateObject validateObject = this.ticketLogValidate.deleteItem(ticketLog);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketLogService.deleteItem(ticketLog)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "TicketLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketLog - admin_show - access denied!"))
                    .getFaultResponse();
        }

        TicketLog ticketLog = new TicketLog();
        ticketLog.setTicketLogId(id);
        ValidateObject validateObject = this.ticketLogValidate.findOne(ticketLog);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketLogService.findOne(ticketLog)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
