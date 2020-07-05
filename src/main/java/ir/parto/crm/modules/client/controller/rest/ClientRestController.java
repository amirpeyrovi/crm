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
@RequestMapping("/v1/client")
public class ClientRestController implements RestControllerInterface {
    private ClientService clientService;
    private ClientValidate clientValidate;

    @Autowired
    public ClientRestController(ClientService clientService, ClientValidate clientValidate) {
        this.clientService = clientService;
        this.clientValidate = clientValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAllClient(Pageable pageable){
        ValidateObject validateObject = this.clientValidate.findAll();
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Pageable pageable0 = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize());
        System.out.println(pageable0.getSort()+"--------------43--------"+pageable0.getPageNumber());
        Page<Client> clientList = this.clientService.findAllItem(pageable);
        return new ApiResponse("success",clientList.getContent()).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object findOneClient(@PathVariable("id") long id){
        Client client = this.clientService.findById(id);
        ValidateObject validateObject = this.clientValidate.findOne(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        return new ApiResponse("success", Arrays.asList(client)).getSuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addClient(@RequestBody Client client){
        ValidateObject validateObject = this.clientValidate.validateAddNewItem(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Client clientAdded = this.clientService.addNewItem(client);
        return new ApiResponse("success", Arrays.asList(clientAdded)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Object deleteClient(@PathVariable("id") long id){
        Client client = this.clientService.findById(id);
        ValidateObject validateObject = this.clientValidate.findOne(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        this.clientService.deleteItem(client);
        return new ApiResponse("success", Arrays.asList(client)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Object updateClient(@PathVariable("id") long id,@RequestBody Client client){

        Client exist = this.clientService.findById(id);
        ValidateObject clientValidateObject = this.clientValidate.findOne(exist);
        if(clientValidateObject.getResult().equals("error")){
            return new ApiResponse("error",101,clientValidateObject.getMessages()).getFaultResponse();
        }
        client.setClientId(id);
        ValidateObject validateObject = this.clientValidate.validateUpdateItem(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }

        try {
            Client updatedClient = this.clientService.updateItem(client);
            return new ApiResponse("success", Arrays.asList(updatedClient)).getSuccessResponse();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return new ApiResponse("error", 102,Arrays.asList("An error occurrd during update")).getFaultResponse();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return new ApiResponse("error", 102,Arrays.asList("An error occurrd during update")).getFaultResponse();
        }
    }

}
