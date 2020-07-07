package ir.parto.crm.utils;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.entity.AdminPermission;
import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.modules.admin.model.service.AdminPermissionService;
import ir.parto.crm.modules.admin.model.service.AdminRoleService;
import ir.parto.crm.modules.authenticate.model.entity.Permission;
import ir.parto.crm.modules.authenticate.model.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CheckPermission {
    private AdminPermissionService adminPermissionService;
    private AdminRoleService adminRoleService;
    private PermissionService permissionService;

    @Autowired
    public CheckPermission(AdminPermissionService adminPermissionService, AdminRoleService adminRoleService, PermissionService permissionService) {
        this.adminPermissionService = adminPermissionService;
        this.adminRoleService = adminRoleService;
        this.permissionService = permissionService;
    }

    public Boolean check(String perms, String action) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return false;
        }

        Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (admin.getAdminRole().getAdminRoleId() == null || admin.getAdminRole().getAdminRoleId() == 0) {
            return false;
        } else {
            AdminRole adminRole = this.adminRoleService.findById(admin.getAdminRole().getAdminRoleId());
            Permission permission = this.permissionService.findByTitle(perms);
            AdminPermission adminPermission = this.adminPermissionService.findByAdminRoleAndPermission(adminRole, permission);

            switch (action) {
                case "client_add":
                    return adminPermission.getClientAddItem() == 1;
                case "client_update":
                    return adminPermission.getClientUpdateItem() == 1;
                case "client_delete":
                    return adminPermission.getClientDeleteItem() == 1;
                case "client_show":
                    return adminPermission.getClientShowItem() == 1;
                case "admin_add":
                    return adminPermission.getAdminAddItem() == 1;
                case "admin_update":
                    return adminPermission.getAdminUpdateItem() == 1;
                case "admin_delete":
                    return adminPermission.getAdminDeleteItem() == 1;
                case "admin_show":
                    return adminPermission.getAdminShowItem() == 1;
            }
            return false;
        }
    }

}
