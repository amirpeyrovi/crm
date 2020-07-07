package ir.parto.crm.utils;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.modules.admin.model.entity.AdminRolePermission;
import ir.parto.crm.modules.admin.model.service.AdminPermissionService;
import ir.parto.crm.modules.admin.model.service.AdminRolePermissionService;
import ir.parto.crm.modules.admin.model.service.AdminRoleService;
import ir.parto.crm.modules.admin.model.entity.AdminPermission;
import ir.parto.crm.modules.authenticate.model.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CheckPermission {
    private AdminPermissionService adminPermissionService;
    private AdminRoleService adminRoleService;
    private AdminRolePermissionService adminRolePermissionService;

    @Autowired
    public CheckPermission(AdminPermissionService adminPermissionService, AdminRoleService adminRoleService, AdminRolePermissionService adminRolePermissionService) {
        this.adminPermissionService = adminPermissionService;
        this.adminRoleService = adminRoleService;
        this.adminRolePermissionService = adminRolePermissionService;
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
            AdminPermission adminPermission = this.adminPermissionService.findByTitle(perms);
            AdminRolePermission adminRolePermission = this.adminRolePermissionService.findByAdminRoleAndPermission(adminRole, adminPermission);

            switch (action) {
                case "client_add":
                    return adminRolePermission.getClient_addPerm().equals(1);
                case "client_update":
                    return adminRolePermission.getClient_updatePerm().equals(1);
                case "client_delete":
                    return adminRolePermission.getClient_deletePerm().equals(1);
                case "client_show":
                    return adminRolePermission.getClient_viewPerm().equals(1);
                case "admin_add":
                    return adminRolePermission.getAdmin_addPerm().equals(1);
                case "admin_update":
                    return adminRolePermission.getAdmin_updatePerm().equals(1);
                case "admin_delete":
                    return adminRolePermission.getAdmin_deletePerm().equals(1);
                case "admin_show":
                    return adminRolePermission.getAdmin_viewPerm().equals(1);
            }
            return false;
        }
    }

}
