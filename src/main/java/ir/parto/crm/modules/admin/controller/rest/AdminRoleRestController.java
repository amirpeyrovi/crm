package ir.parto.crm.modules.admin.controller.rest;

import ir.parto.crm.modules.admin.controller.validate.AdminPermissionValidate;
import ir.parto.crm.modules.admin.controller.validate.AdminRoleValidate;
import ir.parto.crm.modules.admin.model.service.AdminPermissionService;
import ir.parto.crm.modules.admin.model.service.AdminRoleService;
import ir.parto.crm.utils.CheckPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/adminrole")
public class AdminRoleRestController {
    private AdminRoleService adminRoleService;
    private AdminRoleValidate adminRoleValidate;
    private CheckPermission checkPermission;
    private AdminPermissionService adminPermissionService;
    private AdminPermissionValidate adminPermissionValidate;

    @Autowired
    public AdminRoleRestController(AdminRoleService adminRoleService, AdminRoleValidate adminRoleValidate, CheckPermission checkPermission, AdminPermissionService adminPermissionService, AdminPermissionValidate adminPermissionValidate) {
        this.adminRoleService = adminRoleService;
        this.adminRoleValidate = adminRoleValidate;
        this.checkPermission = checkPermission;
        this.adminPermissionService = adminPermissionService;
        this.adminPermissionValidate = adminPermissionValidate;
    }
}
