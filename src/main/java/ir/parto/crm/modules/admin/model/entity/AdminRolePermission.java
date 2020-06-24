package ir.parto.crm.modules.admin.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "crm_admin_role_permission")
public class AdminRolePermission implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "admin_seq", sequenceName = "admin_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "admin_seq")
    private Long adminRolePermissionId;

    @Column(name = "permission_title", columnDefinition = "nvarchar2(100)", unique = true)
    private String title;

    @Column(name = "permission_add", columnDefinition = "number(1)")
    private String addPerm;

    @Column(name = "permission_update", columnDefinition = "number(1)")
    private String updatePerm;

    @Column(name = "permission_delete", columnDefinition = "number(1)")
    private String deletePerm;

    @Column(name = "permission_view", columnDefinition = "number(1)")
    private String viewPerm;

    @ManyToOne
    @JoinColumn(name = "admin_role_id", foreignKey = @ForeignKey(name = "permission_role_fk"))
    private AdminRole adminRole;

    public AdminRolePermission() {
    }

    public AdminRolePermission(String title, String addPerm, String updatePerm, String deletePerm, String viewPerm, AdminRole adminRole) {
        this.title = title;
        this.addPerm = addPerm;
        this.updatePerm = updatePerm;
        this.deletePerm = deletePerm;
        this.viewPerm = viewPerm;
        this.adminRole = adminRole;
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

    public String getAddPerm() {
        return addPerm;
    }

    public void setAddPerm(String addPerm) {
        this.addPerm = addPerm;
    }

    public String getUpdatePerm() {
        return updatePerm;
    }

    public void setUpdatePerm(String updatePerm) {
        this.updatePerm = updatePerm;
    }

    public String getDeletePerm() {
        return deletePerm;
    }

    public void setDeletePerm(String deletePerm) {
        this.deletePerm = deletePerm;
    }

    public String getViewPerm() {
        return viewPerm;
    }

    public void setViewPerm(String viewPerm) {
        this.viewPerm = viewPerm;
    }

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole) {
        this.adminRole = adminRole;
    }
}
