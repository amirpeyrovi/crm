package ir.parto.crm.modules.client.controller.rest;

import ir.parto.crm.modules.client.controller.validate.ClientContactValidate;
import ir.parto.crm.modules.client.model.entity.ClientContact;
import ir.parto.crm.modules.client.model.service.ClientContactService;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@RequestMapping("/V1/clientContact")
public class ClientContactRestController implements RestControllerInterface {
    private ClientContactService clientContactService;
    private ClientContactValidate clientContactValidate;

    @Autowired
    public ClientContactRestController(ClientContactService clientContactService, ClientContactValidate clientContactValidate) {
        this.clientContactService = clientContactService;
        this.clientContactValidate = clientContactValidate;
    }

    @RequestMapping(value = {"/page/{page}/size/{size}",""},method = RequestMethod.GET)
    public Object findAllClient(@PathVariable(name = "page",required = false,value = "0") int page,
                                @PathVariable(name="size",required = false,value = "25") int size){
        ValidateObject validateObject = this.clientContactValidate.findAll();
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Pageable pageable = PageRequest.of(page,size);
        Page<ClientContact> clientContactList = this.clientContactService.findAllItem(pageable);
        return new ApiResponse("success",clientContactList).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object findOneClientContact(@PathVariable("id") long id){
        ClientContact clientContact = this.clientContactService.findById(id);
        ValidateObject validateObject = this.clientContactValidate.findById(clientContact);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        return new ApiResponse("success", Arrays.asList(clientContact)).getSuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addClientContact(@RequestParam ClientContact clientContact){
        ValidateObject validateObject = this.clientContactValidate.validateAddNewItem(clientContact);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        ClientContact clientContactAdded = this.clientContactService.addNewItem(clientContact);
        return new ApiResponse("success", Arrays.asList(clientContactAdded)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Object deleteClientContact(@RequestParam("id") long id){
        ClientContact clientContact = this.clientContactService.findById(id);
        ValidateObject validateObject = this.clientContactValidate.findById(clientContact);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
//        Principal principal = SecurityContextHolder.getContext().getAuthentication();
//        clientContact.setDeletedBy(principal.getName());
//        clientContact.setDeletedAt(LocalDateTime.now());
        this.clientContactService.deleteItem(clientContact);
        return new ApiResponse("success", Arrays.asList(clientContact)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Object updateClientContact(@RequestParam("id") long id){
        ClientContact clientContact = this.clientContactService.findById(id);
        ValidateObject validateObject = this.clientContactValidate.validateUpdateItem(clientContact);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }

        try {
            ClientContact updatedClient = this.clientContactService.updateItem(clientContact);
            return new ApiResponse("success", Arrays.asList(updatedClient)).getSuccessResponse();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return new ApiResponse("error", 102,Arrays.asList("an error occurrd during update")).getFaultResponse();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return new ApiResponse("error", 102,Arrays.asList("an error occurrd during update")).getFaultResponse();
        }
    }
}
