package ir.parto.crm.modules.admin.model.entity;

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

    @Column(name = "permission_title", columnDefinition = "nvarchar2(100)", unique = true)
    private String title;

    @Column(name = "client_permission_add", columnDefinition = "number(1)")
    private String client_addPerm;

    @Column(name = "client_permission_update", columnDefinition = "number(1)")
    private String client_updatePerm;

    @Column(name = "client_permission_delete", columnDefinition = "number(1)")
    private String client_deletePerm;

    @Column(name = "client_permission_view", columnDefinition = "number(1)")
    private String client_viewPerm;

    @Column(name = "admin_permission_add", columnDefinition = "number(1)")
    private String admin_addPerm;

    @Column(name = "admin_permission_update", columnDefinition = "number(1)")
    private String admin_updatePerm;

    @Column(name = "client_permission_delete", columnDefinition = "number(1)")
    private String admin_deletePerm;

    @Column(name = "admin_permission_view", columnDefinition = "number(1)")
    private String admin_viewPerm;

    @ManyToOne
    @JoinColumn(name = "admin_role_id", foreignKey = @ForeignKey(name = "permission_role_fk"))
    private AdminRole adminRole;


    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_at", columnDefinition = "datetime")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "deleted_at", columnDefinition = "datetime")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private int isDeleted;

    public AdminRolePermission() {
    }

    public AdminRolePermission(String title, String client_addPerm, String client_updatePerm, String client_deletePerm, String client_viewPerm, String admin_addPerm, String admin_updatePerm, String admin_deletePerm, String admin_viewPerm, AdminRole adminRole, String createdBy, String updatedBy, LocalDateTime createdDate, LocalDateTime updatedAt, String deletedBy, LocalDateTime deletedAt, int isDeleted) {
        this.title = title;
        this.client_addPerm = client_addPerm;
        this.client_updatePerm = client_updatePerm;
        this.client_deletePerm = client_deletePerm;
        this.client_viewPerm = client_viewPerm;
        this.admin_addPerm = admin_addPerm;
        this.admin_updatePerm = admin_updatePerm;
        this.admin_deletePerm = admin_deletePerm;
        this.admin_viewPerm = admin_viewPerm;
        this.adminRole = adminRole;
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

    public String getClient_addPerm() {
        return client_addPerm;
    }

    public void setClient_addPerm(String client_addPerm) {
        this.client_addPerm = client_addPerm;
    }

    public String getClient_updatePerm() {
        return client_updatePerm;
    }

    public void setClient_updatePerm(String client_updatePerm) {
        this.client_updatePerm = client_updatePerm;
    }

    public String getClient_deletePerm() {
        return client_deletePerm;
    }

    public void setClient_deletePerm(String client_deletePerm) {
        this.client_deletePerm = client_deletePerm;
    }

    public String getClient_viewPerm() {
        return client_viewPerm;
    }

    public void setClient_viewPerm(String client_viewPerm) {
        this.client_viewPerm = client_viewPerm;
    }

    public String getAdmin_addPerm() {
        return admin_addPerm;
    }

    public void setAdmin_addPerm(String admin_addPerm) {
        this.admin_addPerm = admin_addPerm;
    }

    public String getAdmin_updatePerm() {
        return admin_updatePerm;
    }

    public void setAdmin_updatePerm(String admin_updatePerm) {
        this.admin_updatePerm = admin_updatePerm;
    }

    public String getAdmin_deletePerm() {
        return admin_deletePerm;
    }

    public void setAdmin_deletePerm(String admin_deletePerm) {
        this.admin_deletePerm = admin_deletePerm;
    }

    public String getAdmin_viewPerm() {
        return admin_viewPerm;
    }

    public void setAdmin_viewPerm(String admin_viewPerm) {
        this.admin_viewPerm = admin_viewPerm;
    }

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole) {
        this.adminRole = adminRole;
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
