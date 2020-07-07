package ir.parto.crm.modules.admin.controller.rest;

import ir.parto.crm.modules.admin.controller.validate.AdminRoleValidate;
import ir.parto.crm.modules.admin.controller.validate.AdminValidate;
import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.modules.admin.model.service.AdminRoleService;
import ir.parto.crm.modules.admin.model.service.AdminService;
import ir.parto.crm.utils.CheckPermission;
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
@RequestMapping("/v1/admin")
public class AdminRestController {
    private CheckPermission checkPermission;
    private AdminService adminService;
    private AdminValidate adminValidate;
    private AdminRoleService adminRoleService;
    private AdminRoleValidate adminRoleValidate;

    @Autowired
    public AdminRestController(CheckPermission checkPermission, AdminService adminService, AdminValidate adminValidate, AdminRoleService adminRoleService, AdminRoleValidate adminRoleValidate) {
        this.checkPermission = checkPermission;
        this.adminService = adminService;
        this.adminValidate = adminValidate;
        this.adminRoleService = adminRoleService;
        this.adminRoleValidate = adminRoleValidate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object findAllAdmin(Pageable pageable){
        if(!checkPermission.check("Admin","admin_show"))
            return new ApiResponse("error", 102, Arrays.asList("Access denied!")).getFaultResponse();

        ValidateObject validateObject = this.adminValidate.findAll();
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        Pageable pageable0 = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        Page<Admin> adminList = this.adminService.findAllItem(pageable0);
        return new ApiResponse("success",adminList).getSuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addAdmin(@RequestBody Admin admin){
        if(admin.getAdminRole() != null && admin.getAdminRole().getAdminRoleId() != null && admin.getAdminRole().getAdminRoleId() != 0){
            AdminRole adminRole = this.adminRoleService.findById(admin.getAdminRole().getAdminRoleId());
            ValidateObject validateObjectAdminRole = this.adminRoleValidate.findOne(adminRole);
            if(validateObjectAdminRole.getResult().equals("error")){
                return new ApiResponse("error",101,validateObjectAdminRole.getMessages()).getFaultResponse();
            }
            admin.setAdminRole(adminRole);
        }
        ValidateObject validateObject = this.adminValidate.validateAddNewItem(admin);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }

        admin = this.adminService.addNewItem(admin);
        return new ApiResponse("success", Arrays.asList(admin)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Object updateAdmin(@PathVariable("id") long id, @RequestBody Admin admin){
        admin.setAdminId(id);
        if(admin.getAdminRole() != null && admin.getAdminRole().getAdminRoleId() != null && admin.getAdminRole().getAdminRoleId() != 0){
            AdminRole adminRole = this.adminRoleService.findById(admin.getAdminRole().getAdminRoleId());
            ValidateObject validateObjectAdminRole = this.adminRoleValidate.findOne(adminRole);
            if(validateObjectAdminRole.getResult().equals("error")){
                return new ApiResponse("error",101,validateObjectAdminRole.getMessages()).getFaultResponse();
            }
            admin.setAdminRole(adminRole);
        }

        ValidateObject validateObject = this.adminValidate.validateUpdateItem(admin);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }

        try {
            admin = this.adminService.updateItem(admin);
            return new ApiResponse("success", Arrays.asList(admin)).getSuccessResponse();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return new ApiResponse("error", 102,Arrays.asList("An error occurrd during update")).getFaultResponse();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return new ApiResponse("error", 102,Arrays.asList("An error occurrd during update")).getFaultResponse();
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Object deleteAdmin(@PathVariable("id") long id){
        Admin admin = this.adminService.findById(id);
        ValidateObject validateObject = this.adminValidate.deleteItem(admin);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        this.adminService.deleteItem(admin);
        return new ApiResponse("success", Arrays.asList(admin)).getSuccessResponse();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object findoneAdmin(@PathVariable("id") long id){
        Admin admin = this.adminService.findById(id);
        ValidateObject validateObject = this.adminValidate.findOne(admin);
        if(validateObject.getResult().equals("error")){
            return new ApiResponse("error",101,validateObject.getMessages()).getFaultResponse();
        }
        return new ApiResponse("success", Arrays.asList(admin)).getSuccessResponse();
    }
}
