package ir.parto.crm.modules.authorize.model.entity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "crm_permission")
public class Permission implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "number")
    @SequenceGenerator(name = "permission_seq", sequenceName = "permission_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "permission_seq")
    private Long permissionId;

    @Column(name = "TITLE" , columnDefinition = "nvarchar2(50)")
    private String title;

    @Column(name = "SHOW_NAME" , columnDefinition = "nvarchar2(50)")
    private String showName;

    @Column(name = "create_by", updatable = false, columnDefinition = "nvarchar2(60)")
    private String createdBy;

    @Column(name = "create_at", updatable = false, columnDefinition = "TIMESTAMP(6)")
    @CreationTimestamp
    private LocalDateTime createdDate;

    public Permission() {
    }

    public Permission(String title, String showName, String createdBy, LocalDateTime createdDate) {
        this.title = title;
        this.showName = showName;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
