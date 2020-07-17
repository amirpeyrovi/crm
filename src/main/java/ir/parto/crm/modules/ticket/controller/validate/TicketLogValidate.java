package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.TicketLog;
import ir.parto.crm.modules.ticket.model.service.TicketLogService;
import ir.parto.crm.modules.ticket.model.service.TicketService;
import ir.parto.crm.modules.ticket.model.service.TicketStageService;
import ir.parto.crm.modules.ticket.model.service.TicketStateService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class TicketLogValidate implements ValidateInterface<TicketLog> {
    private TicketLogService ticketLogService;
    private TicketService ticketService;
    private TicketStateService ticketStateService;
    private TicketStageService ticketStageService;

    @Autowired
    public TicketLogValidate(TicketLogService ticketLogService, TicketService ticketService, TicketStateService ticketStateService, TicketStageService ticketStageService) {
        this.ticketLogService = ticketLogService;
        this.ticketService = ticketService;
        this.ticketStateService = ticketStateService;
        this.ticketStageService = ticketStageService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketLog ticketLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketLog == null) {
            errorList.add("Object is null");
        } else {
            if (ticketLog.getTicketStage() == null || ticketLog.getTicketStage().getTicketStageId() == null || ticketLog.getTicketStage().getTicketStageId() == 0) {
                errorList.add("TicketStage is required!");
            }else{
                if(!ticketStageService.existsById(ticketLog.getTicketStage().getTicketStageId())){
                    errorList.add("TicketStage Not Found!");
                }
            }

            if (ticketLog.getTicketState() == null || ticketLog.getTicketState().getTicketStateId() == null || ticketLog.getTicketState().getTicketStateId() == 0) {
                errorList.add("TicketState is required!");
            }else{
                if(!this.ticketStateService.existsById(ticketLog.getTicketStage().getTicketStageId())){
                    errorList.add("TicketState Not Found!");
                }
            }

            if (ticketLog.getTicket() == null || ticketLog.getTicket().getTicketId() == null || ticketLog.getTicket().getTicketId() == 0) {
                errorList.add("Ticket is required!");
            }else{
                if(!this.ticketService.existsById(ticketLog.getTicket().getTicketId())){
                    errorList.add("TicketState Not Found!");
                }
            }
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject validateUpdateItem(TicketLog ticketLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        errorList.add("Log is not updatable");
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject deleteItem(TicketLog ticketLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        errorList.add("Log is not deletable");
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findOne(TicketLog ticketLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketLog == null || ticketLog.getTicketLogId() == null || ticketLog.getTicketLogId() == 0) {
            errorList.add("TicketLog not defined!");
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findById(TicketLog ticketLog) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketLog == null || ticketLog.getTicketLogId() == null || ticketLog.getTicketLogId() == 0) {
            errorList.add("TicketLog not defined!");
        }

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }

    @Override
    public ValidateObject findAll() {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();

        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }

        return validateObject;
    }
}
