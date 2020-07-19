package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.TicketStageParameterValue;
import ir.parto.crm.modules.ticket.model.service.TicketStageParameterValueService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ValidationAnnotation
public class TicketStageParameterValueValidate  implements ValidateInterface<TicketStageParameterValue> {
    private TicketStageParameterValueService ticketStageParameterValueService;

    @Autowired
    public TicketStageParameterValueValidate(TicketStageParameterValueService ticketStageParameterValueService) {
        this.ticketStageParameterValueService = ticketStageParameterValueService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketStageParameterValue ticketStageParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageParameterValue == null) {
            errorList.add("Object is null");
        } else {
            if (ticketStageParameterValue.getValue() == null) {
                errorList.add("Value is required!");
            }

            if (ticketStageParameterValue.getTicketStageParameter() != null || ticketStageParameterValue.getTicketStageParameter().getTicketStageParameterId() == null || ticketStageParameterValue.getTicketStageParameter().getTicketStageParameterId() == 0) {
                errorList.add("TicketStageParameter is required");
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
    public ValidateObject validateUpdateItem(TicketStageParameterValue ticketStageParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageParameterValue == null) {
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
    public ValidateObject deleteItem(TicketStageParameterValue ticketStageParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageParameterValue == null || !this.ticketStageParameterValueService.existsById(ticketStageParameterValue.getTicketStageParameterValueId())) {
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
    public ValidateObject findOne(TicketStageParameterValue ticketStageParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageParameterValue == null
                || !this.ticketStageParameterValueService.existsById(ticketStageParameterValue.getTicketStageParameterValueId())) {
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
    public ValidateObject findById(TicketStageParameterValue ticketStageParameterValue) {
        List<String> errorList = new ArrayList<>();
        ValidateObject validateObject = new ValidateObject();
        if (ticketStageParameterValue == null || !this.ticketStageParameterValueService.existsById(ticketStageParameterValue.getTicketStageParameterValueId())) {
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
