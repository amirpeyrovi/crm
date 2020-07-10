package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.TicketStateActionType;
import ir.parto.crm.modules.ticket.model.service.TicketStateActionTypeService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

@ValidationAnnotation
public class TicketStateActionTypeValidate  implements ValidateInterface<TicketStateActionType> {
    private TicketStateActionTypeService ticketStateActionTypeService;

    @Autowired
    public TicketStateActionTypeValidate(TicketStateActionTypeService ticketStateActionTypeService) {
        this.ticketStateActionTypeService = ticketStateActionTypeService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketStateActionType ticketStateActionType) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(TicketStateActionType ticketStateActionType) {
        return null;
    }

    @Override
    public ValidateObject deleteItem(TicketStateActionType ticketStateActionType) {
        return null;
    }

    @Override
    public ValidateObject findOne(TicketStateActionType ticketStateActionType) {
        return null;
    }

    @Override
    public ValidateObject findById(TicketStateActionType ticketStateActionType) {
        return null;
    }

    @Override
    public ValidateObject findAll() {
        return null;
    }
}
