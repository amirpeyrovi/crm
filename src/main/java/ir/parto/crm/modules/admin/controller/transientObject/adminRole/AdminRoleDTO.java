package ir.parto.crm.modules.admin.controller.transientObject.adminRole;

import ir.parto.crm.modules.admin.model.entity.AdminRole;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AdminRoleDTO implements Serializable {
    private Long adminRoleId;
    private String title;
    private String description;

    public AdminRoleDTO() {
    }

    public AdminRoleDTO(Long adminRoleId, String title, String description) {
        this.adminRoleId = adminRoleId;
        this.title = title;
        this.description = description;
    }

    public AdminRoleDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAdminRoleId() {
        return adminRoleId;
    }

    public void setAdminRoleId(Long adminRoleId) {
        this.adminRoleId = adminRoleId;
    }
    public AdminRole convert2Object() {
        AdminRole dto = new AdminRole();
        if(this.adminRoleId != null) dto.setAdminRoleId(this.adminRoleId);
        if(this.title != null) dto.setTitle(this.title);
        if(this.description != null) dto.setDescription(this.description);
        return dto;
    }
}