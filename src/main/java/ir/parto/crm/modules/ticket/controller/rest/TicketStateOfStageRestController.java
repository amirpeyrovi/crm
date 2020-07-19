package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.modules.ticket.controller.validate.TicketStateOfStageValidate;
import ir.parto.crm.modules.ticket.model.entity.TicketStateOfStage;
import ir.parto.crm.modules.ticket.model.service.TicketStageService;
import ir.parto.crm.modules.ticket.model.service.TicketStateOfStageService;
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
@RequestMapping("/v1/ticket/ticketStateOfStage")
public class TicketStateOfStageRestController implements RestControllerInterface {
    private TicketStateOfStageService ticketStateOfStageService;
    private TicketStateOfStageValidate ticketStateOfStageValidate;
    private TicketStageService ticketStageService;

    @Autowired
    public TicketStateOfStageRestController(TicketStageService ticketStageService,TicketStateOfStageService ticketStateOfStageService, TicketStateOfStageValidate ticketStateOfStageValidate) {
        this.ticketStateOfStageService = ticketStateOfStageService;
        this.ticketStateOfStageValidate = ticketStateOfStageValidate;
        this.ticketStageService = ticketStageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "TicketStateOfStage")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateOfStage - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.ticketStateOfStageValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<TicketStateOfStage> ticketStateOfStagesPage = this.ticketStateOfStageService.findAllItem
                    (PageableRequest.getInstance().createPageRequest(page, "TicketStateOfStage", sortProperty, sortOrder));
            return new ApiResponse("Success", ticketStateOfStagesPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody TicketStateOfStage ticketStateOfStage) {
        if (CheckPermission.getInstance().check("admin_add", "TicketStateOfStage")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateOfStage - admin_add - access denied!"))
                    .getFaultResponse();
        }

        ticketStateOfStage.setTicketStateOfStageId(null);

        ValidateObject validateObject = this.ticketStateOfStageValidate.validateAddNewItem(ticketStateOfStage);
        if (validateObject.getResult().equals("success")) {
            ticketStateOfStage.setTicketStage(this.ticketStageService.findOne(ticketStateOfStage.getTicketStage()));
            return new ApiResponse("Success", Arrays.asList(this.ticketStateOfStageService.addNewItem(ticketStateOfStage)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody TicketStateOfStage ticketStateOfStage) {
        if (CheckPermission.getInstance().check("admin_update", "TicketStateOfStage")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateOfStage - admin_update - access denied!"))
                    .getFaultResponse();
        }

        ticketStateOfStage.setTicketStateOfStageId(id);

        ValidateObject validateObject = this.ticketStateOfStageValidate.validateUpdateItem(ticketStateOfStage);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.ticketStateOfStageService.updateItem(ticketStateOfStage)))
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
        if (CheckPermission.getInstance().check("admin_delete", "TicketStateOfStage")) {
            return new ApiResponse("Error", 101, Arrays.asList("Ticket - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        TicketStateOfStage ticketStateOfStage = new TicketStateOfStage();
        ticketStateOfStage.setTicketStateOfStageId(id);
        ValidateObject validateObject = this.ticketStateOfStageValidate.deleteItem(ticketStateOfStage);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStateOfStageService.deleteItem(ticketStateOfStage)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "TicketStateOfStage")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateOfStage - admin_show - access denied!"))
                    .getFaultResponse();
        }

        TicketStateOfStage ticketStateOfStage = new TicketStateOfStage();
        ticketStateOfStage.setTicketStateOfStageId(id);
        ValidateObject validateObject = this.ticketStateOfStageValidate.findOne(ticketStateOfStage);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStateOfStageService.findOne(ticketStateOfStage)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
