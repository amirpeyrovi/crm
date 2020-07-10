package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.TicketState;
import ir.parto.crm.modules.ticket.model.service.TicketStateService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class TicketStateValidate implements ValidateInterface<TicketState> {
    private TicketStateService ticketStateService;

    @Autowired
    public TicketStateValidate(TicketStateService ticketStateService) {
        this.ticketStateService = ticketStateService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketState ticketState) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketState == null) {
            errorList.add("Object is null");
        } else {
            if (ticketState.getTitle() == null) {
                errorList.add("Title is required!");
            } else {
                TicketState exist = this.ticketStateService.findByIsDeletedIsNullAndTitle(ticketState.getTitle());
                if (exist != null && exist.getTitle().equals(ticketState.getTitle())) {
                    errorList.add("Title is duplicate");
                }
            }

            if (ticketState.getTicketStateAction() == null || ticketState.getTicketStateAction().getTicketStateActionId() == null || ticketState.getTicketStateAction().getTicketStateActionId() == 0) {
                errorList.add("Action of ticketState is required");
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
    public ValidateObject validateUpdateItem(TicketState ticketState) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketState == null) {
            errorList.add("Object is null");
        } else {
            if (ticketState.getTitle() != null) {
                TicketState exist = this.ticketStateService.findByIsDeletedIsNullAndTitle(ticketState.getTitle());
                if (exist != null && exist.getTitle().equals(ticketState.getTitle())) {
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
    public ValidateObject deleteItem(TicketState ticketState) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketState == null) {
            errorList.add("TicketState is not defined");
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
    public ValidateObject findOne(TicketState ticketState) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketState == null) {
            errorList.add("TicketState is not defined");
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
    public ValidateObject findById(TicketState ticketState) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketState == null) {
            errorList.add("TicketState is not defined");
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
