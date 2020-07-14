package ir.parto.crm.modules.server.controller.rest;

import ir.parto.crm.modules.server.controller.validate.ServerGroupValidate;
import ir.parto.crm.modules.server.controller.validate.ServerParameterValidate;
import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.server.model.entity.ServerParameter;
import ir.parto.crm.modules.server.model.service.ServerGroupService;
import ir.parto.crm.modules.server.model.service.ServerParameterService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.ServerAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

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
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/serverGroup/{id}", method = RequestMethod.GET)
    public Object findAllByServerGroup(@PathVariable("id") Long id,
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
            serverGroup.setServerGroupId(id);
            ValidateObject validateObjectServerGroup = this.serverGroupValidate.findOne(serverGroup);
            if (validateObjectServerGroup.getResult().equals("success")) {
                ServerGroup serverGroupExist = this.serverGroupService.findOne(serverGroup);
                Page<ServerParameter> productPage = this.serverParameterService.findAllItemByServerGroup(serverGroupExist, PageableRequest.getInstance().createPageRequest(page, "ServerParameter", sortProperty, sortOrder));
                return new ApiResponse("Success", productPage)
                        .getSuccessResponse();
            }else {
                return new ApiResponse("Error", 102, validateObjectServerGroup.getMessages())
                        .getFaultResponse();
            }
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ServerParameter serverParameter) {
        if (CheckPermission.getInstance().check("admin_add", "ServerParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerParameter - admin_add - access denied!"))
                    .getFaultResponse();
        }

        serverParameter.setServerParameterId(null);

        ValidateObject validateObject = this.serverParameterValidate.validateAddNewItem(serverParameter);
        if (validateObject.getResult().equals("success")) {
            serverParameter.setServerGroup(this.serverGroupService.findOne(serverParameter.getServerGroup()));
            return new ApiResponse("Success", Arrays.asList(this.serverParameterService.addNewItem(serverParameter)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ServerParameter serverParameter) {
        if (CheckPermission.getInstance().check("admin_update", "ServerParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerParameter - admin_update - access denied!"))
                    .getFaultResponse();
        }

        serverParameter.setServerParameterId(id);

        ValidateObject validateObject = this.serverParameterValidate.validateUpdateItem(serverParameter);
        if (validateObject.getResult().equals("success")) {
            try {
                serverParameter.setServerGroup(this.serverGroupService.findOne(serverParameter.getServerGroup()));
                return new ApiResponse("Success", Arrays.asList(this.serverParameterService.updateItem(serverParameter)))
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
        if (CheckPermission.getInstance().check("admin_delete", "ServerParameter")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerParameter - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ServerParameter serverParameter = new ServerParameter();
        serverParameter.setServerParameterId(id);
        ValidateObject validateObject = this.serverParameterValidate.deleteItem(serverParameter);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.serverParameterService.deleteItem(serverParameter)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "ServerGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ServerParameter serverParameter = new ServerParameter();
        serverParameter.setServerParameterId(id);
        ValidateObject validateObject = this.serverParameterValidate.findOne(serverParameter);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.serverParameterService.findOne(serverParameter)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
