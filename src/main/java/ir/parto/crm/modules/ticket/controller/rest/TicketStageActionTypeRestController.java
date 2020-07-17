package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.utils.annotations.TicketAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@TicketAnnotation
@RequestMapping("/v1/ticket/ticketStageActionType")
public class TicketStageActionTypeRestController implements RestControllerInterface {
   
}
