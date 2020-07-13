package ir.parto.crm.modules.admin.controller.rest;

import ir.parto.crm.modules.admin.controller.validate.AdminRoleValidate;
import ir.parto.crm.modules.admin.controller.validate.AdminValidate;
import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.modules.admin.model.service.AdminRoleService;
import ir.parto.crm.modules.admin.model.service.AdminService;
import ir.parto.crm.utils.CheckPermission;
import ir.parto.crm.utils.PageableRequest;
import ir.parto.crm.utils.annotations.AdminAnnotation;
import ir.parto.crm.utils.annotations.AdminAnnotation;
import ir.parto.crm.utils.transientObject.ApiResponse;
import ir.parto.crm.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RestController
@AdminAnnotation
@RequestMapping("/v1/admin/admin")
public class AdminRestController {
    private AdminService adminService;
    private AdminValidate adminValidate;
    private AdminRoleService adminRoleService;
    private AdminRoleValidate adminRoleValidate;

    @Autowired
    public AdminRestController(AdminService adminService, AdminValidate adminValidate, AdminRoleService adminRoleService, AdminRoleValidate adminRoleValidate) {
        this.adminService = adminService;
        this.adminValidate = adminValidate;
        this.adminRoleService = adminRoleService;
        this.adminRoleValidate = adminRoleValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(@RequestParam(required = false, defaultValue = "0") String page,
                          @RequestParam(required = false, defaultValue = "default") String sortProperty,
                          @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (CheckPermission.getInstance().check("admin_show", "Admin")) {
            return new ApiResponse("Error", 101, Arrays.asList("Admin - admin_show - access denied!"))
                    .getFaultResponse();
        }

        ValidateObject validateObject = this.adminValidate.findAll();
        if (validateObject.getResult().equals("success")) {
            Page<Admin> adminPage = this.adminService.findAllItem(PageableRequest.getInstance().createPageRequest(page, "Admin", sortProperty, sortOrder));
            return new ApiResponse("Success", adminPage)
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addOne(@RequestBody Admin admin) {
        if (CheckPermission.getInstance().check("admin_add", "Admin")) {
            return new ApiResponse("Error", 101, Arrays.asList("Admin - admin_add - access denied!"))
                    .getFaultResponse();
        }
        admin.setAdminId(null);

        ValidateObject validateObject = this.adminValidate.validateAddNewItem(admin);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminService.addNewItem(admin)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateOne(@PathVariable("id") Long id, @RequestBody Admin admin) {
        if (CheckPermission.getInstance().check("admin_update", "Admin")) {
            return new ApiResponse("Error", 101, Arrays.asList("Admin - admin_update - access denied!"))
                    .getFaultResponse();
        }

        admin.setAdminId(id);

        ValidateObject validateObject = this.adminValidate.validateUpdateItem(admin);
        if (validateObject.getResult().equals("success")) {
            try {
                return new ApiResponse("Success", Arrays.asList(this.adminService.updateItem(admin)))
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
        if (CheckPermission.getInstance().check("admin_delete", "Admin")) {
            return new ApiResponse("Error", 101, Arrays.asList("Admin - admin_delete - access denied!"))
                    .getFaultResponse();
        }

        Admin admin = new Admin();
        admin.setAdminId(id);
        ValidateObject validateObject = this.adminValidate.deleteItem(admin);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminService.deleteItem(admin)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") Long id) {
        if (CheckPermission.getInstance().check("admin_show", "Admin")) {
            return new ApiResponse("Error", 101, Arrays.asList("Admin - admin_show - access denied!"))
                    .getFaultResponse();
        }

        Admin admin = new Admin();
        admin.setAdminId(id);
        ValidateObject validateObject = this.adminValidate.findOne(admin);
        if (validateObject.getResult().equals("success")) {
            return new ApiResponse("Success", Arrays.asList(this.adminService.findOne(admin)))
                    .getSuccessResponse();
        } else {
            return new ApiResponse("Error", 102, validateObject.getMessages())
                    .getFaultResponse();
        }
    }

}
