package ir.parto.crm.modules.client.controller.rest;

import ir.parto.crm.modules.client.controller.transientObject.clientCredit.ClientCreditAddDTO;
import ir.parto.crm.modules.client.controller.transientObject.clientCredit.ClientCreditRelationalDTO;
import ir.parto.crm.modules.client.controller.validate.ClientCreditValidate;
import ir.parto.crm.modules.client.controller.validate.ClientValidate;
import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.entity.ClientCredit;
import ir.parto.crm.modules.client.model.service.ClientCreditService;
import ir.parto.crm.modules.client.model.service.ClientService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageHelper;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.ClientAnnotation;
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
import java.util.List;

@RestController
@CrossOrigin
@ClientAnnotation
@RequestMapping(value = "/v1/client/clientCredit", produces = "application/json")
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
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("client_show", "Client_Credit")) {
            return new ApiResponse("Error", 101, Arrays.asList("ClientCredit - client_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.clientCreditValidate.findAll();
        if (validateObject.getResult().equals("error")) {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
        Page<ClientCredit> findPage = this.clientCreditService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ClientCredit", sortProperty, sortOrder));
        List<ClientCreditRelationalDTO> returnDTO = new ArrayList<>();
        for (ClientCredit content : findPage.getContent()) {
            returnDTO.add(content.convert2RelationalObject());
        }
        return new ApiResponse("Success", PageHelper.getInstance().createResponse(findPage, returnDTO))
                .getSuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ClientCreditAddDTO clientCreditAddDTO) {
        ClientCredit clientCredit = clientCreditAddDTO.convert2Object();
        if (CheckPermission.getInstance().check("client_add", "Client_Credit")) {
            return new ApiResponse("Error", 101, Arrays.asList("ClientCredit - client_add - access denied!"))
                    .getFaultResponse();
        }
        if(clientCredit.getClient() != null)
            clientCredit.setClient(this.clientService.findOne(clientCredit.getClient()));
        ValidateObject validateObject = this.clientCreditValidate.validateAddNewItem(clientCredit);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.clientCreditService.addNewItem(clientCredit).convert2RelationalObject()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ClientCreditAddDTO clientCreditAddDTO) {
        if (CheckPermission.getInstance().check("client_update", "Client_Credit")) {
            return new ApiResponse("Error", 101, Arrays.asList("ClientCredit - client_update - access denied!"))
                    .getFaultResponse();
        }

        ClientCredit clientCredit = clientCreditAddDTO.convert2Object();
        clientCredit.setClientCreditId(id);

        ValidateObject validateObject = this.clientCreditValidate.validateUpdateItem(clientCredit);
        if (validateObject.getResult().equals("success")) {
            try {
                clientCredit.setClient(this.clientService.findOne(clientCredit.getClient()));
                return new ApiResponse("Success", Arrays.asList(this.clientCreditService.updateItem(clientCredit)))
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
    public Object deleteOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("client_delete", "Client_Credit")) {
            return new ApiResponse("Error", 101, Arrays.asList("ClientCredit - client_delete - access denied!"))
                    .getFaultResponse();
        }

        ClientCredit clientCredit = new ClientCredit();
        clientCredit.setClientCreditId(id);
        ValidateObject validateObject = this.clientCreditValidate.deleteItem(clientCredit);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.clientCreditService.deleteItem(clientCredit)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("client_show", "Client_Credit")) {
            return new ApiResponse("Error", 101, Arrays.asList("ClientCredit - client_show - access denied!"))
                    .getFaultResponse();
        }

        ClientCredit clientCredit = new ClientCredit();
        clientCredit.setClientCreditId(id);
        ValidateObject validateObject = this.clientCreditValidate.findOne(clientCredit);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.clientCreditService.findOne(clientCredit)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
