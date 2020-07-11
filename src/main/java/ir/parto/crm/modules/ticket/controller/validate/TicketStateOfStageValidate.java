package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.TicketStateOfStage;
import ir.parto.crm.modules.ticket.model.service.TicketStateOfStageService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class TicketStateOfStageValidate implements ValidateInterface<TicketStateOfStage> {
    private TicketStateOfStageService ticketStateOfStageService;

    @Autowired
    public TicketStateOfStageValidate(TicketStateOfStageService ticketStateOfStageService) {
        this.ticketStateOfStageService = ticketStateOfStageService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketStateOfStage ticketStateOfStage) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateOfStage == null) {
            errorList.add("Object is null");
        } else {
            if (ticketStateOfStage.getTicketState() != null || ticketStateOfStage.getTicketState().getTicketStateId() == null || ticketStateOfStage.getTicketState().getTicketStateId() == 0) {
                errorList.add("TicketState is required");
            }

            if (ticketStateOfStage.getTicketStage() != null || ticketStateOfStage.getTicketStage().getTicketStageId() == null || ticketStateOfStage.getTicketStage().getTicketStageId() == 0) {
                errorList.add("TicketState is required");
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
    public ValidateObject validateUpdateItem(TicketStateOfStage ticketStateOfStage) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateOfStage == null) {
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
    public ValidateObject deleteItem(TicketStateOfStage ticketStateOfStage) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateOfStage == null) {
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
    public ValidateObject findOne(TicketStateOfStage ticketStateOfStage) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateOfStage == null) {
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
    public ValidateObject findById(TicketStateOfStage ticketStateOfStage) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateOfStage == null) {
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
