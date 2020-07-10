package ir.parto.crm.modules.ticket.controller.validate;

import ir.parto.crm.modules.ticket.model.entity.TicketStateOfStage;
import ir.parto.crm.modules.ticket.model.service.TicketStateOfStageService;
import ir.parto.crm.utils.annotations.ValidationAnnotation;
import ir.parto.crm.utils.interfaces.ValidateInterface;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;

@ValidationAnnotation
public class TicketStateOfStageValidate implements ValidateInterface<TicketStateOfStage> {
    private TicketStateOfStageService ticketStateOfStageService;

    @Autowired
    public TicketStateOfStageValidate(TicketStateOfStageService ticketStateOfStageService) {
        this.ticketStateOfStageService = ticketStateOfStageService;
    }

    @Override
    public ValidateObject validateAddNewItem(TicketStateOfStage ticketStateOfStage) {
        return null;
    }

    @Override
    public ValidateObject validateUpdateItem(TicketStateOfStage ticketStateOfStage) {
        return null;
    }

    @Override
    public ValidateObject deleteItem(TicketStateOfStage ticketStateOfStage) {
        return null;
    }

    @Override
    public ValidateObject findOne(TicketStateOfStage ticketStateOfStage) {
        return null;
    }

    @Override
    public ValidateObject findById(TicketStateOfStage ticketStateOfStage) {
        return null;
    }

    @Override
    public ValidateObject findAll() {
        return null;
    }
}
