package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.modules.ticket.controller.validate.TicketStageParameterValidate;
import ir.parto.crm.modules.ticket.model.entity.TicketStageParameter;
import ir.parto.crm.modules.ticket.model.entity.TicketState;
import ir.parto.crm.modules.ticket.model.service.TicketStageParameterService;
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
@RequestMapping("/v1/ticket/ticketStageParameter")
public class TicketStageParameterRestController implements RestControllerInterface {
    private TicketStageParameterService ticketStageParameterService;
    private TicketStageParameterValidate ticketStageParameterValidate;
    private TicketStateService ticketStateService;

    @Autowired
    public TicketStageParameterRestController(TicketStageParameterService ticketStageParameterService, TicketStageParameterValidate ticketStageParameterValidate
    ,TicketStateService ticketStateService) {
        this.ticketStageParameterService = ticketStageParameterService;
        this.ticketStageParameterValidate = ticketStageParameterValidate;
        this.ticketStateService = ticketStateService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "TicketStageParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageParameter - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.ticketStageParameterValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<TicketStageParameter> ticketStageParameterPage = this.ticketStageParameterService.findAllItem
                    (PageableRequest.getInstance().createPageRequest(page, "TicketStageParameter",
                            sortProperty, sortOrder));
            return new ApiResponse("Success", ticketStageParameterPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody TicketStageParameter ticketStageParameter) {
        if (CheckPermission.getInstance().check("admin_add", "TicketStageParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageParameter - admin_add - access denied!"))
                    .getFaultResponse();
        }

        ticketStageParameter.setTicketStageParameterId(null);

        ValidateObject validateObject = this.ticketStageParameterValidate.validateAddNewItem(ticketStageParameter);
        if (validateObject.getResult().equals("success")) {
            ticketStageParameter.setTicketState(this.ticketStateService.findOne(ticketStageParameter.getTicketState()));
            return new ApiResponse("Success", Arrays.asList(this.ticketStageParameterService.addNewItem(ticketStageParameter)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody TicketStageParameter ticketStageParameter) {
        if (CheckPermission.getInstance().check("admin_update", "TicketStageParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageParameter - admin_update - access denied!"))
                    .getFaultResponse();
        }

        ticketStageParameter.setTicketStageParameterId(id);

        ValidateObject validateObject = this.ticketStageParameterValidate.validateUpdateItem(ticketStageParameter);
        if (validateObject.getResult().equals("success")) {
            try {
                ticketStageParameter.setTicketState(this.ticketStateService.findOne(ticketStageParameter.getTicketState()));
                return new ApiResponse("Success", Arrays.asList(this.ticketStageParameterService.updateItem(ticketStageParameter)))
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
        if (CheckPermission.getInstance().check("admin_delete", "TicketStageParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageParameter - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        TicketStageParameter ticketStageParameter = new TicketStageParameter();
        ticketStageParameter.setTicketStageParameterId(id);
        ValidateObject validateObject = this.ticketStageParameterValidate.deleteItem(ticketStageParameter);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStageParameterService.deleteItem(ticketStageParameter)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "TicketStageParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageParameter - admin_show - access denied!"))
                    .getFaultResponse();
        }

        TicketStageParameter ticketStageParameter = new TicketStageParameter();
        ticketStageParameter.setTicketStageParameterId(id);
        ValidateObject validateObject = this.ticketStageParameterValidate.findOne(ticketStageParameter);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStageParameterService.findOne(ticketStageParameter)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}


