package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.modules.ticket.controller.transientObject.ticketLog.TicketLogAddDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketLog.TicketLogDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketLog.TicketLogEditDTO;
import ir.parto.crm.modules.ticket.controller.validate.TicketLogValidate;
import ir.parto.crm.modules.ticket.model.entity.TicketLog;
import ir.parto.crm.modules.ticket.model.service.TicketLogService;
import ir.parto.crm.modules.ticket.model.service.TicketService;
import ir.parto.crm.modules.ticket.model.service.TicketStageService;
import ir.parto.crm.modules.ticket.model.service.TicketStateService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageHelper;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.TicketAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
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
@RequestMapping("/v1/ticket/ticketLog")
public class TicketLogRestController implements RestControllerInterface {
    private TicketLogService ticketLogService;
    private TicketService ticketService;
    private TicketStateService ticketStateService;
    private TicketStageService ticketStageService;
    private TicketLogValidate ticketLogValidate;

    @Autowired
    public TicketLogRestController(TicketLogService ticketLogService, TicketService ticketService, TicketStateService ticketStateService, TicketStageService ticketStageService, TicketLogValidate ticketLogValidate) {
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
            List<TicketLogDTO> returnDTO = new ArrayList<>();
            for (TicketLog ticketLog : ticketLogPage.getContent()) {
                returnDTO.add(ticketLog.convert2Object());
            }
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(ticketLogPage,returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody TicketLogAddDTO ticketLogDTO) {
        if (CheckPermission.getInstance().check("admin_add", "TicketLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketLog - admin_add - access denied!"))
                    .getFaultResponse();
        }

        TicketLog ticketLog = ticketLogDTO.convert2Object();
        ticketLog.setTicketLogId(null);

        ValidateObject validateObject = this.ticketLogValidate.validateAddNewItem(ticketLog);
        if (validateObject.getResult().equals("success")) {
            ticketLog.setTicket(this.ticketService.findOne(ticketLog.getTicket()));
            ticketLog.setTicketStage(this.ticketStageService.findOne(ticketLog.getTicketStage()));
            ticketLog.setTicketState(this.ticketStateService.findOne(ticketLog.getTicketState()));
            return new ApiResponse("Success", Arrays.asList(this.ticketLogService.addNewItem(ticketLog).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody TicketLogEditDTO ticketLogDTO) {
        if (CheckPermission.getInstance().check("admin_update", "TicketLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketLog - admin_update - access denied!"))
                    .getFaultResponse();
        }
        TicketLog ticketLog = ticketLogDTO.convert2Object();
        ticketLog.setTicketLogId(id);

        ValidateObject validateObject = this.ticketLogValidate.validateUpdateItem(ticketLog);
        if (validateObject.getResult().equals("success")) {
            try {
                ticketLog.setTicket(this.ticketService.findOne(ticketLog.getTicket()));
                ticketLog.setTicketStage(this.ticketStageService.findOne(ticketLog.getTicketStage()));
                ticketLog.setTicketState(this.ticketStateService.findOne(ticketLog.getTicketState()));
                return new ApiResponse("Success", Arrays.asList(this.ticketLogService.updateItem(ticketLog).convert2Object()))
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
    public Object deleteOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_delete", "TicketLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketLog - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        TicketLog ticketLog = new TicketLog();
        ticketLog.setTicketLogId(Long.valueOf(id));
        ValidateObject validateObject = this.ticketLogValidate.deleteItem(ticketLog);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketLogService.deleteItem(ticketLog).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_show", "TicketLog")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketLog - admin_show - access denied!"))
                    .getFaultResponse();
        }

        TicketLog ticketLog = new TicketLog();
        ticketLog.setTicketLogId(Long.valueOf(id));
        ValidateObject validateObject = this.ticketLogValidate.findOne(ticketLog);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketLogService.findOne(ticketLog).convert2InfoObject()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
