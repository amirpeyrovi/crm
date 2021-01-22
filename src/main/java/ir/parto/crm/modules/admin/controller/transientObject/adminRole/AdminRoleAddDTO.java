package ir.parto.crm.modules.admin.controller.transientObject.adminRole;

import ir.parto.crm.modules.admin.model.entity.AdminRole;

import java.io.Serializable;

public class AdminRoleAddDTO implements Serializable {
    private String title;
    private String description;

    public AdminRoleAddDTO() {
    }

    public AdminRoleAddDTO(String title, String description) {
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

    public AdminRole convert2Object(){
        AdminRole adminRole = new AdminRole();
        if(this.title != null) adminRole.setTitle(this.title);
        if(this.description != null) adminRole.setDescription(this.description);
        return adminRole;
    }
}