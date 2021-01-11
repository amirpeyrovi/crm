package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.modules.ticket.controller.transientObject.ticketStateAction.TicketStateActionAddDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketStateAction.TicketStateActionDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketStateAction.TicketStateActionEditDTO;
import ir.parto.crm.modules.ticket.controller.validate.TicketStateActionValidate;
import ir.parto.crm.modules.ticket.model.entity.TicketStateAction;
import ir.parto.crm.modules.ticket.model.service.TicketStateActionService;
import ir.parto.crm.modules.ticket.model.service.TicketStateActionTypeService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.TicketAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.Convert2Object;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@TicketAnnotation
@RequestMapping("/v1/ticket/ticketStateAction")
public class TicketStateActionRestController implements RestControllerInterface {
    private TicketStateActionService ticketStateActionService;
    private TicketStateActionValidate ticketStateActionValidate;
    private TicketStateActionTypeService ticketStateActionTypeService;

    @Autowired
    public TicketStateActionRestController(TicketStateActionService ticketStateActionService, TicketStateActionValidate ticketStateActionValidate
            , TicketStateActionTypeService ticketStateActionTypeService) {
        this.ticketStateActionService = ticketStateActionService;
        this.ticketStateActionValidate = ticketStateActionValidate;
        this.ticketStateActionTypeService = ticketStateActionTypeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "TicketStateAction")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateAction - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.ticketStateActionValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<TicketStateAction> ticketStateActionPage = this.ticketStateActionService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "TicketStateAction", sortProperty, sortOrder));
            return new ApiResponse("Success", Convert2Object.mapAll(ticketStateActionPage.getContent(), TicketStateActionDTO.class))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody TicketStateActionAddDTO ticketStateActionDTO) {
        if (CheckPermission.getInstance().check("admin_add", "TicketStateAction")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateAction - admin_add - access denied!"))
                    .getFaultResponse();
        }
        TicketStateAction ticketStateAction = ticketStateActionDTO.convert2Object();
        ticketStateAction.setTicketStateActionId(null);

        ValidateObject validateObject = this.ticketStateActionValidate.validateAddNewItem(ticketStateAction);
        if (validateObject.getResult().equals("success")) {
            ticketStateAction.setTicketStateActionType(this.ticketStateActionTypeService.findOne(ticketStateAction.getTicketStateActionType()));
            return new ApiResponse("Success", Arrays.asList(
                    this.ticketStateActionService.addNewItem(ticketStateAction).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody TicketStateActionEditDTO ticketStateActionDTO) {
        if (CheckPermission.getInstance().check("admin_update", "TicketStateAction")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateAction - admin_update - access denied!"))
                    .getFaultResponse();
        }
        TicketStateAction ticketStateAction = ticketStateActionDTO.convert2Object();
        ticketStateAction.setTicketStateActionId(id);

        ValidateObject validateObject = this.ticketStateActionValidate.validateUpdateItem(ticketStateAction);
        if (validateObject.getResult().equals("success")) {
            try {
                if (ticketStateAction.getTicketStateActionType() != null) {
                    ticketStateAction.setTicketStateActionType(this.ticketStateActionTypeService.findOne(ticketStateAction.getTicketStateActionType()));
                }
                return new ApiResponse("Success", Arrays.asList(
                        this.ticketStateActionService.updateItem(ticketStateAction).convert2Object()))
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
        if (CheckPermission.getInstance().check("admin_delete", "TicketStateAction")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateAction - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        TicketStateAction ticketStateAction = new TicketStateAction();
        ticketStateAction.setTicketStateActionId(id);
        ValidateObject validateObject = this.ticketStateActionValidate.deleteItem(ticketStateAction);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(
                        this.ticketStateActionService.deleteItem(ticketStateAction).convert2Object()))
                        .getSuccessResponse();
            } catch (Exception e) {
                if (e.getMessage().contains("constraint")) {
                    return new ApiResponse("Error", 103, new ArrayList(Arrays.asList("" +
                            "Integrity constraint violated - child record"))).getFaultResponse();
                } else {
                    return new ApiResponse("Error", 103, new ArrayList(Arrays.asList("An error occurred during the Delete")))
                            .getFaultResponse();
                }
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "TicketStateAction")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateAction - admin_show - access denied!"))
                    .getFaultResponse();
        }

        TicketStateAction ticketStateAction = new TicketStateAction();
        ticketStateAction.setTicketStateActionId(id);
        ValidateObject validateObject = this.ticketStateActionValidate.findOne(ticketStateAction);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStateActionService.findOne(ticketStateAction)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
