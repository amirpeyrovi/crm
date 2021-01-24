package ir.parto.crm.modules.admin.controller.rest;

import ir.parto.crm.modules.admin.controller.transientObject.adminRolePermission.AdminRolePermissionAddDTO;
import ir.parto.crm.modules.admin.controller.validate.AdminRolePermissionValidate;
import ir.parto.crm.modules.admin.model.entity.AdminRolePermission;
import ir.parto.crm.modules.admin.model.service.AdminPermissionService;
import ir.parto.crm.modules.admin.model.service.AdminRolePermissionService;
import ir.parto.crm.modules.admin.model.service.AdminRoleService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.AdminAnnotation;
import ir.parto.crm.utils.interfaces.RestControllerInterface;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@AdminAnnotation
@CrossOrigin
@RequestMapping(value = "/v1/admin/adminRolePermission", produces = "application/json")
public class AdminRolePermissionRestController implements RestControllerInterface {
    private AdminRoleService adminRoleService;
    private AdminPermissionService adminPermissionService;
    private AdminRolePermissionService adminRolePermissionService;
    private AdminRolePermissionValidate adminRolePermissionValidate;

    @Autowired
    public AdminRolePermissionRestController(AdminRoleService adminRoleService, AdminPermissionService adminPermissionService, AdminRolePermissionService adminRolePermissionService, AdminRolePermissionValidate adminRolePermissionValidate) {
        this.adminRoleService = adminRoleService;
        this.adminPermissionService = adminPermissionService;
        this.adminRolePermissionService = adminRolePermissionService;
        this.adminRolePermissionValidate = adminRolePermissionValidate;
    }


    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "AdminRolePermission")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminRolePermission - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.adminRolePermissionValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<AdminRolePermission> adminRolePermissions = this.adminRolePermissionService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "Product", sortProperty, sortOrder));
            return new ApiResponse("Success", adminRolePermissions)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody AdminRolePermissionAddDTO adminRolePermissionAddDTO) {
        if (CheckPermission.getInstance().check("admin_add", "AdminRolePermission")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminRolePermission - admin_add - access denied!"))
                    .getFaultResponse();
        }
        AdminRolePermission adminRolePermission = adminRolePermissionAddDTO.convert2Object();
        ValidateObject validateObject = this.adminRolePermissionValidate.validateAddNewItem(adminRolePermission);
        if (validateObject.getResult().equals("success")) {
            adminRolePermission.setAdminPermission(this.adminPermissionService.findOne(adminRolePermission.getAdminPermission()));
            adminRolePermission.setAdminRole(this.adminRoleService.findOne(adminRolePermission.getAdminRole()));
            return new ApiResponse("Success", Arrays.asList(this.adminRolePermissionService.addNewItem(adminRolePermission)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody AdminRolePermissionAddDTO adminRolePermissionAddDTO) {
        if (CheckPermission.getInstance().check("admin_update", "AdminRolePermission")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminRolePermission - admin_update - access denied!"))
                    .getFaultResponse();
        }
        AdminRolePermission adminRolePermission = adminRolePermissionAddDTO.convert2Object();
        adminRolePermission.setAdminRolePermissionId(id);

        ValidateObject validateObject = this.adminRolePermissionValidate.validateUpdateItem(adminRolePermission);
        if (validateObject.getResult().equals("success")) {
            try {
                adminRolePermission.setAdminPermission(this.adminPermissionService.findOne(adminRolePermission.getAdminPermission()));
                adminRolePermission.setAdminRole(this.adminRoleService.findOne(adminRolePermission.getAdminRole()));
                return new ApiResponse("Success", Arrays.asList(this.adminRolePermissionService.updateItem(adminRolePermission)))
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
        if (CheckPermission.getInstance().check("admin_delete", "AdminRolePermission")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminRolePermission - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        AdminRolePermission adminRolePermission = new AdminRolePermission();
        adminRolePermission.setAdminRolePermissionId(id);
        ValidateObject validateObject = this.adminRolePermissionValidate.deleteItem(adminRolePermission);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminRolePermissionService.deleteItem(adminRolePermission)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "AdminRolePermission")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminRolePermission - admin_show - access denied!"))
                    .getFaultResponse();
        }

        AdminRolePermission adminRolePermission = new AdminRolePermission();
        adminRolePermission.setAdminRolePermissionId(id);
        ValidateObject validateObject = this.adminRolePermissionValidate.findOne(adminRolePermission);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminRolePermissionService.findOne(adminRolePermission)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }
}