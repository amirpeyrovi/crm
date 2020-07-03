package ir.parto.crm.modules.client.controller.rest;

import ir.parto.crm.modules.client.controller.validate.ClientValidate;
import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.service.ClientService;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/V1/client")
public class ClientRestController implements RestControllerInterface {
    private ClientService clientService;
    private ClientValidate clientValidate;

    @Autowired
    public ClientRestController(ClientService clientService, ClientValidate clientValidate) {
        this.clientService = clientService;
        this.clientValidate = clientValidate;
    }

    @RequestMapping(value = {"/page/{page}/size/{size}",""},method = RequestMethod.GET)
    public Object findAllClient(@PathVariable(name = "page",required = false,value = "0") int page,
                                @PathVariable(name="size",required = false,value = "25") int size){
        ValidateObject validateObject = this.clientValidate.findAll();
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Pageable pageable = PageRequest.of(page,size);
        Page<Client> clientList = this.clientService.findAllItem(pageable);
        return new ApiResponse("success",clientList).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object findOneClient(@PathVariable("id") long id){
        Client client = this.clientService.findById(id);
        ValidateObject validateObject = this.clientValidate.findById(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        return new ApiResponse("success", Arrays.asList(client)).getSuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addClient(@RequestParam Client client){
        ValidateObject validateObject = this.clientValidate.validateAddNewItem(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Client clientAdded = this.clientService.addNewItem(client);
        return new ApiResponse("success", Arrays.asList(clientAdded)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Object deleteClient(@RequestParam("id") long id){
        Client client = this.clientService.findById(id);
        ValidateObject validateObject = this.clientValidate.findById(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
//        Principal principal = SecurityContextHolder.getContext().getAuthentication();
//        client.setDeletedBy(principal.getName());
//        client.setDeletedAt(LocalDateTime.now());
        this.clientService.deleteItem(client);
        return new ApiResponse("success", Arrays.asList(client)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Object updateClient(@RequestParam("id") long id){
        Client client = this.clientService.findById(id);
        ValidateObject validateObject = this.clientValidate.validateUpdateItem(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }

        try {
            Client updatedClient = this.clientService.updateItem(client);
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