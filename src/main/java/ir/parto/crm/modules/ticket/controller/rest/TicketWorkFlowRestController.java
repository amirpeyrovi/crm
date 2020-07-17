package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.modules.ticket.controller.validate.TicketWorkFlowValidate;
import ir.parto.crm.modules.ticket.model.entity.TicketWorkFlow;
import ir.parto.crm.modules.ticket.model.service.TicketStageService;
import ir.parto.crm.modules.ticket.model.service.TicketStateService;
import ir.parto.crm.modules.ticket.model.service.TicketWorkFlowService;
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
@RequestMapping("/v1/ticket/ticketWorkFlow")
public class TicketWorkFlowRestController implements RestControllerInterface {
    private TicketStateService ticketStateService;
    private TicketStageService ticketStageService;
    private TicketWorkFlowService ticketWorkFlowService;
    private TicketWorkFlowValidate ticketWorkFlowValidate;

    @Autowired
    public TicketWorkFlowRestController(TicketStateService ticketStateService, TicketStageService ticketStageService, TicketWorkFlowService ticketWorkFlowService, TicketWorkFlowValidate ticketWorkFlowValidate) {
        this.ticketStateService = ticketStateService;
        this.ticketStageService = ticketStageService;
        this.ticketWorkFlowService = ticketWorkFlowService;
        this.ticketWorkFlowValidate = ticketWorkFlowValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "TicketWorkFlow")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketWorkFlow - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.ticketWorkFlowValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<TicketWorkFlow> ticketWorkFlowsPage = this.ticketWorkFlowService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "TicketWorkFlow", sortProperty, sortOrder));
            return new ApiResponse("Success", ticketWorkFlowsPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody TicketWorkFlow ticketWorkFlow) {
        if (CheckPermission.getInstance().check("admin_add", "TicketWorkFlow")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketWorkFlow - admin_add - access denied!"))
                    .getFaultResponse();
        }

        ticketWorkFlow.setTicketWorkFlowId(null);

        ValidateObject validateObject = this.ticketWorkFlowValidate.validateAddNewItem(ticketWorkFlow);
        if (validateObject.getResult().equals("success")) {
            ticketWorkFlow.setNextTicketStage(this.ticketStageService.findOne(ticketWorkFlow.getNextTicketStage()));
            ticketWorkFlow.setNextTicketState(this.ticketStateService.findOne(ticketWorkFlow.getNextTicketState()));
            if(ticketWorkFlow.getPreviousTicketStage() != null){
                ticketWorkFlow.setPreviousTicketStage(this.ticketStageService.findOne(ticketWorkFlow.getPreviousTicketStage()));
            }
            if(ticketWorkFlow.getPreviousTicketState() != null) {
                ticketWorkFlow.setPreviousTicketState(this.ticketStateService.findOne(ticketWorkFlow.getPreviousTicketState()));
            }
            return new ApiResponse("Success", Arrays.asList(this.ticketWorkFlowService.addNewItem(ticketWorkFlow)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody TicketWorkFlow ticketWorkFlow) {
        if (CheckPermission.getInstance().check("admin_update", "TicketWorkFlow")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketWorkFlow - admin_update - access denied!"))
                    .getFaultResponse();
        }

        ticketWorkFlow.setTicketWorkFlowId(id);

        ValidateObject validateObject = this.ticketWorkFlowValidate.validateUpdateItem(ticketWorkFlow);
        if (validateObject.getResult().equals("success")) {
            try {
                ticketWorkFlow.setNextTicketStage(this.ticketStageService.findOne(ticketWorkFlow.getNextTicketStage()));
                ticketWorkFlow.setNextTicketState(this.ticketStateService.findOne(ticketWorkFlow.getNextTicketState()));
                if(ticketWorkFlow.getPreviousTicketStage() != null){
                    ticketWorkFlow.setPreviousTicketStage(this.ticketStageService.findOne(ticketWorkFlow.getPreviousTicketStage()));
                }
                if(ticketWorkFlow.getPreviousTicketState() != null) {
                    ticketWorkFlow.setPreviousTicketState(this.ticketStateService.findOne(ticketWorkFlow.getPreviousTicketState()));
                }
                return new ApiResponse("Success", Arrays.asList(this.ticketStageService.updateItem(ticketWorkFlow)))
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

        TicketWorkFlow ticketWorkFlow = new TicketWorkFlow();
        ticketWorkFlow.setTicketWorkFlowId(id);
        ValidateObject validateObject = this.ticketWorkFlowValidate.deleteItem(ticketWorkFlow);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketWorkFlowService.deleteItem(ticketWorkFlow)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "TicketWorkFlow")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketWorkFlow - admin_show - access denied!"))
                    .getFaultResponse();
        }

        TicketWorkFlow ticketWorkFlow = new TicketWorkFlow();
        ticketWorkFlow.setTicketWorkFlowId(id);
        ValidateObject validateObject = this.ticketWorkFlowValidate.findOne(ticketWorkFlow);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketWorkFlowService.findOne(ticketWorkFlow)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
