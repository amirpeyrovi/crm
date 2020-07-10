package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.Ticket;
import ir.parto.crm.modules.ticket.model.service.TicketService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class TicketValidate implements ValidateInterface<Ticket> {
    private TicketService ticketService;

    @Autowired
    public TicketValidate(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public ValidateObject validateAddNewItem(Ticket ticket) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticket == null) {
            errorList.add("Object is null");
        } else {
            if (ticket.getTitle() == null) {
                errorList.add("Title is required!");
            } else {
                Ticket exist = this.ticketService.findByIsDeletedIsNullAndTitle(ticket.getTitle());
                if (exist != null && exist.getTitle().equals(ticket.getTitle())) {
                    errorList.add("Title is duplicate");
                }
            }

            if (ticket.getTicketStage() == null || ticket.getTicketStage().getTicketStageId() == null || ticket.getTicketStage().getTicketStageId() == 0) {
                errorList.add("TicketStage is required");
            }

            if (ticket.getTicketState() == null || ticket.getTicketState().getTicketStateId() == null || ticket.getTicketState().getTicketStateId() == 0) {
                errorList.add("TicketState is required");
            }

            if (ticket.getClient() != null || ticket.getClient().getClientId() == null || ticket.getClient().getClientId() == 0) {
                errorList.add("Client is required");
            }

            if (ticket.getService() != null || ticket.getService().getServiceId() == null || ticket.getService().getServiceId() == 0) {
                errorList.add("Service is required");
            }

            if (ticket.getAdmin() != null || ticket.getAdmin().getAdminId() == null || ticket.getAdmin().getAdminId() == 0) {
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
    public ValidateObject validateUpdateItem(Ticket ticket) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticket == null || ticket.getTicketId() == null || ticket.getTicketId() == 0) {
            errorList.add("Ticket not defined!");
        }
        if (ticket.getTitle() != null) {
            Ticket exist = this.ticketService.findByIsDeletedIsNullAndTitle(ticket.getTitle());
            if (exist != null && exist.getTitle().equals(ticket.getTitle())) {
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
    public ValidateObject deleteItem(Ticket ticket) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticket == null || ticket.getTicketId() == null || ticket.getTicketId() == 0) {
            errorList.add("Ticket not defined!");
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
    public ValidateObject findOne(Ticket ticket) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticket == null || ticket.getTicketId() == null || ticket.getTicketId() == 0) {
            errorList.add("Ticket not defined!");
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
    public ValidateObject findById(Ticket ticket) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticket == null || ticket.getTicketId() == null || ticket.getTicketId() == 0) {
            errorList.add("Ticket not defined!");
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
