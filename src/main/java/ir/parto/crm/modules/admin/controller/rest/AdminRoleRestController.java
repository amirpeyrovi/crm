package ir.parto.crm.modules.admin.controller.rest;

import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.modules.admin.controller.validate.AdminRolePermissionValidate;
import ir.parto.crm.modules.admin.controller.validate.AdminRoleValidate;
import ir.parto.crm.modules.admin.model.service.AdminRolePermissionService;
import ir.parto.crm.modules.admin.model.service.AdminRoleService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@RequestMapping("/v1/adminRole/adminRolerole")
public class AdminRoleRestController {
    private AdminRoleService adminRoleService;
    private AdminRoleValidate adminRoleValidate;
    private CheckPermission checkPermission;
    private AdminRolePermissionService adminRolePermissionService;
    private AdminRolePermissionValidate adminRolePermissionValidate;

    @Autowired
    public AdminRoleRestController(AdminRoleService adminRoleService, AdminRoleValidate adminRoleValidate, CheckPermission checkPermission, AdminRolePermissionService adminRolePermissionService, AdminRolePermissionValidate adminRolePermissionValidate) {
        this.adminRoleService = adminRoleService;
        this.adminRoleValidate = adminRoleValidate;
        this.checkPermission = checkPermission;
        this.adminRolePermissionService = adminRolePermissionService;
        this.adminRolePermissionValidate = adminRolePermissionValidate;
    }


    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("adminRole_show", "AdminRole")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminRole - adminRole_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.adminRoleValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<AdminRole> adminRolePage = this.adminRoleService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "AdminRole", sortProperty, sortOrder));
            return new ApiResponse("Success", adminRolePage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody AdminRole adminRole) {
        if (CheckPermission.getInstance().check("adminRole_add", "AdminRole")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminRole - adminRole_add - access denied!"))
                    .getFaultResponse();
        }
        adminRole.setAdminRoleId(null);
        ValidateObject validateObject = this.adminRoleValidate.validateAddNewItem(adminRole);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminRoleService.addNewItem(adminRole)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody AdminRole adminRole) {
        if (CheckPermission.getInstance().check("adminRole_update", "AdminRole")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminRole - adminRole_update - access denied!"))
                    .getFaultResponse();
        }
        adminRole.setAdminRoleId(id);

        ValidateObject validateObject = this.adminRoleValidate.validateUpdateItem(adminRole);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.adminRoleService.updateItem(adminRole)))
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
        if (CheckPermission.getInstance().check("adminRole_delete", "AdminRole")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminRole - adminRole_delete - access denied!"))
                    .getFaultResponse();
        }

        AdminRole adminRole = new AdminRole();
        adminRole.setAdminRoleId(id);
        ValidateObject validateObject = this.adminRoleValidate.deleteItem(adminRole);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminRoleService.deleteItem(adminRole)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("adminRole_show", "AdminRole")) {
            return new ApiResponse("Error", 101, Arrays.asList("AdminRole - adminRole_show - access denied!"))
                    .getFaultResponse();
        }

        AdminRole adminRole = new AdminRole();
        adminRole.setAdminRoleId(id);
        ValidateObject validateObject = this.adminRoleValidate.findOne(adminRole);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminRoleService.findOne(adminRole)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
