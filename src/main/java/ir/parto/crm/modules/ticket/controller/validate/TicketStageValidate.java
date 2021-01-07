package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.service.AdminService;
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
    private AdminService adminService;

    @Autowired
    public TicketStageValidate(TicketStageService ticketStageService, AdminService adminService) {
        this.ticketStageService = ticketStageService;
        this.adminService = adminService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketStage ticketStage) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStage == null) {
            errorList.add("Object is null");
        } else {
            if (ticketStage.getTitle() == null || ticketStage.getTitle().trim() == null
                    || ticketStage.getTitle().trim().isEmpty()) {
                errorList.add("Title is required!");
            } else {
                TicketStage exist = this.ticketStageService.findByIsDeletedIsNullAndTitle(ticketStage.getTitle().trim());
                if (exist != null && exist.getTitle().equals(ticketStage.getTitle().trim())) {
                    errorList.add("Title is duplicate");
                }
            }

            if (ticketStage.getAdmin() != null && (ticketStage.getAdmin().getAdminId() == null || ticketStage.getAdmin().getAdminId() == 0)) {
                errorList.add("Admin is required");
            } else if (!this.adminService.existsById(ticketStage.getAdmin().getAdminId())) {
                errorList.add("Admin not defined");
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
            if(!ticketStageService.existsById(ticketStage.getTicketStageId())){
                errorList.add("Ticket Stage not defined");
            }else {
                if (ticketStage.getTicketStageId() == null || (ticketStage.getTitle().trim().isEmpty())) {
                    errorList.add("Title is required");
                }
                if (ticketStage.getTitle() != null) {
                    TicketStage exist = this.ticketStageService.findByIsDeletedIsNullAndTitle(ticketStage.getTitle().trim());
                    if (exist != null && exist.getTitle().equals(ticketStage.getTitle().trim()) &&
                            !exist.getTicketStageId().equals(ticketStage.getTicketStageId())) {
                        errorList.add("Title is duplicate");
                    }
                }

                if (ticketStage.getAdmin() != null && (ticketStage.getAdmin().getAdminId() == null || ticketStage.getAdmin().getAdminId() == 0)) {
                    errorList.add("Admin is required");
                } else if (ticketStage.getAdmin() != null && !this.adminService.existsById(ticketStage.getAdmin().getAdminId())) {
                    errorList.add("Admin not defined");
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
    public ValidateObject deleteItem(TicketStage ticketStage) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStage == null) {
            errorList.add("Object is null");
        } else if (!this.ticketStageService.existsById(ticketStage.getTicketStageId())) {
            errorList.add("Ticket Stage not defined");
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
        } else if (!this.ticketStageService.existsById(ticketStage.getTicketStageId())) {
            errorList.add("Ticket Stage not defined");
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
