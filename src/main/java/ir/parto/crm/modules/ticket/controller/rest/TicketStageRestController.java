package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.modules.admin.model.service.AdminService;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketStage.TicketStageAddDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketStage.TicketStageDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketStage.TicketStageEditDTO;
import ir.parto.crm.modules.ticket.controller.validate.TicketStageValidate;
import ir.parto.crm.modules.ticket.model.entity.TicketStage;
import ir.parto.crm.modules.ticket.model.service.TicketStageService;
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
@RequestMapping("/v1/ticket/ticketStage")
public class TicketStageRestController implements RestControllerInterface {

    private TicketStageService ticketStageService;
    private AdminService adminService;
    private TicketStageValidate ticketStageValidate;

    @Autowired
    public TicketStageRestController(TicketStageService ticketStageService,TicketStageValidate ticketStageValidate,
                                     AdminService adminService) {
        this.ticketStageService = ticketStageService;
        this.ticketStageValidate = ticketStageValidate;
        this.adminService = adminService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "TicketStage")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStage - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.ticketStageValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<TicketStage> ticketStagePage = this.ticketStageService.findAllItem
                    (PageableRequest.getInstance().createPageRequest(page, "TicketStage", sortProperty, sortOrder));
            return new ApiResponse("Success", Convert2Object.mapAll(ticketStagePage.getContent(), TicketStageDTO.class
            ))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody TicketStageAddDTO ticketStageDTO) {
        if (CheckPermission.getInstance().check("admin_add", "TicketStage")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStage - admin_add - access denied!"))
                    .getFaultResponse();
        }
        TicketStage ticketStage = ticketStageDTO.convert2Object();
        ticketStage.setTicketStageId(null);

        ValidateObject validateObject = this.ticketStageValidate.validateAddNewItem(ticketStage);
        if (validateObject.getResult().equals("success")) {
            ticketStage.setAdmin(this.adminService.findOne(ticketStage.getAdmin()));
            return new ApiResponse("Success", Arrays.asList(this.ticketStageService.addNewItem(ticketStage).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody TicketStageEditDTO ticketStageDTO) {
        if (CheckPermission.getInstance().check("admin_update", "TicketStage")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStage - admin_update - access denied!"))
                    .getFaultResponse();
        }
        TicketStage ticketStage = ticketStageDTO.convert2Object();
        ticketStage.setTicketStageId(id);
        ValidateObject validateObject = this.ticketStageValidate.validateUpdateItem(ticketStage);
        if (validateObject.getResult().equals("success")) {
            try {
                if(ticketStage.getAdmin() != null){
                    ticketStage.setAdmin(this.adminService.findOne(ticketStage.getAdmin()));
                }
                return new ApiResponse("Success", Arrays.asList(this.ticketStageService.updateItem(ticketStage).convert2Object()))
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
        if (CheckPermission.getInstance().check("admin_delete", "TicketStage")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStage - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        TicketStage ticketStage = new TicketStage();
        ticketStage.setTicketStageId(id);
        ValidateObject validateObject = this.ticketStageValidate.deleteItem(ticketStage);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.ticketStageService.deleteItem(ticketStage).convert2Object()))
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
        if (CheckPermission.getInstance().check("admin_show", "TicketStage")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketStage - admin_show - access denied!"))
                    .getFaultResponse();
        }

        TicketStage ticketStage = new TicketStage();
        ticketStage.setTicketStageId(id);
        ValidateObject validateObject = this.ticketStageValidate.findOne(ticketStage);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketStageService.findOne(ticketStage)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
