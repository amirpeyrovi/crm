package ir.parto.crm.modules.admin.controller.transientObject.adminRolePermission;

import ir.parto.crm.modules.admin.controller.transientObject.adminPermission.AdminPermissionDTO;
import ir.parto.crm.modules.admin.controller.transientObject.adminRole.AdminRoleDTO;

import java.io.Serializable;

public class AdminRolePermissionDTO implements Serializable {
    private Long adminRolePermissionId;
    private String title;
    private int admin_addPerm;
    private int admin_updatePerm;
    private int admin_deletePerm;
    private int admin_viewPerm;
    private AdminRoleDTO adminRole;
    private AdminPermissionDTO adminPermission;

    public AdminRolePermissionDTO() {
    }

    public AdminRolePermissionDTO(Long adminRolePermissionId, String title, int admin_addPerm, int admin_updatePerm, int admin_deletePerm, int admin_viewPerm, AdminRoleDTO adminRole, AdminPermissionDTO adminPermission) {
        this.adminRolePermissionId = adminRolePermissionId;
        this.title = title;
        this.admin_addPerm = admin_addPerm;
        this.admin_updatePerm = admin_updatePerm;
        this.admin_deletePerm = admin_deletePerm;
        this.admin_viewPerm = admin_viewPerm;
        this.adminRole = adminRole;
        this.adminPermission = adminPermission;
    }

    public Long getAdminRolePermissionId() {
        return adminRolePermissionId;
    }

    public void setAdminRolePermissionId(Long adminRolePermissionId) {
        this.adminRolePermissionId = adminRolePermissionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAdmin_addPerm() {
        return admin_addPerm;
    }

    public void setAdmin_addPerm(int admin_addPerm) {
        this.admin_addPerm = admin_addPerm;
    }

    public int getAdmin_updatePerm() {
        return admin_updatePerm;
    }

    public void setAdmin_updatePerm(int admin_updatePerm) {
        this.admin_updatePerm = admin_updatePerm;
    }

    public int getAdmin_deletePerm() {
        return admin_deletePerm;
    }

    public void setAdmin_deletePerm(int admin_deletePerm) {
        this.admin_deletePerm = admin_deletePerm;
    }

    public int getAdmin_viewPerm() {
        return admin_viewPerm;
    }

    public void setAdmin_viewPerm(int admin_viewPerm) {
        this.admin_viewPerm = admin_viewPerm;
    }

    public AdminRoleDTO getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRoleDTO adminRole) {
        this.adminRole = adminRole;
    }

    public AdminPermissionDTO getAdminPermission() {
        return adminPermission;
    }

    public void setAdminPermission(AdminPermissionDTO adminPermission) {
        this.adminPermission = adminPermission;
    }
}