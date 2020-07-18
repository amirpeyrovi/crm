package ir.parto.crm.modules.server.controller.rest;

import ir.parto.crm.modules.server.controller.validate.ServerGroupValidate;
import ir.parto.crm.modules.server.controller.validate.ServerValidate;
import ir.parto.crm.modules.server.model.entity.Server;
import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.server.model.service.ServerGroupService;
import ir.parto.crm.modules.server.model.service.ServerService;
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
@RequestMapping("/v1/server/server")
public class ServerRestController implements RestControllerInterface {
    private ServerValidate serverValidate;
    private ServerGroupValidate serverGroupValidate;
    private ServerService serverService;
    private ServerGroupService serverGroupService;

    @Autowired
    public ServerRestController(ServerValidate serverValidate, ServerGroupValidate serverGroupValidate, ServerService serverService, ServerGroupService serverGroupService) {
        this.serverValidate = serverValidate;
        this.serverGroupValidate = serverGroupValidate;
        this.serverService = serverService;
        this.serverGroupService = serverGroupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "Server")) {
            return new ApiResponse("Error", 101, Arrays.asList("Server - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.serverValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<Server> productPage = this.serverService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "Server", sortProperty, sortOrder));
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
        if (CheckPermission.getInstance().check("admin_show", "Server")) {
            return new ApiResponse("Error", 101, Arrays.asList("Server - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.serverValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            ServerGroup serverGroup = new ServerGroup();
            serverGroup.setServerGroupId(id);
            ValidateObject validateObjectServerGroup = this.serverGroupValidate.findOne(serverGroup);
            if (validateObjectServerGroup.getResult().equals("success")) {
                ServerGroup serverGroupExist = this.serverGroupService.findOne(serverGroup);
                Page<Server> productPage = this.serverService.findAllItemByServerGroup(serverGroupExist, PageableRequest.getInstance().createPageRequest(page, "Server", sortProperty, sortOrder));
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
    public Object addOne(@RequestBody Server server) {
        if (CheckPermission.getInstance().check("admin_add", "Server")) {
            return new ApiResponse("Error", 101, Arrays.asList("Server - admin_add - access denied!"))
                    .getFaultResponse();
        }

        server.setServerId(null);

        ValidateObject validateObject = this.serverValidate.validateAddNewItem(server);
        if (validateObject.getResult().equals("success")) {
            server.setServerGroup(this.serverGroupService.findOne(server.getServerGroup()));
            return new ApiResponse("Success", Arrays.asList(this.serverService.addNewItem(server)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody Server server) {
        if (CheckPermission.getInstance().check("admin_update", "Server")) {
            return new ApiResponse("Error", 101, Arrays.asList("Server - admin_update - access denied!"))
                    .getFaultResponse();
        }

        server.setServerId(id);

        ValidateObject validateObject = this.serverValidate.validateUpdateItem(server);
        if (validateObject.getResult().equals("success")) {
            try {
                server.setServerGroup(this.serverGroupService.findOne(server.getServerGroup()));
                return new ApiResponse("Success", Arrays.asList(this.serverService.updateItem(server)))
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
        if (CheckPermission.getInstance().check("admin_delete", "Server")) {
            return new ApiResponse("Error", 101, Arrays.asList("Server - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        Server server = new Server();
        server.setServerId(id);
        ValidateObject validateObject = this.serverValidate.deleteItem(server);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.serverService.deleteItem(server)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "Server")) {
            return new ApiResponse("Error", 101, Arrays.asList("Server - admin_show - access denied!"))
                    .getFaultResponse();
        }

        Server server = new Server();
        server.setServerId(id);
        ValidateObject validateObject = this.serverValidate.findOne(server);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.serverService.findOne(server)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
