package ir.parto.crm.modules.client.controller.rest;

import ir.parto.crm.modules.client.controller.validate.ClientExternalCodeValidate;
import ir.parto.crm.modules.client.model.entity.ClientExternalCode;
import ir.parto.crm.modules.client.model.service.ClientExternalCodeService;
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
@RequestMapping("/V1/clientExternalCode")
public class ClientExternalCodeRestController implements RestControllerInterface {
    private ClientExternalCodeService clientExternalCodeService;
    private ClientExternalCodeValidate clientExternalCodeValidate;

    @Autowired
    public ClientExternalCodeRestController(ClientExternalCodeService clientExternalCodeService, ClientExternalCodeValidate clientExternalCodeValidate) {
        this.clientExternalCodeService = clientExternalCodeService;
        this.clientExternalCodeValidate = clientExternalCodeValidate;
    }

    @RequestMapping(value = {"/page/{page}/size/{size}",""},method = RequestMethod.GET)
    public Object findAll(@PathVariable(name = "page",required = false,value = "0") int page,
                                @PathVariable(name="size",required = false,value = "25") int size){
        ValidateObject validateObject = this.clientExternalCodeValidate.findAll();
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Pageable pageable = PageRequest.of(page,size);
        Page<ClientExternalCode> clientList = this.clientExternalCodeService.findAllItem(pageable);
        return new ApiResponse("success",clientList).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object findOneClient(@PathVariable("id") long id){
        ClientExternalCode client = this.clientExternalCodeService.findById(id);
        ValidateObject validateObject = this.clientExternalCodeValidate.findById(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        return new ApiResponse("success", Arrays.asList(client)).getSuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addClient(@RequestParam ClientExternalCode clientExternalCode){
        ValidateObject validateObject = this.clientExternalCodeValidate.validateAddNewItem(clientExternalCode);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        ClientExternalCode clientAdded = this.clientExternalCodeService.addNewItem(clientExternalCode);
        return new ApiResponse("success", Arrays.asList(clientAdded)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Object deleteClient(@RequestParam("id") long id){
        ClientExternalCode client = this.clientExternalCodeService.findById(id);
        ValidateObject validateObject = this.clientExternalCodeValidate.findById(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        this.clientExternalCodeService.deleteItem(client);
        return new ApiResponse("success", Arrays.asList(client)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Object updateClient(@RequestParam("id") long id){
        ClientExternalCode client = this.clientExternalCodeService.findById(id);
        ValidateObject validateObject = this.clientExternalCodeValidate.validateUpdateItem(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        try {
            ClientExternalCode updatedClient = this.clientExternalCodeService.updateItem(client);
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