package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.TicketStageParameter;
import ir.parto.crm.modules.ticket.model.service.TicketStageParameterService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class TicketStageParameterValidate implements ValidateInterface<TicketStageParameter> {
    private TicketStageParameterService ticketStageParameterService;

    @Autowired
    public TicketStageParameterValidate(TicketStageParameterService ticketStageParameterService) {
        this.ticketStageParameterService = ticketStageParameterService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketStageParameter ticketStageParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageParameter == null) {
            errorList.add("Object is null");
        } else {
            if (ticketStageParameter.getTitle() == null) {
                errorList.add("Title is required!");
            }

            if (ticketStageParameter.getTicketState() != null || ticketStageParameter.getTicketState().getTicketStateId() == null || ticketStageParameter.getTicketState().getTicketStateId() == 0) {
                errorList.add("TicketState is required");
            }

            if (ticketStageParameter.getType() != null ) {
                errorList.add("Type is required");
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
    public ValidateObject validateUpdateItem(TicketStageParameter ticketStageParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageParameter == null) {
            errorList.add("Object is null");
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
    public ValidateObject deleteItem(TicketStageParameter ticketStageParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageParameter == null) {
            errorList.add("Object is null");
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
    public ValidateObject findOne(TicketStageParameter ticketStageParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageParameter == null) {
            errorList.add("Object is null");
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
    public ValidateObject findById(TicketStageParameter ticketStageParameter) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageParameter == null) {
            errorList.add("Object is null");
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
