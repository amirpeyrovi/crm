package ir.parto.crm.modules.server.controller.rest;


import ir.parto.crm.modules.server.controller.validate.ServerGroupValidate;
import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.server.model.entity.ServerVendor;
import ir.parto.crm.modules.server.model.service.ServerGroupService;
import ir.parto.crm.modules.server.model.service.ServerVendorService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.ServerAnnotation;
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

@RestController
@ServerAnnotation
@RequestMapping("/v1/server/serverGroup")
public class ServerGroupRestController implements RestControllerInterface {
    private ServerGroupValidate serverGroupValidate;
    private ServerGroupService serverGroupService;
    private ServerVendorService serverVendorService;

    public ServerGroupRestController(ServerGroupValidate serverGroupValidate, ServerGroupService serverGroupService, ServerVendorService serverVendorService) {
        this.serverGroupValidate = serverGroupValidate;
        this.serverGroupService = serverGroupService;
        this.serverVendorService = serverVendorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ServerGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerGroup - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.serverGroupValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ServerGroup> productPage = this.serverGroupService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "ServerGroup", sortProperty, sortOrder));
            return new ApiResponse("Success", productPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ServerGroup serverGroup) {
        if (CheckPermission.getInstance().check("admin_add", "ServerGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerGroup - admin_add - access denied!"))
                    .getFaultResponse();
        }

        serverGroup.setServerGroupId(null);

        ValidateObject validateObject = this.serverGroupValidate.validateAddNewItem(serverGroup);
        if (validateObject.getResult().equals("success")) {
            serverGroup.setServerVendor(this.serverVendorService.findOne(serverGroup.getServerVendor()));
            return new ApiResponse("Success", Arrays.asList(this.serverGroupService.addNewItem(serverGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ServerGroup serverGroup) {
        if (CheckPermission.getInstance().check("admin_update", "ServerGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerGroup - admin_update - access denied!"))
                    .getFaultResponse();
        }

        serverGroup.setServerGroupId(id);

        ValidateObject validateObject = this.serverGroupValidate.validateUpdateItem(serverGroup);
        if (validateObject.getResult().equals("success")) {
            try {
                serverGroup.setServerVendor(this.serverVendorService.findOne(serverGroup.getServerVendor()));
                return new ApiResponse("Success", Arrays.asList(this.serverGroupService.updateItem(serverGroup)))
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
        if (CheckPermission.getInstance().check("admin_delete", "ServerGroup")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerGroup - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ServerGroup serverGroup = new ServerGroup();
        serverGroup.setServerGroupId(id);
        ValidateObject validateObject = this.serverGroupValidate.deleteItem(serverGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.serverGroupService.deleteItem(serverGroup)))
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

        ServerGroup serverGroup = new ServerGroup();
        serverGroup.setServerGroupId(id);
        ValidateObject validateObject = this.serverGroupValidate.findOne(serverGroup);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.serverGroupService.findOne(serverGroup)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
