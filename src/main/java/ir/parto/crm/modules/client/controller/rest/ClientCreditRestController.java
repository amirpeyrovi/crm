package ir.parto.crm.modules.client.controller.rest;

import ir.parto.crm.modules.client.controller.validate.ClientCreditValidate;
import ir.parto.crm.modules.client.controller.validate.ClientValidate;
import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.entity.ClientCredit;
import ir.parto.crm.modules.client.model.service.ClientCreditService;
import ir.parto.crm.modules.client.model.service.ClientService;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/v1/clientCredit")
public class ClientCreditRestController  implements RestControllerInterface {
    private ClientCreditService clientCreditService;
    private ClientCreditValidate clientCreditValidate;
    private ClientService clientService;
    private ClientValidate clientValidate;

    @Autowired
    public ClientCreditRestController(ClientCreditService clientCreditService, ClientCreditValidate clientCreditValidate
    ,ClientService clientService ,ClientValidate clientValidate) {
        this.clientCreditService = clientCreditService;
        this.clientCreditValidate = clientCreditValidate;
        this.clientService = clientService;
        this.clientValidate = clientValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAllClientCredit(Pageable pageable){
        ValidateObject validateObject = this.clientCreditValidate.findAll();
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Pageable pageable0 = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        Page<ClientCredit> clientCreditList = this.clientCreditService.findAllItem(pageable0);
        return new ApiResponse("success",new ArrayList(Arrays.asList(clientCreditList))).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object findOneClientCredit(@PathVariable("id") long id){
        ClientCredit clientCredit = this.clientCreditService.findById(id);
        ValidateObject validateObject = this.clientCreditValidate.findById(clientCredit);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        return new ApiResponse("success", new ArrayList(Arrays.asList(clientCredit))).getSuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addClientCredit(@RequestBody ClientCredit clientCredit){
        ValidateObject validateObject = this.clientCreditValidate.validateAddNewItem(clientCredit);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Client client = this.clientService.findById(clientCredit.getClient().getClientId());
        ValidateObject clientValidateObject = this.clientValidate.findOne(client);
        if(clientValidateObject.getResult().equals("error")){
            return new ApiResponse("error",101,clientValidateObject.getMessages()).getFaultResponse();
        }
        clientCredit.setClient(client);
        ClientCredit clientCreditAdded = this.clientCreditService.addNewItem(clientCredit);
        return new ApiResponse("success", new ArrayList(Arrays.asList(clientCreditAdded))).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Object deleteClientCredit(@PathVariable("id") long id){
        ClientCredit clientCredit = this.clientCreditService.findById(id);
        ValidateObject validateObject = this.clientCreditValidate.findById(clientCredit);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        clientCredit.setDeletedBy(principal.getName());
        clientCredit.setDeletedAt(LocalDateTime.now());
        this.clientCreditService.deleteItem(clientCredit);
        return new ApiResponse("success", new ArrayList(Arrays.asList(clientCredit))).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Object updateClientCredit(@PathVariable("id") long id,@RequestBody ClientCredit clientCredit){
        ClientCredit exist = this.clientCreditService.findById(id);
        ValidateObject validateObject = this.clientCreditValidate.validateUpdateItem(exist);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        if(clientCredit.getClient() != null ){
            Client client = this.clientService.findById(clientCredit.getClient().getClientId());
            ValidateObject clientValidateObject = this.clientValidate.findOne(client);
            if(clientValidateObject.getResult().equals("error")){
                return new ApiResponse("error",101,clientValidateObject.getMessages()).getFaultResponse();
            }
            clientCredit.setClient(client);
        }else{
            clientCredit.setClient(exist.getClient());
        }
        clientCredit.setClientCreditId(id);
        try {
            ClientCredit updatedClientCredit = this.clientCreditService.updateItem(clientCredit);
            return new ApiResponse("success", new ArrayList(Arrays.asList(updatedClientCredit))).getSuccessResponse();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return new ApiResponse("error", 102,new ArrayList(Arrays.asList("An error occurrd during update"))).getFaultResponse();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return new ApiResponse("error", 102,new ArrayList(Arrays.asList("An error occurrd during update"))).getFaultResponse();
        }
    }

}
