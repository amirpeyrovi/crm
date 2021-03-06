package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.TicketStateActionType;
import ir.parto.crm.modules.ticket.model.service.TicketStateActionTypeService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class TickeStateActionTypeValidate implements ValidateInterface<TicketStateActionType> {
    private TicketStateActionTypeService ticketStateActionTypeService;

    @Autowired
    public TickeStateActionTypeValidate(TicketStateActionTypeService ticketStateActionTypeService) {
        this.ticketStateActionTypeService = ticketStateActionTypeService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketStateActionType ticketStateActionType) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateActionType == null) {
            errorList.add("Object not defined");
        } else {
            if (ticketStateActionType.getTitle().trim() == null ||
                    ticketStateActionType.getTitle().trim().isEmpty()) {
                errorList.add("Title is required");
            }

            TicketStateActionType exist = this.ticketStateActionTypeService.findByIsDeletedIsNullAndTitle(ticketStateActionType.getTitle().trim());
            if (exist != null && exist.getTicketStateActionTypeId() != 0) {
                errorList.add("Title is duplicate");
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
    public ValidateObject validateUpdateItem(TicketStateActionType ticketStateActionType) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateActionType == null) {
            errorList.add("Object is null");
        } else {
            if(!this.ticketStateActionTypeService.existsById(ticketStateActionType.getTicketStateActionTypeId())){
                errorList.add("Object not defined");
            }else {
                if (ticketStateActionType != null && ticketStateActionType.getTitle() != null &&
                        ticketStateActionType.getTitle().trim().isEmpty()) {
                    errorList.add("Title is required");
                } else {
                    TicketStateActionType exist = this.ticketStateActionTypeService.findByIsDeletedIsNullAndTitle(ticketStateActionType.getTitle().trim());
                    if (exist != null && exist.getTicketStateActionTypeId() != 0
                            && exist.getTicketStateActionTypeId() != ticketStateActionType.getTicketStateActionTypeId()) {
                        errorList.add("Title is duplicate");
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
    public ValidateObject deleteItem(TicketStateActionType ticketStateActionType) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateActionType == null || ticketStateActionType.getTicketStateActionTypeId() == null
                || ticketStateActionType.getTicketStateActionTypeId() == 0
        || !this.ticketStateActionTypeService.existsById(ticketStateActionType.getTicketStateActionTypeId())) {
            errorList.add("Object not defined!");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);

        return validateObject;
    }

    @Override
    public ValidateObject findOne(TicketStateActionType ticketStateActionType) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateActionType == null || ticketStateActionType.getTicketStateActionTypeId() == null
                || ticketStateActionType.getTicketStateActionTypeId() == 0
                || !this.ticketStateActionTypeService.existsById(ticketStateActionType.getTicketStateActionTypeId())) {
            errorList.add("Object not defined!");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);

        return validateObject;
    }

    @Override
    public ValidateObject findById(TicketStateActionType ticketStateActionType) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStateActionType == null || ticketStateActionType.getTicketStateActionTypeId() == null
                || ticketStateActionType.getTicketStateActionTypeId() == 0) {
            errorList.add("Object not defined!");
        }
        if (errorList.size() > 0) {
            validateObject.setResult("error");
        } else {
            validateObject.setResult("success");
        }
        validateObject.setCount(errorList.size());
        validateObject.setMessages(errorList);

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
