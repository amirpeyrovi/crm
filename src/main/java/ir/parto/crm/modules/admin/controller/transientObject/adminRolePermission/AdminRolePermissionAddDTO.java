package ir.parto.crm.modules.admin.controller.transientObject.adminRolePermission;
import ir.parto.crm.modules.admin.model.entity.AdminPermission;
import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.modules.admin.model.entity.AdminRolePermission;
import java.io.Serializable;

public class AdminRolePermissionAddDTO implements Serializable {
    private String title;
    private int addPerm;
    private int updatePerm;
    private int deletePerm;
    private int viewPerm;
    private Long permissionId;

    public AdminRolePermissionAddDTO() {
    }

    public AdminRolePermissionAddDTO(String title, int addPerm, int updatePerm, int deletePerm, int viewPerm, Long permissionId) {
        this.title = title;
        this.addPerm = addPerm;
        this.updatePerm = updatePerm;
        this.deletePerm = deletePerm;
        this.viewPerm = viewPerm;
        this.permissionId = permissionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAddPerm() {
        return addPerm;
    }

    public void setAddPerm(int addPerm) {
        this.addPerm = addPerm;
    }

    public int getUpdatePerm() {
        return updatePerm;
    }

    public void setUpdatePerm(int updatePerm) {
        this.updatePerm = updatePerm;
    }

    public int getDeletePerm() {
        return deletePerm;
    }

    public void setDeletePerm(int deletePerm) {
        this.deletePerm = deletePerm;
    }

    public int getViewPerm() {
        return viewPerm;
    }

    public void setViewPerm(int viewPerm) {
        this.viewPerm = viewPerm;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public AdminRolePermission convert2Object(){
        AdminRolePermission adminRolePermission = new AdminRolePermission();
        if(this.title != null) adminRolePermission.setTitle(this.title);
        adminRolePermission.setAdmin_addPerm(this.addPerm);
        adminRolePermission.setAdmin_updatePerm(this.updatePerm);
        adminRolePermission.setAdmin_deletePerm(this.deletePerm);
        adminRolePermission.setAdmin_viewPerm(this.viewPerm);
        if(this.permissionId != null) adminRolePermission.setAdminPermission(new AdminPermission(this.permissionId));
        return adminRolePermission;
    }
}