package ir.parto.crm.modules.client.controller.rest;

import ir.parto.crm.modules.client.controller.transientObject.clientExternalCode.ClientExternalCodeAddDTO;
import ir.parto.crm.modules.client.controller.transientObject.clientExternalCode.ClientExternalCodeInfoDTO;
import ir.parto.crm.modules.client.controller.validate.ClientExternalCodeValidate;
import ir.parto.crm.modules.client.controller.validate.ClientValidate;
import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.entity.ClientExternalCode;
import ir.parto.crm.modules.client.model.service.ClientExternalCodeService;
import ir.parto.crm.modules.client.model.service.ClientService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/client/clientExternalCode")
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
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("client_show", "Client_ExternalCode")) {
            return new ApiResponse("Error", 101, Arrays.asList("ClientExternalCode - client_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.clientExternalCodeValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ClientExternalCode> clientList = this.clientExternalCodeService.findAllItem(
                    PageableRequest.getInstance().createPageRequest(page, "Client", sortProperty, sortOrder));
            List<ClientExternalCodeInfoDTO> clientExternalCodeInfoDTOList = new ArrayList<>();
            for (ClientExternalCode clientExternalCode : clientList) {
                clientExternalCodeInfoDTOList.add(clientExternalCode.convert2Object());
            }
            return new ApiResponse("Success", clientExternalCodeInfoDTOList)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("client_show", "Client_ExternalCode")) {
            return new ApiResponse("Error", 101, Arrays.asList("ClientExternalCode - client_show - access denied!"))
                    .getFaultResponse();
        }

        ClientExternalCode clientExternalCode = new ClientExternalCode();
        clientExternalCode.setClientExternalCodeId(id);
        ValidateObject validateObject = this.clientExternalCodeValidate.findOne(clientExternalCode);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.clientExternalCodeService.findOne(clientExternalCode).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ClientExternalCodeAddDTO clientExternalCodeAddDTO) {
        ClientExternalCode clientExternalCode = clientExternalCodeAddDTO.convert2Object();
        if (CheckPermission.getInstance().check("client_add", "Client_ExternalCode")) {
            clientExternalCode.setClient(this.clientService.findOne(clientExternalCode.getClient()));
            return new ApiResponse("Error", 101, Arrays.asList("ClientExternalCode - client_add - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.clientExternalCodeValidate.validateAddNewItem(clientExternalCode);
        if (validateObject.getResult().equals("success")) {
            if(clientExternalCode.getClient().getClientId() != null)
                clientExternalCode.setClient(this.clientService.findById(clientExternalCode.getClient().getClientId()));
            return new ApiResponse("Success", Arrays.asList(this.clientExternalCodeService.addNewItem(clientExternalCode).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("client_delete", "Client_ExternalCode")) {
            return new ApiResponse("Error", 101, Arrays.asList("ClientExternalCode - client_delete - access denied!"))
                    .getFaultResponse();
        }

        ClientExternalCode clientExternalCode = new ClientExternalCode();
        clientExternalCode.setClientExternalCodeId(id);
        ValidateObject validateObject = this.clientExternalCodeValidate.deleteItem(clientExternalCode);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.clientExternalCodeService.deleteItem(clientExternalCode).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") String id, @RequestBody ClientExternalCodeAddDTO clientExternalCodeAddDTO) {
        if (CheckPermission.getInstance().check("client_update", "Client_ExternalCode")) {
            return new ApiResponse("Error", 101, Arrays.asList("ClientExternalCode - client_update - access denied!"))
                    .getFaultResponse();
        }

        ClientExternalCode clientExternalCode = clientExternalCodeAddDTO.convert2Object();
        clientExternalCode.setClientExternalCodeId(Long.valueOf(id));
        ValidateObject validateObject = this.clientExternalCodeValidate.validateUpdateItem(clientExternalCode);
        if (validateObject.getResult().equals("success")) {
            try {
                if(clientExternalCode.getClient().getClientId() != null)
                    clientExternalCode.setClient(this.clientService.findById(clientExternalCode.getClient().getClientId()));
                return new ApiResponse("Success", Arrays.asList(this.clientExternalCodeService.updateItem(clientExternalCode).convert2Object()))
                        .getSuccessResponse();
            } catch (InvocationTargetException e) {
                return new ApiResponse("Error", 103, Collections.singletonList("An error occurred Try again later"))
                        .getFaultResponse();
            } catch (IllegalAccessException e) {
                return new ApiResponse("Error", 104, Collections.singletonList("An error occurred Try again later"))
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }


}