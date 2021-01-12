package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.modules.ticket.controller.transientObject.ticketStateActionType.TicketStateActionTypeAddDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketStateActionType.TicketStateActionTypeDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketStateActionType.TicketStateActionTypeEditDTO;
import ir.parto.crm.modules.ticket.controller.validate.TickeStateActionTypeValidate;
import ir.parto.crm.modules.ticket.model.entity.TicketStateActionType;
import ir.parto.crm.modules.ticket.model.service.TickeStateActionTypeService;
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
@RequestMapping("/v1/ticket/ticketStateActionType")
public class TicketStateActionTypeRestController implements RestControllerInterface {
    private TickeStateActionTypeService ticketStateActionTypeService;
    private TickeStateActionTypeValidate ticketStateActionTypeValidate;


    @Autowired
    public TicketStateActionTypeRestController(TickeStateActionTypeService ticketStateActionTypeService, TickeStateActionTypeValidate ticketStateActionTypeValidate) {
        this.ticketStateActionTypeService = ticketStateActionTypeService;
        this.ticketStateActionTypeValidate = ticketStateActionTypeValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "TicketStateActionType")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateActionType - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.ticketStateActionTypeValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<TicketStateActionType> ticketStateActionTypes = this.ticketStateActionTypeService.
                    findAllItem(PageableRequest.getInstance().createPageRequest(page, "TicketStateActionType",
                            sortProperty, sortOrder));
            List<TicketStateActionTypeDTO> returnDTO =  Convert2Object.mapAll(ticketStateActionTypes.getContent(), TicketStateActionTypeDTO.class);
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(ticketStateActionTypes, returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody TicketStateActionTypeAddDTO ticketStateActionTypeDTO) {
        if (CheckPermission.getInstance().check("admin_add", "TicketStateActionType")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateActionType - admin_add - access denied!"))
                    .getFaultResponse();
        }
        TicketStateActionType ticketStateActionType = ticketStateActionTypeDTO.convert2Object();

        ticketStateActionType.setTicketStateActionTypeId(null);

        ValidateObject validateObject = this.ticketStateActionTypeValidate.validateAddNewItem(ticketStateActionType);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStateActionTypeService.
                    addNewItem(ticketStateActionType).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody TicketStateActionTypeEditDTO ticketStateActionTypeDTO) {
        if (CheckPermission.getInstance().check("admin_update", "TicketStateActionType")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateActionType - admin_update - access denied!"))
                    .getFaultResponse();
        }

        TicketStateActionType ticketStateActionType = ticketStateActionTypeDTO.convert2Object();
        ticketStateActionType.setTicketStateActionTypeId(id);

        ValidateObject validateObject = this.ticketStateActionTypeValidate.validateUpdateItem(ticketStateActionType);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(
                        this.ticketStateActionTypeService.updateItem(ticketStateActionType).convert2Object()))
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
        if (CheckPermission.getInstance().check("admin_delete", "TicketStateActionType")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateActionType - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        TicketStateActionType ticketStateActionType = new TicketStateActionType();
        ticketStateActionType.setTicketStateActionTypeId(id);
        ValidateObject validateObject = this.ticketStateActionTypeValidate.deleteItem(ticketStateActionType);
        if (validateObject.getResult().equals("success")) {

            try {
                return new ApiResponse("Success", Arrays.asList(
                        this.ticketStateActionTypeService.deleteItem(ticketStateActionType).convert2Object()))
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
        if (CheckPermission.getInstance().check("admin_show", "TicketStateActionType")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStateActionType - admin_show - access denied!"))
                    .getFaultResponse();
        }

        TicketStateActionType ticketStateActionType = new TicketStateActionType();
        ticketStateActionType.setTicketStateActionTypeId(id);
        ValidateObject validateObject = this.ticketStateActionTypeValidate.findOne(ticketStateActionType);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStateActionTypeService.findOne(ticketStateActionType)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}


