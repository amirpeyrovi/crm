package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.modules.ticket.controller.transientObject.ticketState.TicketStateAddDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketState.TicketStateDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketState.TicketStateEditDTO;
import ir.parto.crm.modules.ticket.controller.validate.TicketStateValidate;
import ir.parto.crm.modules.ticket.model.entity.TicketState;
import ir.parto.crm.modules.ticket.model.service.TicketStateActionService;
import ir.parto.crm.modules.ticket.model.service.TicketStateService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageHelper;
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
import java.util.List;

@RestController
@TicketAnnotation
@RequestMapping("/v1/ticket/ticketState")
public class TicketStateRestController implements RestControllerInterface {
    private TicketStateService ticketStateService;
    private TicketStateValidate ticketStateValidate;
    private TicketStateActionService ticketStateActionService;

    @Autowired
    public TicketStateRestController(TicketStateService ticketStateService, TicketStateValidate ticketStateValidate
    , TicketStateActionService ticketStateActionService) {
        this.ticketStateService = ticketStateService;
        this.ticketStateValidate = ticketStateValidate;
        this.ticketStateActionService = ticketStateActionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "TicketState")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketState - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.ticketStateValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<TicketState> ticketStatePage = this.ticketStateService.findAllItem(
                    PageableRequest.getInstance().createPageRequest(page, "TicketState", sortProperty, sortOrder));
            List<TicketStateDTO> returnDTO = Convert2Object.mapAll(ticketStatePage.getContent(),TicketStateDTO.class);
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(ticketStatePage,returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody TicketStateAddDTO ticketStateDTO) {
        if (CheckPermission.getInstance().check("admin_add", "TicketState")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketState - admin_add - access denied!"))
                    .getFaultResponse();
        }
        TicketState ticketState = ticketStateDTO.convert2Object();
        ticketState.setTicketStateId(null);
        if(ticketState.getTicketStateAction() != null){
            ticketState.setTicketStateAction(this.ticketStateActionService.findOne(ticketState.getTicketStateAction()));
        }

        ValidateObject validateObject = this.ticketStateValidate.validateAddNewItem(ticketState);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStateService.addNewItem(ticketState).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody TicketStateEditDTO ticketStateDTO) {
        if (CheckPermission.getInstance().check("admin_update", "TicketState")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketState - admin_update - access denied!"))
                    .getFaultResponse();
        }
        TicketState ticketState = ticketStateDTO.convert2Object();
        ticketState.setTicketStateId(id);
        if(ticketState.getTicketStateAction() != null){
            ticketState.setTicketStateAction(this.ticketStateActionService.findOne(ticketState.getTicketStateAction()));
        }

        ValidateObject validateObject = this.ticketStateValidate.validateUpdateItem(ticketState);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.ticketStateService.updateItem(ticketState).convert2Object()))
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
        if (CheckPermission.getInstance().check("admin_delete", "TicketState")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketState - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        TicketState ticketState = new TicketState();
        ticketState.setTicketStateId(id);
        ValidateObject validateObject = this.ticketStateValidate.deleteItem(ticketState);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.ticketStateService.deleteItem(ticketState).convert2Object()))
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
        if (CheckPermission.getInstance().check("admin_show", "TicketState")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketState - admin_show - access denied!"))
                    .getFaultResponse();
        }

        TicketState ticketState = new TicketState();
        ticketState.setTicketStateId(id);
        ValidateObject validateObject = this.ticketStateValidate.findOne(ticketState);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStateService.findOne(ticketState)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}

