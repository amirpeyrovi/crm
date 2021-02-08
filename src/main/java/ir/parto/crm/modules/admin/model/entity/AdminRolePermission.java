package ir.parto.crm.modules.admin.model.entity;

import ir.parto.crm.modules.admin.controller.transientObject.adminRolePermission.AdminRolePermissionDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_admin_role_permission")
public class AdminRolePermission implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "admin_seq", sequenceName = "admin_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "admin_seq")
    private Long adminRolePermissionId;

    @Column(name = "permission_title", columnDefinition = "nvarchar2(100)")
    private String title;
//
//    @Column(name = "client_permission_add", columnDefinition = "number(1)")
//    private int client_addPerm;
//
//    @Column(name = "client_permission_update", columnDefinition = "number(1)")
//    private int client_updatePerm;
//
//    @Column(name = "client_permission_delete", columnDefinition = "number(1)")
//    private int client_deletePerm;
//
//    @Column(name = "client_permission_view", columnDefinition = "number(1)")
//    private int client_viewPerm;

    @Column(name = "admin_permission_add", columnDefinition = "number(1)")
    private int admin_addPerm;

    @Column(name = "admin_permission_update", columnDefinition = "number(1)")
    private int admin_updatePerm;

    @Column(name = "admin_permission_delete", columnDefinition = "number(1)")
    private int admin_deletePerm;

    @Column(name = "admin_permission_view", columnDefinition = "number(1)")
    private int admin_viewPerm;

    @ManyToOne
    @JoinColumn(name = "admin_role_id", foreignKey = @ForeignKey(name = "permission_role_fk"))
    private AdminRole adminRole;

    @ManyToOne
    @JoinColumn(name = "admin_permission_id", foreignKey = @ForeignKey(name = "permission_role_fk_permission"))
    private AdminPermission adminPermission;


    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "TIMESTAMP(6)")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_at", columnDefinition = "TIMESTAMP(6)")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private Integer isDeleted;

    public AdminRolePermission() {
    }

    public AdminRolePermission(String title, int admin_addPerm, int admin_updatePerm, int admin_deletePerm, int admin_viewPerm, AdminRole adminRole, AdminPermission adminPermission, String createdBy, String updatedBy, LocalDateTime createdDate, LocalDateTime updatedAt, String deletedBy, LocalDateTime deletedAt, Integer isDeleted) {
        this.title = title;
        this.admin_addPerm = admin_addPerm;
        this.admin_updatePerm = admin_updatePerm;
        this.admin_deletePerm = admin_deletePerm;
        this.admin_viewPerm = admin_viewPerm;
        this.adminRole = adminRole;
        this.adminPermission = adminPermission;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedBy = deletedBy;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
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

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole) {
        this.adminRole = adminRole;
    }

    public AdminPermission getAdminPermission() {
        return adminPermission;
    }

    public void setAdminPermission(AdminPermission adminPermission) {
        this.adminPermission = adminPermission;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public AdminRolePermissionDTO convert2Object() {
        AdminRolePermissionDTO dto = new AdminRolePermissionDTO();
        if(this.title != null) dto.setTitle(this.title);
        dto.setAdmin_addPerm(this.admin_addPerm);
        dto.setAdmin_updatePerm(this.admin_updatePerm);
        dto.setAdmin_deletePerm(this.admin_deletePerm);
        dto.setAdmin_viewPerm(this.admin_viewPerm);
        if(this.adminRole != null) dto.setAdminRole(this.adminRole.convert2Object());
        if(this.adminPermission != null) dto.setAdminPermission(this.adminPermission.convert2Object());
        if(this.adminRolePermissionId != null) dto.setAdminRolePermissionId(this.adminRolePermissionId);
        return dto;
    }
}