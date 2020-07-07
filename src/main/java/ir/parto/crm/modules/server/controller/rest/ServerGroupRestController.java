package ir.parto.crm.modules.server.controller.rest;


import ir.parto.crm.modules.server.controller.validate.ServerGroupValidate;
import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.server.model.service.ServerGroupService;
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
@RequestMapping("/v1/serverGroup")
public class ServerGroupRestController implements RestControllerInterface {
    private ServerGroupService serverGroupService;
    private ServerGroupValidate serverGroupValidate;

    @Autowired
    public ServerGroupRestController(ServerGroupService serverGroupService, ServerGroupValidate serverGroupValidate) {
        this.serverGroupService = serverGroupService;
        this.serverGroupValidate = serverGroupValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAllServerGroup(Pageable pageable){
        ValidateObject validateObject = this.serverGroupValidate.findAll();
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Pageable pageable0 = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        Page<ServerGroup> serverGroupList = this.serverGroupService.findAllItem(pageable0);
        return new ApiResponse("success",serverGroupList).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object findOneServerGroup(@PathVariable("id") long id){
        ServerGroup serverGroup = this.serverGroupService.findById(id);
        ValidateObject validateObject = this.serverGroupValidate.findOne(serverGroup);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        return new ApiResponse("success", new ArrayList(Arrays.asList(serverGroup))).getSuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addServerGroup(@RequestBody ServerGroup serverGroup){
        ValidateObject validateObject = this.serverGroupValidate.validateAddNewItem(serverGroup);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        ServerGroup serverGroupAdded = this.serverGroupService.addNewItem(serverGroup);
        return new ApiResponse("success", new ArrayList(Arrays.asList(serverGroupAdded))).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Object deleteServerGroup(@PathVariable("id") long id){
        ServerGroup serverGroup = this.serverGroupService.findById(id);
        ValidateObject validateObject = this.serverGroupValidate.findOne(serverGroup);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        ServerGroup deleteServeGroup = this.serverGroupService.deleteItem(serverGroup);
        return new ApiResponse("success", Arrays.asList(deleteServeGroup)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Object updateServerGroup(@PathVariable("id") long id,@RequestBody ServerGroup serverGroup){
        ServerGroup exist = this.serverGroupService.findById(id);
        ValidateObject serverGroupValidateObject = this.serverGroupValidate.findOne(exist);
        if(serverGroupValidateObject.getResult().equals("error")){
            return new ApiResponse("error",101,serverGroupValidateObject.getMessages()).getFaultResponse();
        }
        serverGroup.setServerGroupId(id);
        ValidateObject validateObject = this.serverGroupValidate.validateUpdateItem(serverGroup);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        try {
            ServerGroup updatedServerGroup = this.serverGroupService.updateItem(serverGroup);
            return new ApiResponse("success", new ArrayList(Arrays.asList(updatedServerGroup))).getSuccessResponse();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return new ApiResponse("error", 102,new ArrayList(Arrays.asList("An error occurrd during update"))).getFaultResponse();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return new ApiResponse("error", 102,new ArrayList(Arrays.asList("An error occurrd during update"))).getFaultResponse();
        }
    }
}
