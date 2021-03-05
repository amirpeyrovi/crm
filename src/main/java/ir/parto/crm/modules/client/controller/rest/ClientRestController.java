package ir.parto.crm.modules.client.controller.rest;

import ir.parto.crm.modules.client.controller.transientObject.client.ClientAddDTO;
import ir.parto.crm.modules.client.controller.transientObject.client.ClientRelationalDTO;
import ir.parto.crm.modules.client.controller.validate.ClientValidate;
import ir.parto.crm.modules.client.model.entity.Client;
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
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@ClientAnnotation
@RequestMapping(value = "/v1/client/client", produces = "application/json")
public class ClientRestController implements RestControllerInterface {
    private ClientService clientService;
    private ClientValidate clientValidate;

    @Autowired
    public ClientRestController(ClientService clientService, ClientValidate clientValidate) {
        this.clientService = clientService;
        this.clientValidate = clientValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("client_show", "Client_Client")) {
            return new ApiResponse("Error", 101, Arrays.asList("Client - client_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.clientValidate.findAll();
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }

        Page<Client> findPage = this.clientService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "Client", sortProperty, sortOrder));
        List<ClientRelationalDTO> returnDTO = new ArrayList<>();
        for (Client content : findPage.getContent()) {
            returnDTO.add(content.convert2RelationalObject());
        }
        return new ApiResponse("Success", PageHelper.getInstance().createResponse(findPage, returnDTO))
                .getSuccessResponse();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("client_show", "Client_Client")) {
            return new ApiResponse("Error", 101, Arrays.asList("Client - client_show - access denied!"))
                    .getFaultResponse();
        }

        Client client = new Client();
        client.setClientId(id);
        ValidateObject validateObject = this.clientValidate.findOne(client);
        if (validateObject.getResult().equals("success")) {
            client = this.clientService.findById(id);
            return new ApiResponse("Success", Arrays.asList(client.convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ClientAddDTO clientAddDTO) {
        if (CheckPermission.getInstance().check("client_add", "Client_Client")) {
            return new ApiResponse("Error", 101, Arrays.asList("Client - client_add - access denied!"))
                    .getFaultResponse();
        }

        Client client = clientAddDTO.convert2Object();
        ValidateObject validateObject = this.clientValidate.validateAddNewItem(client);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.clientService.addNewItem(client).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("client_delete", "Client_Client")) {
            return new ApiResponse("Error", 101, Arrays.asList("Client - client_delete - access denied!"))
                    .getFaultResponse();
        }

        Client client = new Client();
        client.setClientId(id);
        ValidateObject validateObject = this.clientValidate.deleteItem(client);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.clientService.deleteItem(client)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ClientAddDTO clientAddDTO) {
        if (CheckPermission.getInstance().check("client_update", "Client_Client")) {
            return new ApiResponse("Error", 101, Arrays.asList("Client - client_update - access denied!"))
                    .getFaultResponse();
        }
        Client client = clientAddDTO.convert2Object();
        client.setClientId(id);

        ValidateObject validateObject = this.clientValidate.validateUpdateItem(client);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.clientService.updateItem(client).convert2Object()))
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
}
