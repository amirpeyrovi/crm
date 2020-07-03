package ir.parto.crm.modules.client.controller.rest;

import ir.parto.crm.modules.client.controller.validate.ClientCreditValidate;
import ir.parto.crm.modules.client.model.entity.ClientCredit;
import ir.parto.crm.modules.client.model.service.ClientCreditService;
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
import java.util.Arrays;

@RestController
@RequestMapping("/V1/clientCredit")
public class ClientCreditRestController  implements RestControllerInterface {
    private ClientCreditService clientCreditService;
    private ClientCreditValidate clientCreditValidate;

    @Autowired
    public ClientCreditRestController(ClientCreditService clientCreditService, ClientCreditValidate clientCreditValidate) {
        this.clientCreditService = clientCreditService;
        this.clientCreditValidate = clientCreditValidate;
    }

    @RequestMapping(value = {"/page/{page}/size/{size}",""},method = RequestMethod.GET)
    public Object findAllClientCredit(@PathVariable(name = "page",required = false,value = "0") int page,
                                @PathVariable(name="size",required = false,value = "25") int size){
        ValidateObject validateObject = this.clientCreditValidate.findAll();
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Pageable pageable = PageRequest.of(page,size);
        Page<ClientCredit> clientCreditList = this.clientCreditService.findAllItem(pageable);
        return new ApiResponse("success",clientCreditList).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object findOneClientCredit(@PathVariable("id") long id){
        ClientCredit clientCredit = this.clientCreditService.findById(id);
        ValidateObject validateObject = this.clientCreditValidate.findById(clientCredit);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        return new ApiResponse("success", Arrays.asList(clientCredit)).getSuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addClientCredit(@RequestParam ClientCredit clientCredit){
        ValidateObject validateObject = this.clientCreditValidate.validateAddNewItem(clientCredit);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        ClientCredit clientCreditAdded = this.clientCreditService.addNewItem(clientCredit);
        return new ApiResponse("success", Arrays.asList(clientCreditAdded)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Object deleteClientCredit(@RequestParam("id") long id){
        ClientCredit clientCredit = this.clientCreditService.findById(id);
        ValidateObject validateObject = this.clientCreditValidate.findById(clientCredit);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        clientCredit.setDeletedBy(principal.getName());
        clientCredit.setDeletedAt(LocalDateTime.now());
        this.clientCreditService.deleteItem(clientCredit);
        return new ApiResponse("success", Arrays.asList(clientCredit)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Object updateClientCredit(@RequestParam("id") long id){
        ClientCredit clientCredit = this.clientCreditService.findById(id);
        ValidateObject validateObject = this.clientCreditValidate.validateUpdateItem(clientCredit);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }

        try {
            ClientCredit updatedClientCredit = this.clientCreditService.updateItem(clientCredit);
            return new ApiResponse("success", Arrays.asList(updatedClientCredit)).getSuccessResponse();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return new ApiResponse("error", 102,Arrays.asList("an error occurrd during update")).getFaultResponse();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return new ApiResponse("error", 102,Arrays.asList("an error occurrd during update")).getFaultResponse();
        }
    }

}
