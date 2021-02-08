package ir.parto.crm.modules.server.controller.rest;

import ir.parto.crm.modules.server.controller.transientObject.serverParameter.ServerParameterAddDTO;
import ir.parto.crm.modules.server.controller.transientObject.serverParameter.ServerParameterDTO;
import ir.parto.crm.modules.server.controller.transientObject.serverParameter.ServerParameterEditDTO;
import ir.parto.crm.modules.server.controller.validate.ServerGroupValidate;
import ir.parto.crm.modules.server.controller.validate.ServerParameterValidate;
import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.server.model.entity.ServerParameter;
import ir.parto.crm.modules.server.model.service.ServerGroupService;
import ir.parto.crm.modules.server.model.service.ServerParameterService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageHelper;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.ServerAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.Convert2Object;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

@RestController
@ServerAnnotation
@RequestMapping("/v1/server/serverParameter")
public class ServerParameterRestController implements RestControllerInterface {
    private ServerParameterValidate serverParameterValidate;
    private ServerGroupValidate serverGroupValidate;
    private ServerParameterService serverParameterService;
    private ServerGroupService serverGroupService;

    @Autowired
    public ServerParameterRestController(ServerParameterValidate serverParameterValidate, ServerGroupValidate serverGroupValidate, ServerParameterService serverParameterService, ServerGroupService serverGroupService) {
        this.serverParameterValidate = serverParameterValidate;
        this.serverGroupValidate = serverGroupValidate;
        this.serverParameterService = serverParameterService;
        this.serverGroupService = serverGroupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ServerParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerParameter - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.serverParameterValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ServerParameter> productPage = this.serverParameterService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ServerParameter", sortProperty, sortOrder));
            List<ServerParameterDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(), ServerParameterDTO.class);
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(productPage, returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/serverGroup/{id}", method = RequestMethod.GET)
    public Object findAllByServerGroup(@PathVariable("id") String id,
                                       @RequestParam(required = false, defaultValue = "0") String page,
                                       @RequestParam(required = false, defaultValue = "default") String sortProperty,
                                       @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ServerParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerParameter - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.serverParameterValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            ServerGroup serverGroup = new ServerGroup();
            serverGroup.setServerGroupId(Long.valueOf(id));
            ValidateObject validateObjectServerGroup = this.serverGroupValidate.findOne(serverGroup);
            if (validateObjectServerGroup.getResult().equals("success")) {
                ServerGroup serverGroupExist = this.serverGroupService.findOne(serverGroup);
                Page<ServerParameter> productPage = this.serverParameterService.findAllItemByServerGroup(serverGroupExist, PageableRequest.getInstance().createPageRequest(page, "ServerParameter", sortProperty, sortOrder));
                List<ServerParameterDTO> returnDTO = Convert2Object.mapAll(productPage.getContent(), ServerParameterDTO.class);
                return new ApiResponse("Success", PageHelper.getInstance().createResponse(productPage, returnDTO))
                        .getSuccessResponse();
            } else {
                return new ApiResponse("Error", 102, validateObjectServerGroup.getMessages())
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ServerParameterAddDTO serverParameterDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ServerParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerParameter - admin_add - access denied!"))
                    .getFaultResponse();
        }
        ServerParameter serverParameter = serverParameterDTO.convert2Object();
        serverParameter.setServerParameterId(null);

        ValidateObject validateObject = this.serverParameterValidate.validateAddNewItem(serverParameter);
        if (validateObject.getResult().equals("success")) {
            serverParameter.setServerGroup(this.serverGroupService.findOne(serverParameter.getServerGroup()));
            return new ApiResponse("Success", Arrays.asList(this.serverParameterService.addNewItem(serverParameter).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") String id, @RequestBody ServerParameterEditDTO serverParameterDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ServerParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerParameter - admin_update - access denied!"))
                    .getFaultResponse();
        }
        ServerParameter serverParameter = serverParameterDTO.convert2Object();
        serverParameter.setServerParameterId(Long.valueOf(id));
        if (serverParameter.getServerGroup() != null)
            serverParameter.setServerGroup(this.serverGroupService.findOne(serverParameter.getServerGroup()));
        ValidateObject validateObject = this.serverParameterValidate.validateUpdateItem(serverParameter);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.serverParameterService.updateItem(serverParameter).convert2Object()))
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
    public Object deleteOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_delete", "ServerParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerParameter - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ServerParameter serverParameter = new ServerParameter();
        serverParameter.setServerParameterId(Long.valueOf(id));
        ValidateObject validateObject = this.serverParameterValidate.deleteItem(serverParameter);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.serverParameterService.deleteItem(serverParameter).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") String id) {
        if (CheckPermission.getInstance().check("admin_show", "ServerGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ServerParameter serverParameter = new ServerParameter();
        serverParameter.setServerParameterId(Long.valueOf(id));
        ValidateObject validateObject = this.serverParameterValidate.findOne(serverParameter);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.serverParameterService.findOne(serverParameter).convert2InfoObject()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
