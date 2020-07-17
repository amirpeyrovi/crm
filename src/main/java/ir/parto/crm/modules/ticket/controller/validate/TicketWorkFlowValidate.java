package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.TicketWorkFlow;
import ir.parto.crm.modules.ticket.model.service.TicketStageService;
import ir.parto.crm.modules.ticket.model.service.TicketStateService;
import ir.parto.crm.modules.ticket.model.service.TicketWorkFlowService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class TicketWorkFlowValidate implements ValidateInterface<TicketWorkFlow> {
    private TicketWorkFlowService ticketWorkFlowService;
    private TicketStageService ticketStageService;
    private TicketStateService ticketStateService;

    @Autowired
    public TicketWorkFlowValidate(TicketWorkFlowService ticketWorkFlowService, TicketStageService ticketStageService, TicketStateService ticketStateService) {
        this.ticketWorkFlowService = ticketWorkFlowService;
        this.ticketStageService = ticketStageService;
        this.ticketStateService = ticketStateService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketWorkFlow ticketWorkFlow) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketWorkFlow == null) {
            errorList.add("Object is null");
        } else {
            if (ticketWorkFlow.getTitle() == null) {
                errorList.add("Title is required!");
            } else {
                TicketWorkFlow exist = this.ticketWorkFlowService.findByIsDeletedIsNullAndTitle(ticketWorkFlow.getTitle());
                if (exist != null && exist.getTitle().equals(ticketWorkFlow.getTitle())) {
                    errorList.add("Title is duplicate");
                }
            }
            if(ticketWorkFlow.getNextTicketStage() == null || ticketWorkFlow.getNextTicketStage().getTicketStageId() == null || ticketWorkFlow.getNextTicketStage().getTicketStageId() == 0)
            {
                errorList.add("Next Stage of ticket is required!");
            }else {
                if (!this.ticketStageService.existsById(ticketWorkFlow.getNextTicketStage().getTicketStageId())) {
                    errorList.add("TicketStage Not Defined");
                }
            }

            if(ticketWorkFlow.getPreviousTicketStage() != null && ticketWorkFlow.getPreviousTicketStage().getTicketStageId() != null && ticketWorkFlow.getPreviousTicketStage().getTicketStageId() == 0)
            {
                if (!this.ticketStageService.existsById(ticketWorkFlow.getPreviousTicketStage().getTicketStageId())) {
                    errorList.add("TicketStage Not Defined");
                }
            }

            if(ticketWorkFlow.getNextTicketState() == null || ticketWorkFlow.getNextTicketState().getTicketStateId() == null || ticketWorkFlow.getNextTicketState().getTicketStateId() == 0)
            {
                errorList.add("Next State of ticket is required!");
            }else {
                if (!this.ticketStateService.existsById(ticketWorkFlow.getNextTicketState().getTicketStateId())) {
                    errorList.add("TicketState Not Defined");
                }
            }

            if(ticketWorkFlow.getPreviousTicketState() != null && ticketWorkFlow.getPreviousTicketState().getTicketStateId() != null && ticketWorkFlow.getPreviousTicketState().getTicketStateId() == 0)
            {
                if (!this.ticketStateService.existsById(ticketWorkFlow.getPreviousTicketState().getTicketStateId())) {
                    errorList.add("TicketState Not Defined");
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
    public ValidateObject validateUpdateItem(TicketWorkFlow ticketWorkFlow) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketWorkFlow == null) {
            errorList.add("Object is null");
        } else {
            if (ticketWorkFlow.getTitle() != null) {
                TicketWorkFlow exist = this.ticketWorkFlowService.findByIsDeletedIsNullAndTitle(ticketWorkFlow.getTitle());
                if (exist != null && exist.getTitle().equals(ticketWorkFlow.getTitle())) {
                    errorList.add("Title is duplicate");
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
    public ValidateObject deleteItem(TicketWorkFlow ticketWorkFlow) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketWorkFlow == null) {
            errorList.add("Object not defined");
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
    public ValidateObject findOne(TicketWorkFlow ticketWorkFlow) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketWorkFlow == null) {
            errorList.add("Object not defined");
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
    public ValidateObject findById(TicketWorkFlow ticketWorkFlow) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketWorkFlow == null) {
            errorList.add("Object not defined");
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
