package ir.parto.crm.modules.admin.controller.rest;
import ir.parto.crm.modules.admin.controller.transientObject.adminPermission.AdminPermissionAddDTO;
import ir.parto.crm.modules.admin.controller.transientObject.adminPermission.AdminPermissionDTO;
import ir.parto.crm.modules.admin.controller.validate.AdminPermissionValidate;
import ir.parto.crm.modules.admin.model.entity.AdminPermission;
import ir.parto.crm.modules.admin.model.service.AdminPermissionService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageHelper;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.AdminAnnotation;
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
@AdminAnnotation
@CrossOrigin
@RequestMapping(value = "/v1/admin/adminPermission", produces = "application/json")
public class AdminPermissionRestController implements RestControllerInterface {
    private AdminPermissionService adminPermissionService;
    private AdminPermissionValidate adminPermissionValidate;

    @Autowired
    public AdminPermissionRestController(AdminPermissionService adminPermissionService,
                                         AdminPermissionValidate adminPermissionValidate) {
        this.adminPermissionService = adminPermissionService;
        this.adminPermissionValidate = adminPermissionValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "AdminPermission")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminPermission - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.adminPermissionValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<AdminPermission> findPage = this.adminPermissionService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "Admin", sortProperty, sortOrder));
            List<AdminPermissionDTO> returnDTO = new ArrayList<>();
            for (AdminPermission content : findPage.getContent()) {
                returnDTO.add(content.convert2Object());
            }
            return new ApiResponse("Success", PageHelper.getInstance().createResponse(findPage, returnDTO))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody AdminPermissionAddDTO adminPermissionAddDTO) {
        if (CheckPermission.getInstance().check("admin_add", "AdminPermission")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminPermission - admin_add - access denied!"))
                    .getFaultResponse();
        }
        AdminPermission adminPermission = adminPermissionAddDTO.convert2Object();
        ValidateObject validateObject = this.adminPermissionValidate.validateAddNewItem(adminPermission);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminPermissionService.addNewItem(adminPermission)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody AdminPermissionAddDTO adminPermissionAddDTO) {
        if (CheckPermission.getInstance().check("admin_update", "AdminPermission")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminPermission - admin_update - access denied!"))
                    .getFaultResponse();
        }

        AdminPermission adminPermission = adminPermissionAddDTO.convert2Object();
        adminPermission.setPermissionId(id);
        ValidateObject validateObject = this.adminPermissionValidate.validateUpdateItem(adminPermission);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.adminPermissionService.updateItem(adminPermission)))
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
        if (CheckPermission.getInstance().check("admin_delete", "AdminPermission")) {
            return new ApiResponse("Error", 101, Arrays.asList("Product - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        AdminPermission adminPermission = new AdminPermission();
        adminPermission.setPermissionId(id);
        ValidateObject validateObject = this.adminPermissionValidate.deleteItem(adminPermission);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminPermissionService.deleteItem(adminPermission)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "AdminPermission")) {
            return new ApiResponse("Error", 101, Arrays.asList("Product - admin_show - access denied!"))
                    .getFaultResponse();
        }

        AdminPermission adminPermission = new AdminPermission();
        adminPermission.setPermissionId(id);
        ValidateObject validateObject = this.adminPermissionValidate.findOne(adminPermission);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminPermissionService.findOne(adminPermission)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}
