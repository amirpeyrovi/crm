package ir.parto.crm.modules.authenticate.model.entity;
import ir.parto.crm.modules.admin.model.entity.AdminRole;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CRM_PERMISSION_VALUE")
public class PermissionValue {
    @Id
    @Column(name = "PERMISSION_VALUE_ID", columnDefinition = "number")
    @SequenceGenerator(name = "permission_value_seq", sequenceName = "permission_value_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "permission_value_seq")
    private Long permissionValueId;

    @Column(name = "TITLE" , columnDefinition = "nvarchar2(50)")
    private String title;

    @ManyToOne
    @JoinColumn(name = "ADMIN_ROLE_ID", foreignKey = @ForeignKey(name = "permission_value_role_fk"))
    private AdminRole adminRole;

    @ManyToOne
    @JoinColumn(name = "PERMISSION_ID", foreignKey = @ForeignKey(name = "permission_value_permission_fk"))
    private Permission permission;

    @Column(name = "CLIENT_ADD_ITEM" , columnDefinition = "Number(1)")
    private int clientAddItem;

    @Column(name = "CLIENT_UPDATE_ITEM" , columnDefinition = "Number(1)")
    private int clientUpdateItem;

    @Column(name = "CLIENT_DELETE_ITEM" , columnDefinition = "Number(1)")
    private int clientDeleteItem;

    @Column(name = "CLIENT_SHOW_ITEM" , columnDefinition = "Number(1)")
    private int clientShowItem;

    @Column(name = "ADMIN_ADD_ITEM" , columnDefinition = "Number(1)")
    private int adminAddItem;

    @Column(name = "ADMIN_UPDATE_ITEM" , columnDefinition = "Number(1)")
    private int adminUpdateItem;

    @Column(name = "ADMIN_DELETE_ITEM" , columnDefinition = "Number(1)")
    private int adminDeleteItem;

    @Column(name = "ADMIN_SHOW_ITEM" , columnDefinition = "Number(1)")
    private int adminShowItem;

    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "TIMESTAMP(6)")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_at", columnDefinition = "TIMESTAMP(6)")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private LocalDateTime isDeleted;

    public PermissionValue() {
    }

    public PermissionValue(String title, AdminRole adminRole, Permission permission, int clientAddItem, int clientUpdateItem, int clientDeleteItem, int clientShowItem, int adminAddItem, int adminUpdateItem, int adminDeleteItem, int adminShowItem, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, LocalDateTime isDeleted) {
        this.title = title;
        this.adminRole = adminRole;
        this.permission = permission;
        this.clientAddItem = clientAddItem;
        this.clientUpdateItem = clientUpdateItem;
        this.clientDeleteItem = clientDeleteItem;
        this.clientShowItem = clientShowItem;
        this.adminAddItem = adminAddItem;
        this.adminUpdateItem = adminUpdateItem;
        this.adminDeleteItem = adminDeleteItem;
        this.adminShowItem = adminShowItem;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public Long getPermissionValueId() {
        return permissionValueId;
    }

    public void setPermissionValueId(Long permissionValueId) {
        this.permissionValueId = permissionValueId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole) {
        this.adminRole = adminRole;
    }

    public int getClientAddItem() {
        return clientAddItem;
    }

    public void setClientAddItem(int clientAddItem) {
        this.clientAddItem = clientAddItem;
    }

    public int getClientUpdateItem() {
        return clientUpdateItem;
    }

    public void setClientUpdateItem(int clientUpdateItem) {
        this.clientUpdateItem = clientUpdateItem;
    }

    public int getClientDeleteItem() {
        return clientDeleteItem;
    }

    public void setClientDeleteItem(int clientDeleteItem) {
        this.clientDeleteItem = clientDeleteItem;
    }

    public int getClientShowItem() {
        return clientShowItem;
    }

    public void setClientShowItem(int clientShowItem) {
        this.clientShowItem = clientShowItem;
    }

    public int getAdminAddItem() {
        return adminAddItem;
    }

    public void setAdminAddItem(int adminAddItem) {
        this.adminAddItem = adminAddItem;
    }

    public int getAdminUpdateItem() {
        return adminUpdateItem;
    }

    public void setAdminUpdateItem(int adminUpdateItem) {
        this.adminUpdateItem = adminUpdateItem;
    }

    public int getAdminDeleteItem() {
        return adminDeleteItem;
    }

    public void setAdminDeleteItem(int adminDeleteItem) {
        this.adminDeleteItem = adminDeleteItem;
    }

    public int getAdminShowItem() {
        return adminShowItem;
    }

    public void setAdminShowItem(int adminShowItem) {
        this.adminShowItem = adminShowItem;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
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

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(LocalDateTime isDeleted) {
        this.isDeleted = isDeleted;
    }
}
