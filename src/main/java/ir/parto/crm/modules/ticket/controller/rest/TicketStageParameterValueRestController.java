package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.modules.ticket.controller.validate.TicketStageParameterValueValidate;
import ir.parto.crm.modules.ticket.model.entity.TicketStageParameterValue;
import ir.parto.crm.modules.ticket.model.service.TicketService;
import ir.parto.crm.modules.ticket.model.service.TicketStageParameterService;
import ir.parto.crm.modules.ticket.model.service.TicketStageParameterValueService;
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
@RequestMapping("/v1/ticket/ticketStageParameterValue")
public class TicketStageParameterValueRestController implements RestControllerInterface {
    private TicketStageParameterValueService ticketStageParameterValueService;
    private TicketStageParameterValueValidate ticketStageParameterValueValidate;
    private TicketService ticketService;
    private TicketStageParameterService ticketStageParameterService;

    @Autowired
    public TicketStageParameterValueRestController(TicketStageParameterService ticketStageParameterService,TicketService ticketService,TicketStageParameterValueService ticketStageParameterValueService, TicketStageParameterValueValidate ticketStageParameterValueValidate) {
        this.ticketStageParameterValueService = ticketStageParameterValueService;
        this.ticketStageParameterValueValidate = ticketStageParameterValueValidate;
        this.ticketService = ticketService;
        this.ticketStageParameterService = ticketStageParameterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "TicketStageParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageParameterValue - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.ticketStageParameterValueValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<TicketStageParameterValue> ticketStageParameterValuePage = this.ticketStageParameterValueService.findAllItem
                    (PageableRequest.getInstance().createPageRequest(page, "TicketStageParameterValue", sortProperty, sortOrder));
            return new ApiResponse("Success", ticketStageParameterValuePage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody TicketStageParameterValue ticketStageParameterValue) {
        if (CheckPermission.getInstance().check("admin_add", "TicketStageParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageParameterValue - admin_add - access denied!"))
                    .getFaultResponse();
        }

        ticketStageParameterValue.setTicketStageParameterValueId(null);

        ValidateObject validateObject = this.ticketStageParameterValueValidate.validateAddNewItem(ticketStageParameterValue);
        if (validateObject.getResult().equals("success")) {
            ticketStageParameterValue.setTicket(this.ticketService.findOne(ticketStageParameterValue.getTicket()));
            ticketStageParameterValue.setTicketStageParameter(this.ticketStageParameterService.findOne(ticketStageParameterValue.getTicketStageParameter()));
            return new ApiResponse("Success", Arrays.asList(this.ticketStageParameterValueService.addNewItem(ticketStageParameterValue)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody TicketStageParameterValue ticketStageParameterValue) {
        if (CheckPermission.getInstance().check("admin_update", "TicketStageParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageParameterValue - admin_update - access denied!"))
                    .getFaultResponse();
        }

        ticketStageParameterValue.setTicketStageParameterValueId(id);

        ValidateObject validateObject = this.ticketStageParameterValueValidate.validateUpdateItem(ticketStageParameterValue);
        if (validateObject.getResult().equals("success")) {
            try {
                ticketStageParameterValue.setTicket(this.ticketService.findOne(ticketStageParameterValue.getTicket()));
                ticketStageParameterValue.setTicketStageParameter(this.ticketStageParameterService.findOne(ticketStageParameterValue.getTicketStageParameter()));
                return new ApiResponse("Success", Arrays.asList(this.ticketStageParameterValueService.updateItem(ticketStageParameterValue)))
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
        if (CheckPermission.getInstance().check("admin_delete", "TicketStageParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageParameterValue - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        TicketStageParameterValue ticketStageParameterValue = new TicketStageParameterValue();
        ticketStageParameterValue.setTicketStageParameterValueId(id);
        ValidateObject validateObject = this.ticketStageParameterValueValidate.deleteItem(ticketStageParameterValue);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStageParameterValueService.deleteItem(ticketStageParameterValue)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "TicketStageParameterValue")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageParameterValue - admin_show - access denied!"))
                    .getFaultResponse();
        }

        TicketStageParameterValue ticketStageParameterValue = new TicketStageParameterValue();
        ticketStageParameterValue.setTicketStageParameterValueId(id);
        ValidateObject validateObject = this.ticketStageParameterValueValidate.findOne(ticketStageParameterValue);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStageParameterValueService.findOne(ticketStageParameterValue)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}


