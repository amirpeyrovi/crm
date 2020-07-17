package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.modules.admin.model.service.AdminRoleService;
import ir.parto.crm.modules.ticket.controller.validate.TicketStageRoleValidate;
import ir.parto.crm.modules.ticket.model.entity.TicketStageRole;
import ir.parto.crm.modules.ticket.model.service.TicketStageRoleService;
import ir.parto.crm.modules.ticket.model.service.TicketStageService;
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
@RequestMapping("/v1/ticket/ticketStageRole")
public class TicketStageRoleRestController implements RestControllerInterface {

    private TicketStageRoleService ticketStageRoleService;
    private TicketStageRoleValidate ticketStageRoleValidate;
    private AdminRoleService adminRoleService;
    private TicketStageService ticketStageService;

    @Autowired
    public TicketStageRoleRestController(TicketStageRoleService ticketStageRoleService, TicketStageRoleValidate ticketStageRoleValidate,
                                         AdminRoleService adminRoleService, TicketStageService ticketStageService) {
        this.ticketStageRoleService = ticketStageRoleService;
        this.ticketStageRoleValidate = ticketStageRoleValidate;
        this.adminRoleService = adminRoleService;
        this.ticketStageService = ticketStageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "TicketStageRole")) {
            return new ApiResponse("Error", 101, Arrays.asList(
                    "TicketStageRole - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.ticketStageRoleValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<TicketStageRole> ticketStageRolePage = this.ticketStageRoleService.findAllItem(
                    PageableRequest.getInstance().createPageRequest(page, "TicketStageRole", sortProperty, sortOrder));
            return new ApiResponse("Success", ticketStageRolePage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody TicketStageRole ticketStageRole) {
        if (CheckPermission.getInstance().check("admin_add", "TicketStageRole")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageRole - admin_add - access denied!"))
                    .getFaultResponse();
        }

        ticketStageRole.setTicketStageRoleId(null);

        ValidateObject validateObject = this.ticketStageRoleValidate.validateAddNewItem(ticketStageRole);
        if (validateObject.getResult().equals("success")) {
            ticketStageRole.setAdminRole(this.adminRoleService.findOne(ticketStageRole.getAdminRole()));
            ticketStageRole.setTicketStage(this.ticketStageService.findOne(ticketStageRole.getTicketStage()));
            return new ApiResponse("Success", Arrays.asList(this.ticketStageRoleService.addNewItem(ticketStageRole)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody TicketStageRole ticketStageRole) {
        if (CheckPermission.getInstance().check("admin_update", "TicketStageRole")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageRole - admin_update - access denied!"))
                    .getFaultResponse();
        }

        ticketStageRole.setTicketStageRoleId(id);

        ValidateObject validateObject = this.ticketStageRoleValidate.validateUpdateItem(ticketStageRole);
        if (validateObject.getResult().equals("success")) {
            try {
                ticketStageRole.setAdminRole(this.adminRoleService.findOne(ticketStageRole.getAdminRole()));
                ticketStageRole.setTicketStage(this.ticketStageService.findOne(ticketStageRole.getTicketStage()));
                return new ApiResponse("Success", Arrays.asList(this.ticketStageRoleService.updateItem(ticketStageRole)))
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
        if (CheckPermission.getInstance().check("admin_delete", "TicketStageRole")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageRole - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        TicketStageRole ticketStageRole = new TicketStageRole();
        ticketStageRole.setTicketStageRoleId(id);
        ValidateObject validateObject = this.ticketStageRoleValidate.deleteItem(ticketStageRole);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStageRoleService.deleteItem(ticketStageRole)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "TicketStageRole")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStageRole - admin_show - access denied!"))
                    .getFaultResponse();
        }

        TicketStageRole ticketStageRole = new TicketStageRole();
        ticketStageRole.setTicketStageRoleId(id);
        ValidateObject validateObject = this.ticketStageRoleValidate.findOne(ticketStageRole);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStageRoleService.findOne(ticketStageRole)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}

