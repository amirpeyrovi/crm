package ir.parto.crm.modules.client.controller.rest;

import ir.parto.crm.modules.client.controller.validate.ClientContactValidate;
import ir.parto.crm.modules.client.controller.validate.ClientValidate;
import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.entity.ClientContact;
import ir.parto.crm.modules.client.model.service.ClientContactService;
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
@RequestMapping("/v1/clientContact")
public class ClientContactRestController implements RestControllerInterface {
    private ClientContactService clientContactService;
    private ClientContactValidate clientContactValidate;
    private ClientService clientService;
    private ClientValidate clientValidate;

    @Autowired
    public ClientContactRestController(ClientContactService clientContactService, ClientContactValidate clientContactValidate
    , ClientService clientService, ClientValidate clientValidate) {
        this.clientContactService = clientContactService;
        this.clientContactValidate = clientContactValidate;
        this.clientService = clientService;
        this.clientValidate = clientValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAllClient(Pageable pageable){
        ValidateObject validateObject = this.clientContactValidate.findAll();
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Pageable pageable0 = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        Page<ClientContact> clientContactList = this.clientContactService.findAllItem(pageable0);
        return new ApiResponse("success",clientContactList).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object findOneClientContact(@PathVariable("id") long id){
        ClientContact clientContact = this.clientContactService.findById(id);
        ValidateObject validateObject = this.clientContactValidate.findOne(clientContact);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        return new ApiResponse("success", Arrays.asList(clientContact)).getSuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addClientContact(@RequestBody ClientContact clientContact){
        ValidateObject validateObject = this.clientContactValidate.validateAddNewItem(clientContact);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Client client = this.clientService.findById(clientContact.getClient().getClientId());
        ValidateObject clientValidateObject = this.clientValidate.findOne(client);
        if(clientValidateObject.getResult().equals("error")){
            return new ApiResponse("error",101,clientValidateObject.getMessages()).getFaultResponse();
        }
        clientContact.setClient(client);
        ClientContact clientContactAdded = this.clientContactService.addNewItem(clientContact);
        return new ApiResponse("success", Arrays.asList(clientContactAdded)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Object deleteClientContact(@PathVariable("id") long id){
        ClientContact clientContact = this.clientContactService.findById(id);
        ValidateObject validateObject = this.clientContactValidate.findOne(clientContact);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        this.clientContactService.deleteItem(clientContact);
        return new ApiResponse("success", Arrays.asList(clientContact)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Object updateClientContact(@PathVariable("id") long id,@RequestBody ClientContact clientContact){
        ClientContact exist = this.clientContactService.findById(id);
        ValidateObject clientContactValidateObject = this.clientContactValidate.findOne(exist);
        if(clientContactValidateObject.getResult().equals("error")){
            return new ApiResponse("error",101,clientContactValidateObject.getMessages()).getFaultResponse();
        }
        clientContact.setClientContactId(id);
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
