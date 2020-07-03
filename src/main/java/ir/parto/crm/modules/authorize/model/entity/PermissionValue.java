package ir.parto.crm.modules.authorize.model.entity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_permission_value")
public class PermissionValue {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "permission_value_seq", sequenceName = "permission_value_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "permission_value_seq")
    private Long permissionValueId;

    @Column(name = "TITLE" , columnDefinition = "nvarchar2(50)")
    private String title;

    @ManyToOne
    @JoinColumn(name = "role", foreignKey = @ForeignKey(name = "permission_value_role_fk"))
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission", foreignKey = @ForeignKey(name = "permission_value_permission_fk"))
    private Permission permission;

    @Column(name = "ADD_ITEM" , columnDefinition = "Number(1)")
    private int addItem;

    @Column(name = "UPDATE_ITEM" , columnDefinition = "Number(1)")
    private int updateItem;

    @Column(name = "DELETE_ITEM" , columnDefinition = "Number(1)")
    private int deleteItem;

    @Column(name = "SHOW_ITEM" , columnDefinition = "Number(1)")
    private int showItem;

    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "update_by", columnDefinition = "nvarchar2(60)")
    private String updatedBy;

    @Column(name = "deleted_by", columnDefinition = "nvarchar2(60)")
    private String deletedBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "update_at", columnDefinition = "datetime")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "datetime")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", columnDefinition = "number(1)")
    private LocalDateTime isDeleted;

    public PermissionValue() {
    }

    public PermissionValue(String title, Role role, Permission permission, int addItem, int updateItem, int deleteItem, int showItem, String createdBy, String updatedBy, String deletedBy, LocalDateTime createdDate, LocalDateTime updatedAt, LocalDateTime deletedAt, LocalDateTime isDeleted) {
        this.title = title;
        this.role = role;
        this.permission = permission;
        this.addItem = addItem;
        this.updateItem = updateItem;
        this.deleteItem = deleteItem;
        this.showItem = showItem;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public int getAddItem() {
        return addItem;
    }

    public void setAddItem(int addItem) {
        this.addItem = addItem;
    }

    public int getUpdateItem() {
        return updateItem;
    }

    public void setUpdateItem(int updateItem) {
        this.updateItem = updateItem;
    }

    public int getDeleteItem() {
        return deleteItem;
    }

    public void setDeleteItem(int deleteItem) {
        this.deleteItem = deleteItem;
    }

    public int getShowItem() {
        return showItem;
    }

    public void setShowItem(int showItem) {
        this.showItem = showItem;
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
