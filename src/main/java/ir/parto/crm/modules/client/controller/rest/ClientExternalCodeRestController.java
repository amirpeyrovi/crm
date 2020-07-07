package ir.parto.crm.modules.client.controller.rest;

import ir.parto.crm.modules.client.controller.validate.ClientExternalCodeValidate;
import ir.parto.crm.modules.client.controller.validate.ClientValidate;
import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.entity.ClientExternalCode;
import ir.parto.crm.modules.client.model.service.ClientExternalCodeService;
import ir.parto.crm.modules.client.model.service.ClientService;
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
@RequestMapping("/v1/clientExternalCode")
public class ClientExternalCodeRestController implements RestControllerInterface {
    private ClientExternalCodeService clientExternalCodeService;
    private ClientExternalCodeValidate clientExternalCodeValidate;
    private ClientService clientService;
    private ClientValidate clientValidate;

    @Autowired
    public ClientExternalCodeRestController(ClientExternalCodeService clientExternalCodeService, ClientExternalCodeValidate clientExternalCodeValidate
    , ClientService clientService, ClientValidate clientValidate) {
        this.clientExternalCodeService = clientExternalCodeService;
        this.clientExternalCodeValidate = clientExternalCodeValidate;
        this.clientService = clientService;
        this.clientValidate = clientValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(Pageable pageable){
        ValidateObject validateObject = this.clientExternalCodeValidate.findAll();
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Pageable pageable0 = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        Page<ClientExternalCode> clientList = this.clientExternalCodeService.findAllItem(pageable0);
        return new ApiResponse("success",clientList).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object findOneClient(@PathVariable("id") long id){
        ClientExternalCode client = this.clientExternalCodeService.findById(id);
        ValidateObject validateObject = this.clientExternalCodeValidate.findOne(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        return new ApiResponse("success", Arrays.asList(client)).getSuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addClient(@RequestBody ClientExternalCode clientExternalCode){
        ValidateObject validateObject = this.clientExternalCodeValidate.validateAddNewItem(clientExternalCode);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Client client = this.clientService.findById(clientExternalCode.getClient().getClientId());
        ValidateObject clientValidateObject = this.clientValidate.findOne(client);
        if(clientValidateObject.getResult().equals("error")){
            return new ApiResponse("error",101,clientValidateObject.getMessages()).getFaultResponse();
        }
        clientExternalCode.setClient(client);
        ClientExternalCode clientAdded = this.clientExternalCodeService.addNewItem(clientExternalCode);
        return new ApiResponse("success", Arrays.asList(clientAdded)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Object deleteClient(@PathVariable("id") long id){
        ClientExternalCode client = this.clientExternalCodeService.findById(id);
        ValidateObject validateObject = this.clientExternalCodeValidate.findOne(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        ClientExternalCode deleted = this.clientExternalCodeService.deleteItem(client);
        return new ApiResponse("success", Arrays.asList(deleted)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Object updateClient(@PathVariable("id") long id,@RequestBody ClientExternalCode clientExternalCode){
        System.out.println("---------------------------------------");
        ClientExternalCode client = this.clientExternalCodeService.findById(id);
        ValidateObject validateObject = this.clientExternalCodeValidate.findOne(client);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        clientExternalCode.setClientExternalCodeId(id);
        try {
            ClientExternalCode updatedClient = this.clientExternalCodeService.updateItem(clientExternalCode);
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