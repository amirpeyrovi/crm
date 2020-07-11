package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.TicketStageRole;
import ir.parto.crm.modules.ticket.model.service.TicketStageRoleService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class TicketStageRoleValidate implements ValidateInterface<TicketStageRole> {
    private TicketStageRoleService ticketStageRoleService;

    @Autowired
    public TicketStageRoleValidate(TicketStageRoleService ticketStageRoleService) {
        this.ticketStageRoleService = ticketStageRoleService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketStageRole ticketStageRole) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageRole == null) {
            errorList.add("Object is null");
        } else {
            if (ticketStageRole.getTicketStage() != null || ticketStageRole.getTicketStage().getTicketStageId() == null || ticketStageRole.getTicketStage().getTicketStageId() == 0) {
                errorList.add("TicketStage is required");
            }

            if (ticketStageRole.getAdminRole() != null || ticketStageRole.getAdminRole().getAdminRoleId() == null || ticketStageRole.getAdminRole().getAdminRoleId() == 0) {
                errorList.add("AdminRole is required");
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
    public ValidateObject validateUpdateItem(TicketStageRole ticketStageRole) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageRole == null) {
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
    public ValidateObject deleteItem(TicketStageRole ticketStageRole) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageRole == null) {
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
    public ValidateObject findOne(TicketStageRole ticketStageRole) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageRole == null) {
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
    public ValidateObject findById(TicketStageRole ticketStageRole) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageRole == null) {
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
