package ir.parto.crm.modules.admin.controller.transientObject.adminPermission;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AdminPermissionDTO implements Serializable {
    private Long permissionId;
    private String title;
    private String showName;

    public AdminPermissionDTO() {
    }

    public AdminPermissionDTO(Long permissionId, String title, String showName) {
        this.permissionId = permissionId;
        this.title = title;
        this.showName = showName;
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

}
