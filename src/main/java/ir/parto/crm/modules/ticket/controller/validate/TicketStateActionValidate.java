package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.TicketStateAction;
import ir.parto.crm.modules.ticket.model.service.TicketStateActionService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class TicketStateActionValidate implements ValidateInterface<TicketStateAction> {
    private TicketStateActionService ticketStateActionService;

    @Autowired
    public TicketStateActionValidate(TicketStateActionService ticketStateActionService) {
        this.ticketStateActionService = ticketStateActionService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketStateAction ticketStateAction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateAction == null) {
            errorList.add("Object is null");
        } else {
            if (ticketStateAction.getTitle() == null) {
                errorList.add("Title is required!");
            } else {
                TicketStateAction exist = this.ticketStateActionService.findByIsDeletedIsNullAndTitle(ticketStateAction.getTitle());
                if (exist != null && exist.getTitle().equals(ticketStateAction.getTitle())) {
                    errorList.add("Title is duplicate");
                }
            }

            if (ticketStateAction.getTicketStateActionType() != null || ticketStateAction.getTicketStateActionType().getTicketStateActionTypeId() == null || ticketStateAction.getTicketStateActionType().getTicketStateActionTypeId() == 0) {
                errorList.add("TicketStateActionType is required");
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
    public ValidateObject validateUpdateItem(TicketStateAction ticketStateAction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateAction == null) {
            errorList.add("Object is null");
        } else {
            if (ticketStateAction.getTitle() != null) {
                TicketStateAction exist = this.ticketStateActionService.findByIsDeletedIsNullAndTitle(ticketStateAction.getTitle());
                if (exist != null && !exist.getTicketStateActionId().equals(ticketStateAction.getTicketStateActionId())) {
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
    public ValidateObject deleteItem(TicketStateAction ticketStateAction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateAction == null) {
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
    public ValidateObject findOne(TicketStateAction ticketStateAction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateAction == null) {
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
    public ValidateObject findById(TicketStateAction ticketStateAction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateAction == null) {
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
