package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.TicketStage;
import ir.parto.crm.modules.ticket.model.service.TicketStageService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class TicketStageValidate implements ValidateInterface<TicketStage> {
    private TicketStageService ticketStageService;

    @Autowired
    public TicketStageValidate(TicketStageService ticketStageService) {
        this.ticketStageService = ticketStageService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketStage ticketStage) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStage == null) {
            errorList.add("Object is null");
        } else {
            if (ticketStage.getTitle() == null) {
                errorList.add("Title is required!");
            } else {
                TicketStage exist = this.ticketStageService.findByIsDeletedIsNullAndTitle(ticketStage.getTitle());
                if (exist != null && exist.getTitle().equals(ticketStage.getTitle())) {
                    errorList.add("Title is duplicate");
                }
            }

            if (ticketStage.getAdmin() != null || ticketStage.getAdmin().getAdminId() == null || ticketStage.getAdmin().getAdminId() == 0) {
                errorList.add("Admin is required");
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
    public ValidateObject validateUpdateItem(TicketStage ticketStage) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStage == null) {
            errorList.add("Object is null");
        } else {
            if (ticketStage.getTitle() != null) {
                TicketStage exist = this.ticketStageService.findByIsDeletedIsNullAndTitle(ticketStage.getTitle());
                if (exist != null && exist.getTitle().equals(ticketStage.getTitle())) {
                    errorList.add("Title is duplicate");
                }
            }

            if (ticketStage.getAdmin() != null || ticketStage.getAdmin().getAdminId() == null || ticketStage.getAdmin().getAdminId() == 0) {
                errorList.add("Admin is required");
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
    public ValidateObject deleteItem(TicketStage ticketStage) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStage == null) {
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
    public ValidateObject findOne(TicketStage ticketStage) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStage == null) {
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
    public ValidateObject findById(TicketStage ticketStage) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStage == null) {
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
