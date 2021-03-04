package ir.parto.crm.modules.ticket.controller.rest;

import ir.parto.crm.modules.ticket.controller.transientObject.ticketNote.TicketNoteAddDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketNote.TicketNoteDTO;
import ir.parto.crm.modules.ticket.controller.transientObject.ticketNote.TicketNoteEditDTO;
import ir.parto.crm.modules.ticket.controller.validate.TicketNoteValidate;
import ir.parto.crm.modules.ticket.model.entity.TicketNote;
import ir.parto.crm.modules.ticket.model.service.TicketNoteService;
import ir.parto.crm.modules.ticket.model.service.TicketService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.TicketAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@TicketAnnotation
@RequestMapping("/v1/ticket/ticketNote")
public class TicketNoteRestController implements RestControllerInterface {
    private TicketNoteValidate ticketNoteValidate;
    private TicketNoteService ticketNoteService;
    private TicketService ticketService;

    @Autowired
    public TicketNoteRestController(TicketNoteValidate ticketNoteValidate, TicketNoteService ticketNoteService
    , TicketService ticketService) {
        this.ticketNoteValidate = ticketNoteValidate;
        this.ticketNoteService = ticketNoteService;
        this.ticketService = ticketService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "TicketNote")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketNote - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.ticketNoteValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<TicketNote> ticketNotePage = this.ticketNoteService.
                    findAllItem(PageableRequest.getInstance().createPageRequest(page, "TicketNote",
                            sortProperty, sortOrder));
            List<TicketNoteDTO> returnDTO = new ArrayList<>();
            for (TicketNote ticketNote : ticketNotePage.getContent()) {
                returnDTO.add(ticketNote.convert2Object());
            }
            return new ApiResponse("Success", ticketNotePage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody TicketNoteAddDTO ticketNoteDTO) {
        if (CheckPermission.getInstance().check("admin_add", "TicketNote")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketNote - admin_add - access denied!"))
                    .getFaultResponse();
        }
        TicketNote ticketNote = ticketNoteDTO.convert2Object();
        ticketNote.setTicketNoteId(null);
        ValidateObject validateObject = this.ticketNoteValidate.validateAddNewItem(ticketNote);
        if (validateObject.getResult().equals("success")) {
            ticketNote.setTicket(this.ticketService.findOne(ticketNote.getTicket()));
            return new ApiResponse("Success", Arrays.asList(this.ticketNoteService.addNewItem(ticketNote).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") String id, @RequestBody TicketNoteEditDTO ticketNoteDTO) {
        if (CheckPermission.getInstance().check("admin_update", "TicketNote")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketNote - admin_update - access denied!"))
                    .getFaultResponse();
        }
        TicketNote ticketNote = ticketNoteDTO.convert2Object();
        ticketNote.setTicketNoteId(Long.valueOf(id));

        ValidateObject validateObject = this.ticketNoteValidate.validateUpdateItem(ticketNote);
        if (validateObject.getResult().equals("success")) {
            try {
                ticketNote.setTicket(this.ticketService.findOne(ticketNote.getTicket()));
                return new ApiResponse("Success", Arrays.asList(this.ticketNoteService.updateItem(ticketNote).convert2Object()))
                        .getSuccessResponse();
            } catch (InvocationTargetException e) {
                return new ApiResponse("Error", 103, Arrays.asList("An error occurred Try again later"))
                        .getFaultResponse();
            } catch (IllegalAccessException e) {
                return new ApiResponse("Error", 104, Arrays.asList("An error occurred Try again later"))
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_delete", "TicketNote")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketNote - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        TicketNote ticketNote = new TicketNote();
        ticketNote.setTicketNoteId(Long.valueOf(id));
        ValidateObject validateObject = this.ticketNoteValidate.deleteItem(ticketNote);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketNoteService.deleteItem(ticketNote).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_show", "TicketNote")) {
            return new ApiResponse("Error", 101, Arrays.asList("TicketNote - admin_show - access denied!"))
                    .getFaultResponse();
        }

        TicketNote ticketNote = new TicketNote();
        ticketNote.setTicketNoteId(Long.valueOf(id));
        ValidateObject validateObject = this.ticketNoteValidate.findOne(ticketNote);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.ticketNoteService.findOne(ticketNote)
                    .convert2InfoObject()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
