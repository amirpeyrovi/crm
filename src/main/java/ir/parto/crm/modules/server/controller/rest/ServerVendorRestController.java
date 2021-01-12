package ir.parto.crm.modules.server.controller.rest;

import ir.parto.crm.modules.server.controller.transientObject.serverVendor.ServerVendorAddDTO;
import ir.parto.crm.modules.server.controller.transientObject.serverVendor.ServerVendorDTO;
import ir.parto.crm.modules.server.controller.validate.ServerVendorValidate;
import ir.parto.crm.modules.server.model.entity.ServerVendor;
import ir.parto.crm.modules.server.model.service.ServerVendorService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageHelper;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.ServerAnnotation;
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
@ServerAnnotation
@RequestMapping("/v1/server/serverVendor")
public class ServerVendorRestController implements RestControllerInterface {
    private ServerVendorValidate serverVendorValidate;
    private ServerVendorService serverVendorService;

    @Autowired
    public ServerVendorRestController(ServerVendorValidate serverVendorValidate, ServerVendorService serverVendorService) {
        this.serverVendorValidate = serverVendorValidate;
        this.serverVendorService = serverVendorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "ServerVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerVendor - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.serverVendorValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<ServerVendor> productPage = this.serverVendorService.findAllItem(
                    PageableRequest.getInstance().createPageRequest(page, "ServerVendor", sortProperty, sortOrder));

            List<ServerVendorDTO> returnDTO = new ArrayList<>();
            for (ServerVendor serverVendor : productPage) {
                returnDTO.add(serverVendor.convert2Object());
            }
            return new ApiResponse("Success",  PageHelper.getInstance().createResponse(productPage, returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody ServerVendorAddDTO serverVendorAddDTO) {
        if (CheckPermission.getInstance().check("admin_add", "ServerVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerVendor - admin_add - access denied!"))
                    .getFaultResponse();
        }

        ServerVendor serverVendor = serverVendorAddDTO.convert2Object();
        serverVendor.setServerVendorId(null);

        ValidateObject validateObject = this.serverVendorValidate.validateAddNewItem(serverVendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.serverVendorService.addNewItem(serverVendor).convert2Object()))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody ServerVendorAddDTO serverVendorAddDTO) {
        if (CheckPermission.getInstance().check("admin_update", "ServerVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerVendor - admin_update - access denied!"))
                    .getFaultResponse();
        }


        ServerVendor serverVendor = serverVendorAddDTO.convert2Object();
        serverVendor.setServerVendorId(id);

        ValidateObject validateObject = this.serverVendorValidate.validateUpdateItem(serverVendor);
        if (validateObject.getResult().equals("success")) {
            try {
                serverVendor = this.serverVendorService.updateItem(serverVendor);
                return new ApiResponse("Success", Arrays.asList(serverVendor.convert2Object())).getSuccessResponse();
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
        if (CheckPermission.getInstance().check("admin_delete", "ServerVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerVendor - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        ServerVendor serverVendor = new ServerVendor();
        serverVendor.setServerVendorId(id);
        ValidateObject validateObject = this.serverVendorValidate.deleteItem(serverVendor);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.serverVendorService.deleteItem(serverVendor).convert2Object()))
                        .getSuccessResponse();
            } catch (Exception e) {
                if (e.getMessage().contains("constraint")) {
                    return new ApiResponse("Error", 103, new ArrayList(Arrays.asList("" +
                            "Integrity constraint violated - child record"))).getFaultResponse();
                } else {
                    return new ApiResponse("Error", 103, new ArrayList(Arrays.asList("An error occurred during the Delete")))
                            .getFaultResponse();
                }
            }

        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "ServerVendor")) {
            return new ApiResponse("Error", 101, Arrays.asList("ServerVendor - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ServerVendor serverVendor = new ServerVendor();
        serverVendor.setServerVendorId(id);
        ValidateObject validateObject = this.serverVendorValidate.findOne(serverVendor);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.serverVendorService.findOne(serverVendor)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
