package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.TicketStateAction;
import ir.parto.crm.modules.ticket.model.entity.TicketStateActionType;
import ir.parto.crm.modules.ticket.model.service.TicketStateActionService;
import ir.parto.crm.modules.ticket.model.service.TicketStateActionTypeService;
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
    private TicketStateActionTypeService ticketStateActionTypeService;

    @Autowired
    public TicketStateActionValidate(TicketStateActionService ticketStateActionService,
                                     TicketStateActionTypeService ticketStateActionTypeService) {
        this.ticketStateActionService = ticketStateActionService;
        this.ticketStateActionTypeService = ticketStateActionTypeService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketStateAction ticketStateAction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateAction == null) {
            errorList.add("Object is null");
        } else {
            if (ticketStateAction.getTitle() == null || ticketStateAction.getTitle().trim().isEmpty()) {
                errorList.add("Title is required!");
            } else {
                TicketStateAction exist = this.ticketStateActionService.findByIsDeletedIsNullAndTitle(
                        ticketStateAction.getTitle().trim());
                if (exist != null && exist.getTitle().equals(ticketStateAction.getTitle().trim())) {
                    errorList.add("Title is duplicate");
                }
            }

            if (ticketStateAction.getTicketStateActionType() != null && (
                    ticketStateAction.getTicketStateActionType().getTicketStateActionTypeId() == null
                            || ticketStateAction.getTicketStateActionType().getTicketStateActionTypeId() == 0)) {
                errorList.add("TicketStateActionType is required");
            } else {
                TicketStateActionType ticketStateActionTypeExist = this.ticketStateActionTypeService.findById(ticketStateAction.getTicketStateActionType()
                        .getTicketStateActionTypeId());
                if (ticketStateActionTypeExist == null || ticketStateActionTypeExist.getTicketStateActionTypeId() == 0) {
                    errorList.add("TicketStateActionType not defined");
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
    public ValidateObject validateUpdateItem(TicketStateAction ticketStateAction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateAction == null) {
            errorList.add("Object is null");
        } else {
            if (!this.ticketStateActionService.existsById(ticketStateAction.getTicketStateActionId())) {
                errorList.add("Object not defined");
            } else {
                if (ticketStateAction.getTitle() != null && (ticketStateAction.getTitle().trim().isEmpty())) {
                    errorList.add("Title is required");
                }
                if (ticketStateAction.getTitle() != null) {
                    TicketStateAction exist = this.ticketStateActionService.findByIsDeletedIsNullAndTitle(ticketStateAction.getTitle().trim());
                    if (exist != null && !exist.getTicketStateActionId().equals(ticketStateAction.getTicketStateActionId())) {
                        errorList.add("Title is duplicate");
                    }
                }

                if (ticketStateAction.getTicketStateActionType() != null && (
                        ticketStateAction.getTicketStateActionType().getTicketStateActionTypeId() == null
                                || ticketStateAction.getTicketStateActionType().getTicketStateActionTypeId() == 0)) {
                    errorList.add("TicketStateActionType is required");
                } else if (ticketStateAction.getTicketStateActionType() != null) {
                    TicketStateActionType ticketStateActionTypeExist = this.ticketStateActionTypeService.findById(ticketStateAction.getTicketStateActionType()
                            .getTicketStateActionTypeId());
                    if (ticketStateActionTypeExist == null || ticketStateActionTypeExist.getTicketStateActionTypeId() == 0) {
                        errorList.add("TicketStateActionType not defined");
                    }
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
        } else if (!this.ticketStateActionService.existsById(ticketStateAction.getTicketStateActionId())) {
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
    public ValidateObject findOne(TicketStateAction ticketStateAction) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateAction == null) {
            errorList.add("Object is null");
        } else if (!this.ticketStateActionService.existsById(ticketStateAction.getTicketStateActionId())) {
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
